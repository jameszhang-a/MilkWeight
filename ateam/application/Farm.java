package application;

import java.util.ArrayList;

/**
 * Farm - TODO Describe purpose of this type
 * 
 *
 */
public class Farm {

	private String farmID;
	private String owner;
	private ArrayList<WeightData>[] milkData;
	// milkData data structure

	/**
	 * 
	 * WeightData - Data type that stores a weight and a date
	 *
	 */
	class WeightData {

		String date;
		String milkWeight;

		private WeightData(String date, String milkWeight) {
			this.date = date;
			this.milkWeight = milkWeight;
		}

		private void setWeight(String milkWeight) {
			this.milkWeight = milkWeight;
		}
		
		private String getWeight() {
          return milkWeight;
        }
	}

	/**
	 * public constructor for the Farm class. Initializes the ID and
	 * 
	 * @param ID
	 */
	public Farm(String ID) {
		farmID = ID;
		milkData = new ArrayList[12];
		for (int i = 0; i < milkData.length; i++) {
			milkData[i] = new ArrayList<WeightData>();
		}
	}

	/**
	 * adds a weight to a certain date TODO invalid data handling
	 * 
	 * @param date
	 * @param weight
	 * @return true if new weight is added, false otherwise
	 */
	public boolean insertMilkForDate(String date, String weight) {
		// create new WeightData object
		WeightData data = new WeightData(date, weight);

		// get month of the data
		int monthIndex = Integer.parseInt(date.split("-")[1]);
		milkData[monthIndex - 1].add(data);
		return false;
	}

	/**
	 * edits an existing weight at a date
	 * 
	 * @param date
	 * @param curWeight
	 * @param newWeight
	 * @return true if a weight is replaced, false otherwise
	 */
	public boolean editMilkForDate(String date, String curWeight, String newWeight) {
		// creates data wit new weight
		WeightData data = new WeightData(date, curWeight);

		// get month from data
		int monthIndex = Integer.parseInt(date.split("-")[1]);
		ArrayList<WeightData> monthData = milkData[monthIndex - 1];

		// finds the weight entry and replaces with new weight
		for (WeightData d : monthData) {
			if (d.milkWeight.equals(curWeight)) {
				d.setWeight(newWeight);
				return true;
			}
		}
		return false;
	}

	public String getID() {
		return farmID;
	}

	/**
	 * clears all data from this farm
	 * 
	 * @return the empty ArrayList
	 */
	public ArrayList<WeightData>[] clearData() {
		milkData = new ArrayList[12];
		return milkData;
	}

	/**
	 * removes a weight from a date
	 * 
	 * @param date
	 * @param curWeight
	 * @return true if deleted, false otherwise
	 */
	public boolean removeMilkForDate(String date, String curWeight) {
		// gets the month
		int monthIndex = Integer.parseInt(date.split("-")[1]);

		// searches for the weight, if found then delete
		for (int i = 0; i < milkData[monthIndex - 1].size(); i++) {
			if (milkData[monthIndex - 1].get(i).milkWeight.equals(curWeight)) {
				milkData[monthIndex - 1].remove(i);
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<WeightData>[] getData() {
	  return milkData;
	}
	
	public String getWeight(WeightData data) {
	      return data.getWeight();
	    }

	public int getIntWeight() {
		int sum = 0;
		for(int i = 0; i < this.milkData.length; i++) {
			for(int j = 0; j < milkData[i].size(); j++) {
				sum += Integer.parseInt(milkData[i].get(j).getWeight());
			}
		}
      return sum;
	}
	
	/**
	 * returns farm ID
	 */
	public String toString() {
		return farmID;
	}

}
