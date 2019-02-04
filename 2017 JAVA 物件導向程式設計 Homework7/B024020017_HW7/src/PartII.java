import com.opencsv.CSVReader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import javax.swing.JButton;

/**
 * A class to let user repeatedly check the amount of buyer's purchasing products by id,
 * and plot into a bar chart.
 * @author saberLiou
 */
public class PartII {
	/**
	 * Standard input object.
	 */
	private static Scanner scan;

	/*
	 * CSVReader object.
	 */
	private static CSVReader reader;
	
	/**
	 * @param args not used
	 */
	public static void main(String[] args) {
		int originalRecord = 0, repeatRecord = 0, sameIdProduct = 0;
		
		/* Read csv file. */
		try {
			reader = new CSVReader(new FileReader("src/query_result.csv"), ',', '\'', 1);
		} catch (FileNotFoundException e) {
			System.out.println("Error: csv file not found.");
		}
		
		/* Refined csv records container. */
		HashMap<String, ArrayList<String>> records = new HashMap<String, ArrayList<String>>();
		/* Product kinds container. */
		HashSet<String> productKinds = new HashSet<String>();
		
		/* Read csv records. */
		try {
			String[] record;
			while ((record = reader.readNext()) != null){
				/* Set a new product kind if distinct. */
				productKinds.add(record[1]);
				originalRecord++;
				
				if (records.containsKey(record[0])){
					if (records.get(record[0]).contains(record[1])){
						/* Save repeat product into the same id buyer. */
						records.get(record[0]).add(record[1]);
						repeatRecord++;
					}
					else{
						/* Save a different product into the same id buyer. */
						records.get(record[0]).add(record[1]);
						sameIdProduct++;
					}
				}
				else{
					/* Add a new record. */
					ArrayList<String> products = new ArrayList<String>();
					products.add(record[1]);
					records.put(record[0], products);
				}
			}
		} catch (IOException e) {
			System.out.println("Error: unable to read csv file.");
		}
		
		/* Show refined csv records, id => product(s).*/
		for (String idSex : records.keySet()){
			System.out.print(idSex + " => ");
			for (String p : records.get(idSex)){
				System.out.print(p + "\t");
			}
			System.out.println("");
		}
		
		/* Show the statistic result. */
		int validRecord = originalRecord - repeatRecord;
		System.out.println("======Statistics======"
				+ "\nOriginal records: "+ originalRecord
				+ "\nRepeat records: " + repeatRecord
				+ "\nValid records: " + validRecord
				+ "\nProducts with repeat id: " + sameIdProduct);
		
		/* Product kind as key container of specific id. */
		HashMap<String, Integer> idProducts = new HashMap<String, Integer>();
		/* Initialize product key container. */
		for (String k : productKinds) {
			idProducts.put(k, 0);
		}
		
		while (true){
			/* Show the usage. */
			System.out.println("Please input the ID:");
			scan = new Scanner(System.in);
			String id = scan.nextLine();
			if (!records.containsKey(id)){
				System.out.println("ID not found!");
			}
			else{
				/* Group by product key. */
				for (String p : records.get(id)){
					idProducts.replace(p, idProducts.get(p) + 1);	
				}
				
				/* Prepare product dataset of specific id for bar chart. */
				DefaultCategoryDataset dataset = new DefaultCategoryDataset();
				for (String p : idProducts.keySet()){
					System.out.println(p + ": " + idProducts.get(p));
					dataset.setValue(idProducts.get(p), p, p);
				}
				
				/* Build bar chart for user. */
				JFreeChart chart = ChartFactory.createBarChart3D(id + "的購買統計", "產品類別", "個數", dataset);
				/* Set bar chart transparent. */
				chart.getPlot().setForegroundAlpha(0.5f);
				chart.setBackgroundPaint(new Color(255, 181, 181));
				ChartPanel chartpanel = new ChartPanel(chart);
				
				/* Put bar chart panel in the frame. */
				ApplicationFrame af = new ApplicationFrame("JAVA_HW7_長條圖分佈");
				af.setSize(600, 520);
				chartpanel.setPreferredSize(new Dimension(af.getSize().width, af.getSize().height - 70));
				af.add(chartpanel, BorderLayout.NORTH);

				/* Make ApplicationFrame middle in the screen. */
				Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
				af.setLocation((screenDimension.width - af.getSize().width) / 2, (screenDimension.height - af.getSize().height) / 2);
				af.setVisible(true);
				
				/* Add a close button to terminate the execution. */
				JButton closeButton = new JButton("Click to hide the bar chart.");
				closeButton.setFont(new Font("SansSerif", Font.BOLD, 24));
				closeButton.setBounds(0, chartpanel.getSize().height, af.getSize().width, 50);
				closeButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						af.dispose();
					}
				});
				af.add(closeButton, BorderLayout.SOUTH);
			}
		}
	}
}