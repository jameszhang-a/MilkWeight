package application;

import java.util.ArrayList;

import application.Farm.WeightData;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DataFile {

	//required methods

  public DataFile() {};
  
  public ArrayList<String> getFarmReport(String ID, ArrayList<Farm> farms){
    Farm farm = new Farm("");
    for(int i = 0; i < farms.size(); i++) {
      if(ID.equals(farms.get(i).getID())) {
        farm = farms.get(i);
        System.out.println(i);
      }
    }
    
    ArrayList<String> farmReport = new ArrayList<String>();
    
    int totalWeight = getYearlyWeightForFarm(farm);
    String weight = /*"Total weight for farm: " + */ "" + totalWeight;
    farmReport.add(weight);
    
    for(int i = 0; i < 12; i++) {
      int monthlyWeight = getMonthlyWeight(i, farm);
      String entry =/* "Month " + (i + 1) + " weight: " +*/ "" + monthlyWeight;
      farmReport.add(entry);
      
      String averageEntry =/* "Percent of total weight for month " + (i + 1) +*/"" + getMonthlyAverage(monthlyWeight, totalWeight);
      farmReport.add(averageEntry);
    }
    
    return farmReport;
   
  }
  
  public static int getMonthlyWeight(int month, Farm farm) {
    int total = 0;
    for(int i = 0; i < farm.getData().length; i++) {
      for(int j = 0; j < farm.getData()[i].size(); j++) {
        if(i == month) {
          String tempWeight = farm.getWeight(farm.getData()[i].get(j));
          int weight = Integer.parseInt(tempWeight);
          total += weight;
        }      
      }
    }
    
    return total;
  }
  
  //required methods
  public double getMonthlyAverage(int monthTotal, int totalWeight) {
    double average = ((double)monthTotal / (double)totalWeight) * 100;
    long factor = (long) Math.pow(10, 2);
    average = average * factor;
    long temp = Math.round(average);
    return (double) temp / factor;
  }
 
  
  public int getMonthlyMin(String ID, String year) {
      return 0;
  }
  
  public int getMonthlyMax(String ID, String year) {
      return 0;
  }
  
  public int getMonthlyAverageForFarm(CheeseFactory factory, String ID) {
    return 0;
}
  
  public int getMonthlyMinForFarm() {
      return 0;
  }
  
  public int getMonthlyMaxForFarm() {
      return 0;
  }
  
  public int getDataSortedByField() {
      return 0;
  }
  
  public int getAverageInDataRange() {
      return 0;
  }
  
  public int getMinInDataRange() {
       return 0;
  }
  
  public int getMaxInDataRange() {
      return 0;
  }
  
  public static ArrayList<String> getAnnualReport(ArrayList<Farm> farms){
    ArrayList<String> annualReport = new ArrayList<String>();
    ArrayList<Integer> yearTotals = new ArrayList<Integer>();
    int totalWeight = 0;
    
    //calculate the total weight of all farms and add to annual report
    for(int i = 0; i < farms.size(); i++) {
      totalWeight += getYearlyWeightForFarm(farms.get(i));
    }

    annualReport.add("Total weight for all farms: " + totalWeight);
    
    //get the total weight by farm and add to annual report
    for(int i = 0; i < farms.size(); i++) {
      int farmTotal = getYearlyWeightForFarm(farms.get(i));
      String entry = farms.get(i).getID() + " total weight: " + farmTotal;
      annualReport.add(entry);
      
      double average = getYearlyAverage(farmTotal, totalWeight);
      String averageEntry = "Average for " + farms.get(i).getID() + " " + average;
      annualReport.add(averageEntry);
    }
    
    return annualReport;
  }
  
  public static double getYearlyAverage(int farmTotal, int totalWeight) {
    double average = ((double)farmTotal / (double)totalWeight) * 100;
    long factor = (long) Math.pow(10, 2);
    average = average * factor;
    long temp = Math.round(average);
    return (double) temp / factor;
  }
  
  public static int getYearlyWeightForFarm(Farm farm) {
    int total = 0;
    for(int i = 0; i < farm.getData().length; i++) {
      for(int j = 0; j < farm.getData()[i].size(); j++) {
        String tempWeight = farm.getWeight(farm.getData()[i].get(j));
        int weight = Integer.parseInt(tempWeight);
        total += weight;
      }
    }
    
    return total;
  }


}