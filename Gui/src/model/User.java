/**
 * 
 */
package model;

import java.net.URL;

import org.json.JSONObject;

/**
 * @author Giang Nguyễn
 *
 * Nhìn giề! đọc code kìa!
 */
public class User {
	private int id;
	private String name;
	private String phone;
	private String address;
	private String email;
	private String role;
	private String createdTime;
	/**
	 * 
	 */
	public User() {
		super();
	}
	/**
	 * @param id
	 * @param name
	 * @param phone
	 * @param address
	 * @param email
	 * @param role
	 * @param createdTime
	 */
	public User(int id, String name, String phone, String address, String email, String role, String createdTime) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.role = role;
		this.createdTime = createdTime;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}
	/**
	 * @return the createdTime
	 */
	public String getCreatedTime() {
		return createdTime;
	}
	/**
	 * @param createdTime the createdTime to set
	 */
	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "id: " + this.getId() +
				",name: "+ this.getName() +
				"phone: "+ this.getPhone()+
				"address: " + this.getAddress() +
				"email: " + this.getEmail() + 
				"role: " + this.getRole() + 
				"created time: " +this.getCreatedTime();
	}
	
}
