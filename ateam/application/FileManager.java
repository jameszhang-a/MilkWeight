package application;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * FileManager - reads and parses the files
 *
 */
public class FileManager {

	private String outputFile;
	private ArrayList<String> farmsFound; // keeps track of farms that have been found while parsing
	private int numData;
	private ArrayList<File> files;
	private CheeseFactory factory;

	/**
	 * public constructor for the File Manager class
	 * 
	 * @param file name
	 */
	public FileManager(ArrayList<File> files) {
		this.files = files;
		farmsFound = new ArrayList<String>();
		factory = new CheeseFactory(); // instantiate factory
		System.out.println("This many files entered: " + files.size());
		this.readFile();
	}

	/**
	 * reads the file
	 * 
	 * @return true if read, false otherwise
	 */
	public boolean readFile() {
		// creates a factory, collection of farms

		// loops ovber how many files there are
		for (int i = 0; i < files.size(); i++) {
			// reading file
			try {

				// gets each of the files stored in the files list
				BufferedReader fileReader = new BufferedReader(new FileReader(files.get(i)));
				String line = "";

				fileReader.readLine();

				while ((line = fileReader.readLine()) != null) {
					String[] data = line.split(",");

					// testing - delete later
					// System.out.println(Arrays.toString(data));

					if (farmsFound.indexOf(data[1]) == -1) {
						factory.createFarm(data[1]);
						farmsFound.add(data[1]);
						factory.insertData(data);

						// keeps tract of total data
						numData++;
					} else {
						factory.insertData(data);

						// keeps tract of total data
						numData++;
					}
				}

			} catch (IOException e) {
				System.out.println("I/O exception while reading files");
				e.printStackTrace();
			}
		}
		return false;
	}

	public boolean writeToFile(ArrayList<Farm> arrayList) {
		FileWriter outputWriter;
		try {
			outputWriter = new FileWriter("report.txt");
			for (Farm str : arrayList) {
				outputWriter.write(str + " -- total weight: " + str.getIntWeight() + System.lineSeparator());
			}
			outputWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	public String getFileContents() {
		return "";
	}

	public CheeseFactory getFactory() {
		return factory;
	}

	/**
	 * testing method to print out some info
	 */
	private void print() {
		System.out.println(farmsFound);
		System.out.println("# of different farms: " + farmsFound.size());
		System.out.println("Total data added: " + numData);
	}

	public static void main(String[] args) {

		// Testing relative path
		File testFile = new File("");
		String currentPath = testFile.getAbsolutePath();
		System.out.println("current path is: " + currentPath);

		String file = "2019-4.csv";
		File file1 = new File("2019-4.csv");
		File file2 = new File("2019-10.csv");

		ArrayList<File> files = new ArrayList();
		files.add(file2);
		files.add(file1);

		FileManager mg1 = new FileManager(files);
		mg1.readFile();
		mg1.print();
	}

}