package myjava.homework;
import java.util.HashSet;
import java.util.stream.Collectors;

/**
 * A class to store the user name and his or her friends object set.
 * @author saberLiou
 */
public class User {
	private String username;
	private HashSet<User> friends;
	
	/**
	 * Constructs an User.
	 * @param username the user name
	 */
	public User(String username){
		setUsername(username);
		friends = new HashSet<User>();
	}
	
	/**
	 * Sets the user name of the user
	 * @param username the user name
	 */
	public void setUsername(String username){
		this.username = username;
	}
	
	/**
	 * Sets a set of friends object to the user.
	 * @param friends a set of friends
	 */
	public void setFriends(HashSet<User> friends){
		this.friends = friends;
	}
	
	/**
	 * Adds a friend object to the user's friends set and
	 * returns this object for continuously friend-adding operation.
	 * @param friend a friend to add
	 * @return this object with its friends set modified
	 */
	public User addFriend(User friend){
		this.friends.add(friend);
		return this;
	}
	
	/**
	 * Gets the user name.
	 * @return the user name
	 */
	public String getUsername(){
		return username;
	}
	
	/**
	 * Gets the set of user's friends safely.
	 * @return the set of user's friends
	 */
	public HashSet<User> getFriends(){
		return friends.stream().map(u -> u).collect(Collectors.toCollection(HashSet::new));
	}
}