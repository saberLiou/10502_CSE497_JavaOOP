import com.opencsv.CSVReader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.ui.ApplicationFrame;

import java.awt.BorderLayout;
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
import javax.swing.JButton;

/**
 * A class to calculate the every sorts of products and sex proportion in csv file,
 * and plot into two seperate pie charts.
 * @author saberLiou
 */
public class PartI {
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
			while ((record = reader.readNext()) != null) {
				/* Set a new product kind if distinct. */
				productKinds.add(record[1]);
				originalRecord++;

				/* Concatenate id with sex. */
				String idSex = record[0] + ":" + record[2];
				if (records.containsKey(idSex)) {
					if (records.get(idSex).contains(record[1])) {
						/* Get rid of repeat product with same id, sex and product. */
						repeatRecord++;
					} else {
						/* Save a different product into the same id buyer. */
						records.get(idSex).add(record[1]);
						sameIdProduct++;
					}
				} else {
					/* Add a new record. */
					ArrayList<String> products = new ArrayList<String>();
					products.add(record[1]);
					records.put(idSex, products);
				}
			}
		} catch (IOException e) {
			System.out.println("Error: unable to read csv file.");
		}
		
		/* Product kind as key container. */
		HashMap<String, Integer> productStatistics = new HashMap<String, Integer>();
		/* Initialize product key container. */
		for (String k : productKinds) {
			productStatistics.put(k, 0);
		}
		
		/* Show refined csv records, id:sex => product(s).*/
		for (String idSex : records.keySet()) {
			System.out.print(idSex + " => ");
			for (String p : records.get(idSex)) {
				System.out.print(p + "\t");
				/* Group by product key. */
				productStatistics.replace(p, productStatistics.get(p) + 1);
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
		
		/* Prepare product dataset for pie chart. */
		DefaultPieDataset pDataset = new DefaultPieDataset();
		for (String p : productStatistics.keySet()) {
			System.out.print(p + ": " + productStatistics.get(p) + " / " + validRecord);
			double proportion = (double) productStatistics.get(p) / validRecord;
			System.out.println(" = " + proportion);
			pDataset.setValue(p + "\n: " + String.format("%.2f", (proportion * 100)) + "%", productStatistics.get(p));
		}
		
		/* Sex kind as key container. */
		HashMap<String, Integer> sexStatistics = new HashMap<String, Integer>();
		/* Initialize sex key container. */
		sexStatistics.put("Male", 0);
		sexStatistics.put("Female", 0);
		
		/* Split idSex, group by sex key. */
		for (String c : records.keySet()) {
			String[] s = c.split(":");
			if (s[1].equals("M")) {
				sexStatistics.replace("Male", sexStatistics.get("Male") + 1);
			} else {
				sexStatistics.replace("Female", sexStatistics.get("Female") + 1);
			}
		}
		
		/* Prepare sex dataset for pie chart. */
		int sexCount = validRecord - sameIdProduct;
		DefaultPieDataset sDataset = new DefaultPieDataset();
		for (String s : sexStatistics.keySet()) {
			double proportion = (double) sexStatistics.get(s) / sexCount;
			System.out.println(s + ": " + sexStatistics.get(s) + " / " + sexCount + " = " + proportion);
			sDataset.setValue(s + " :\n" + String.format("%.2f", (proportion * 100)) + "%", proportion);
		}

		/* Build pie chart for product. */
		JFreeChart pChart = ChartFactory.createPieChart3D("product_type", pDataset, false, false, false);
		/* Set pie chart transparent. */
		pChart.getPlot().setForegroundAlpha(0.5f);
		ChartPanel leftPanel = new ChartPanel(pChart);

		/* Build pie chart for sex. */
		JFreeChart sChart = ChartFactory.createPieChart3D("id_sex", sDataset, false, false, false);
		/* Set pie chart transparent. */
		sChart.getPlot().setForegroundAlpha(0.5f);
		ChartPanel rightPanel = new ChartPanel(sChart);

		/* Put two pie chart panel in the frame. */
		ApplicationFrame af = new ApplicationFrame("JAVA_HW7_PartI");
		af.setSize(1000, 500);
		leftPanel.setPreferredSize(new Dimension(af.getSize().width / 2, af.getSize().height));
		rightPanel.setPreferredSize(new Dimension(af.getSize().width / 2, af.getSize().height));
		af.add(leftPanel, BorderLayout.WEST);
		af.add(rightPanel, BorderLayout.EAST);

		/* Make ApplicationFrame middle in the screen. */
		Dimension screenDimension = Toolkit.getDefaultToolkit().getScreenSize();
		af.setLocation((screenDimension.width - af.getSize().width) / 2, (screenDimension.height - af.getSize().height) / 2);
		af.setDefaultCloseOperation(ApplicationFrame.EXIT_ON_CLOSE);
		af.setVisible(true);

		/* Add a close button to terminate the execution. */
		JButton closeButton = new JButton("Click to close.");
		closeButton.setFont(new Font("SansSerif", Font.BOLD, 24));
		closeButton.setBounds(0, 0, 50, 50);
		closeButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		af.add(closeButton, BorderLayout.CENTER);
	}
}