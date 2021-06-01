/**
 * EditData.java created by james on Surface Pro in ateam
 *
 * Author:		James Zhang (jzhang924@wisc.edu)
 * Date: 		@date
 * 
 * Course:		CS400
 * Semester: 	Spring 2020
 * Lecture: 	001
 * 
 * IDE: 		Eclipse IDE for Java Developers 
 * 
 * List Collaborators:	NONE
 * 
 * Other Credits: 		NONE
 * 
 * Known Bugs:	describe known unresolved bugs here
 */
package application;

/**
 * EditData - TODO Describe purpose of this user-defined type
 * 
 * @author zhang (2020)
 *
 */
public class EditData {

	private String weight;
	private String ID;

	public EditData(String weight, String ID) {
		this.weight = weight;
		this.ID = ID;
	}
	
	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}
}
