package application;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
	// NOTE: this.getParameters().getRaw() will command line args
	Scene fileSelect, reportSelect, farmReport, annualReport, monthlyReport, rangeReport, farmReportNumbers,
			anuallReportNumbers, monthlyReportNumbers, rangeReportNumbers; // Based on our design layout
	ComboBox reportBox;
	Stage window;
	private ArrayList<File> files;
	private FileManager fileManager;

	@Override
	public void start(Stage primaryStage) throws Exception {
		window = primaryStage;
		// File Select Scene
		// ObservableList<String> options = FXCollections.observableArrayList("File 1",
		// "File 2", "File 3");
		// ComboBox fileBox = new ComboBox(options);
		// fileBox.setPromptText("Select file");

		// file chooser, allow uses to choose files
		Button fileButton = new Button("Import Files");
		MyHandler handler = new MyHandler(fileButton);
		fileButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				files = new ArrayList<File>();
				System.out.println("Button clicked");
				FileChooser choose = new FileChooser();
				// choose.setInitialDirectory(new File("\\aTeamMilkWeight\\ateam"));
				List<File> picked = choose.showOpenMultipleDialog(null);

				if (picked != null) {
					for (int i = 0; i < picked.size(); i++) {
						files.add(picked.get(i));
						System.out.println(picked.get(i).getName());
					}
					// System.out.println(picked.getName());
				} else {
					System.out.println("Something wrong");
				}

				System.out.println("Files are: " + files.get(0));
			}
		});

//		// Constructor for the files manager
//		FileManager fileManager = new FileManager(files);

		// adds panels to root
		Button next = new Button("Next");
		next.setOnAction(e -> window.setScene(reportSelect)); // Sets new scene on click
		BorderPane root = new BorderPane(); // Main layout is Border Pane example (top,left,center,right,bottom)
		root.setTop(new Label("Milk Weight"));
		root.setCenter(fileButton);
		root.setBottom(next);

		fileSelect = new Scene(root, 400, 400);

		// Report Select Scene
		// Report Select Scene
		VBox layout = new VBox(8);
		Label fileName = new Label("File Name: ");

		Label farmReportLabel = new Label("Farm Report: ");
		Text farmDescription = new Text("Prompt user for a farm id and year (or use all available data) "
				+ "\nThen, display the total milk weight and percent of the total of all " + "\nfarm for each month. "
				+ "\nSort, the list by month number 1-12, show total weight, then that "
				+ "\nfarm's percent of the total milk received for each month. ");
		Label monthlyReportLabel = new Label("Monthly Report: ");
		Text monthlyDescription = new Text(
				"Ask for year and month" + "\nThen, display a list of totals and percent of total by farm."
						+ "\nThe list must be sorted by Farm ID, or you can prompt for "
						+ "\nascending or descending by weight.");
		Label rangeLabel = new Label("Data Range Report: ");
		Text rangeDescription = new Text("Prompt user for start date (year-month-day)" + "\nand end month-day,"
				+ "\nThen display the total milk weight per farm and the percentage of the "
				+ "\ntotal for each farm over that date range."
				+ "\nThe list must be sorted by Farm ID, or you can prompt for ascending or descending "
				+ "\norder by weight or percentage.");
		Label annualReportLabel = new Label("Annual Report: ");
		Text annualDescription = new Text("Ask for year"
				+ "\nThen display list of total weight and percent of total weight of all farms by farm for the year."
				+ "\nSort by Farm ID, or you can allow the user to select display ascending or descensding by weight.");

		Label prompt = new Label("Select report to view: ");
		ObservableList<String> reports = FXCollections.observableArrayList("Farm Report", "Annual Report",
				"Monthly Report", "Data Range Report");
		reportBox = new ComboBox(reports);
		reportSelect = new Scene(layout, 600, 600);
		Button reportNext = new Button("Get Report");
		reportNext.setOnAction(e -> getReport()); // Sets new scene on click
		layout.getChildren().addAll(fileName, farmReportLabel, farmDescription, monthlyReportLabel, monthlyDescription,
				rangeLabel, rangeDescription, annualReportLabel, annualDescription, prompt, reportBox, reportNext);

		// Farm Report Scene
		VBox farmLayout = new VBox(5);
		Label farmLabel = new Label("Farm Report");
		TextField year = new TextField();
		year.setPromptText("Year");
		TextField ID = new TextField();
		ID.setPromptText("ID");
		Button create = new Button("Create Report");
		Button getData = new Button("Display/Edit data");
		Button back = new Button("Back");
		back.setOnAction(e -> window.setScene(reportSelect));
		// getData.setOnAction(e -> displayEditData(year.getText(), ID.getText()));
		create.setOnAction(e -> displayFarmData(year.getText(), ID.getText()));
		farmReport = new Scene(farmLayout, 400, 400);
		farmLayout.getChildren().addAll(farmLabel, year, ID, getData, create, back);

		// Annual Report Scene
		VBox anuallLayout = new VBox(5);
		Label annualLabel = new Label("Annual Report");
		TextField annualYear = new TextField();
		annualYear.setPromptText("Year");
		Button annuallNext = new Button("Create Report");
		Button annualBack = new Button("Back");
		annualBack.setOnAction(e -> window.setScene(reportSelect));
		annuallNext.setOnAction(e -> displayAnnualData(annualYear.getText()));
		annualReport = new Scene(anuallLayout, 400, 400);
		anuallLayout.getChildren().addAll(annualLabel, annualYear, annuallNext, annualBack);

		// Monthly Report Scene
		VBox monthlyLayout = new VBox(5);
		Label monthLabel = new Label("Monthly Report");
		TextField monthlyMonth = new TextField();
		monthlyMonth.setPromptText("Month");
		TextField monthlyYear = new TextField();
		monthlyYear.setPromptText("Year");
		Button monthlyBack = new Button("Back");
		monthlyBack.setOnAction(e -> window.setScene(reportSelect));
		Button monthlyNext = new Button("Create Report");
		monthlyNext.setOnAction(e -> displayMonthlyData(monthlyMonth.getText(), monthlyYear.getText()));
		monthlyReport = new Scene(monthlyLayout, 400, 400);
		monthlyLayout.getChildren().addAll(monthLabel, monthlyMonth, monthlyYear, monthlyNext, monthlyBack);

		// Data Range Report Scene
		VBox dataRange = new VBox(10);
		Label rangeReportLabel = new Label("Data Range Report");
		Label startDate = new Label("Start Date:");
		TextField startYear = new TextField();
		startYear.setPromptText("Year");
		TextField startDay = new TextField();
		startDay.setPromptText("Start Day");
		TextField startMonth = new TextField();
		startMonth.setPromptText("Start Month");
		Label endDate = new Label("End Date:");
		TextField endDay = new TextField();
		endDay.setPromptText("End Day");
		TextField endMonth = new TextField();
		endMonth.setPromptText("End Month");
		Button rangeBack = new Button("Back");
		rangeBack.setOnAction(e -> window.setScene(reportSelect));
		Button rangeNext = new Button("Create Report");
		rangeNext.setOnAction(e -> displayDataRangeData(startYear.getText(), startDay.getText(), startMonth.getText(),
				endDay.getText(), endMonth.getText()));
		rangeReport = new Scene(dataRange, 400, 400);
		dataRange.getChildren().setAll(rangeReportLabel, startDate, startYear, startDay, startMonth, endDate, endDay,
				endMonth, rangeNext, rangeBack);

		// Set the primary stage
		window.setScene(fileSelect);
		window.show();
	}

	private void getReport() {
		if (this.reportBox.getValue() != null) {
			String report = this.reportBox.getValue().toString();
			switch (report) {
			case "Farm Report":
				window.setScene(farmReport);
				break;
			case "Annual Report":
				window.setScene(annualReport);
				break;
			case "Monthly Report":
				window.setScene(monthlyReport);
				break;
			case "Data Range Report":
				window.setScene(rangeReport);
				break;
			}
		}
	}

	/**
	 * Displays the data-previously from datafile
	 * 
	 * @param year- the year entered in from the user
	 * @param ID    - the ID entered in from the user
	 */
	public void displayFarmData(String year, String ID) {
		this.fileManager = new FileManager(files);
		DataFile datafile = new DataFile();
		fileManager.writeToFile(fileManager.getFactory().getFarms());

		ArrayList<String> farmReport = datafile.getFarmReport(ID, fileManager.getFactory().getFarms());
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

//		for (String x : farmReport) {
//			System.out.println(x);
//		}

		ObservableList<MilkWeightData> data = FXCollections.observableArrayList();
		data.add(new MilkWeightData("January", farmReport.get(1), farmReport.get(2)));
		data.add(new MilkWeightData("February", farmReport.get(3), farmReport.get(4)));
		data.add(new MilkWeightData("March", farmReport.get(5), farmReport.get(6)));
		data.add(new MilkWeightData("April", farmReport.get(7), farmReport.get(8)));
		data.add(new MilkWeightData("May", farmReport.get(9), farmReport.get(10)));
		data.add(new MilkWeightData("June", farmReport.get(11), farmReport.get(12)));
		data.add(new MilkWeightData("July", farmReport.get(13), farmReport.get(14)));
		data.add(new MilkWeightData("August", farmReport.get(15), farmReport.get(16)));
		data.add(new MilkWeightData("September", farmReport.get(17), farmReport.get(18)));
		data.add(new MilkWeightData("October", farmReport.get(19), farmReport.get(20)));
		data.add(new MilkWeightData("November", farmReport.get(21), farmReport.get(22)));
		data.add(new MilkWeightData("December", farmReport.get(23), farmReport.get(24)));

		TableColumn<MilkWeightData, String> monthColumn = new TableColumn<>("Month");
		monthColumn.setMinWidth(300);
		monthColumn.setCellValueFactory(new PropertyValueFactory<MilkWeightData, String>("month"));

		TableColumn<MilkWeightData, String> weightColumn = new TableColumn<>("Weight");
		weightColumn.setMinWidth(300);
		weightColumn.setCellValueFactory(new PropertyValueFactory<MilkWeightData, String>("weight"));

		TableColumn<MilkWeightData, String> percentageColumn = new TableColumn<>("Percentage");
		percentageColumn.setMinWidth(300);
		percentageColumn.setCellValueFactory(new PropertyValueFactory<MilkWeightData, String>("weightPercentage"));

		TableView table = new TableView<>();
		table.setItems(data);
		table.getColumns().addAll(monthColumn, weightColumn, percentageColumn);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(table);

		/*
		 * Label label = new Label("Data will be displayed here"); Button closeButton =
		 * new Button("Close Window"); closeButton.setOnAction(e -> window.close());
		 * VBox layout = new VBox(10); layout.getChildren().setAll(label, closeButton);
		 * layout.setAlignment(Pos.CENTER);
		 */
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.show();
	}

	public void displayEditData(String year, String ID) {
		this.fileManager = new FileManager(files);
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);
		// creates window
		ObservableList<EditData> data = FXCollections.observableArrayList();

		for (int i = 0; i < fileManager.getFactory().getFarms().size(); i++) {
			System.out.println(i);
		}
		// data.add(new EditData(fileManager.getFactory()))
	}

	/**
	 * Displays the data-previously from datafile
	 * 
	 * @param year- the year entered in from the user
	 */
	public void displayAnnualData(String year) {
		System.out.println(files.get(0));
		this.fileManager = new FileManager(files);
		DataFile datafile = new DataFile();
		int reportCounter = 1;

		ArrayList<String> farmReport = datafile.getAnnualReport(fileManager.getFactory().getFarms());
		Stage window = new Stage();
		window.initModality(Modality.APPLICATION_MODAL);

//		for (String x : farmReport) {
//			System.out.println(x);
//		}

		ObservableList<MilkWeightData> data = FXCollections.observableArrayList();
		for (int i = 0; i < fileManager.getFactory().getFarms().size(); i++) {
			data.add(new MilkWeightData(farmReport.get(reportCounter), farmReport.get(reportCounter + 1)));
			reportCounter = reportCounter + 2;
		}

		TableColumn<MilkWeightData, String> weightColumn = new TableColumn<>("Weight");
		weightColumn.setMinWidth(300);
		weightColumn.setCellValueFactory(new PropertyValueFactory<MilkWeightData, String>("weight"));

		TableColumn<MilkWeightData, String> percentageColumn = new TableColumn<>("Percentage");
		percentageColumn.setMinWidth(300);
		percentageColumn.setCellValueFactory(new PropertyValueFactory<MilkWeightData, String>("weightPercentage"));

		TableView table = new TableView<>();
		table.setItems(data);
		table.getColumns().addAll(weightColumn, percentageColumn);

		VBox vBox = new VBox();
		vBox.getChildren().addAll(table);
		/*
		 * Label label = new Label("Data will be displayed here"); Button closeButton =
		 * new Button("Close Window"); closeButton.setOnAction(e -> window.close());
		 * VBox layout = new VBox(10); layout.getChildren().setAll(label, closeButton);
		 * layout.setAlignment(Pos.CENTER);f
		 */
		Scene scene = new Scene(vBox);
		window.setScene(scene);
		window.showAndWait();
	}

	/**
	 * Displays the data-previously from datafile
	 * 
	 * @param year- the year entered in from the user
	 * @param month - the month entered in from the user
	 */
	public static void displayMonthlyData(String month, String year) {
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);

		Label label = new Label("Data will be displayed here");
		Button closeButton = new Button("Close Window");
		closeButton.setOnAction(e -> window.close());
		VBox layout = new VBox(10);
		layout.getChildren().setAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 300, 300);
		window.setScene(scene);
		window.showAndWait();
	}

	/**
	 * Displays the data-previously from datafile
	 * 
	 * @param year-      the year entered in from the user
	 * @param startDay   - the start day entered by the user
	 * @param startMonth - the start month entered in from the user
	 * @param endDay     - the end day entered by the user
	 * @param endMonth   - the end month entered by the user
	 */
	public static void displayDataRangeData(String year, String startDay, String startMonth, String endDay,
			String endMonth) {
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);

		Label label = new Label("Data will be displayed here");
		Button closeButton = new Button("Close Window");
		closeButton.setOnAction(e -> window.close());
		VBox layout = new VBox(10);
		layout.getChildren().setAll(label, closeButton);
		layout.setAlignment(Pos.CENTER);
		Scene scene = new Scene(layout, 300, 300);
		window.setScene(scene);
		window.showAndWait();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
	}

}