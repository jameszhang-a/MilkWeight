package application;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;

/**
 * MyHandler - TODO Describe purpose of this user-defined type
 *
 */
public class MyHandler implements javafx.event.EventHandler<ActionEvent> {

	private Button button;
	// saves the files that the users picked
	private ArrayList<File> files;

	/**
	 * empty constructor
	 */
	public MyHandler() {
	}

	/**
	 * @param butt
	 */
	public MyHandler(Button butt) {
		this.button = butt;
	}

	/**
	 * handles what the import files button does, allows users to pick multiple
	 * files to import
	 */
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

	}

	/**
	 * Returns the list of files the used selected
	 * 
	 * @return
	 */
	public List<File> getFiles() {
		return files;
	}

}
