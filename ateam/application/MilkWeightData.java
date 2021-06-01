package application;

public class MilkWeightData {
	private String month;
	private String weight;
	private String weightPercentage;
	
	public MilkWeightData(String month, String weight, String weightPercentage) {
		this.month = month;
		this.weight = weight;
		this.weightPercentage = weightPercentage;
	}
	
	public MilkWeightData(String weight, String weightPercentage) {
		this.weight = weight;
		this.weightPercentage = weightPercentage;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWeightPercentage() {
		return weightPercentage;
	}

	public void setWeightPercentage(String weightPercentage) {
		this.weightPercentage = weightPercentage;
	}
}
