package application;

import java.util.ArrayList;

/**
 * CheeseFactory - This class stores data from multiple farms
 *
 */
public class CheeseFactory {

	private String name;
	private ArrayList<Farm> farms; // array list containing farm objects

	/**
	 * public constructor for cheese factory
	 */
	public CheeseFactory() {
		farms = new ArrayList<Farm>();
	}

	public boolean insertSingleData() {
		return false;
	}

	/**
	 * Inserts a parsed line from the csv file into farm
	 * 
	 * @param data
	 * @return
	 */
	public boolean insertData(String[] data) {
		for (int i = 0; i < farms.size(); i++) {
			if (data[1].equals(farms.get(i).getID())) {
				farms.get(i).insertMilkForDate(data[0], data[2]);
			}
		}
		return false;
	} 

	public boolean editSingleData() {
		return false;
	}

	public void createFarm(String farmID) {
		Farm farm = new Farm(farmID);
		farms.add(farm);
	}
	
	public ArrayList<Farm> getFarms(){
		System.out.println("Farms processed: " + farms.size());
	    return farms;
	  }


	// removeSingleData()

}
