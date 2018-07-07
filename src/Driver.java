import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

public class Driver extends Application {

	AVLTree tree = new AVLTree();
	HashTable HTable = new HashTable();
	Scanner scan = null;
	String data = "";
	TextArea tx2 = new TextArea();
	TextArea tx3 = new TextArea();
	TextArea tx4 = new TextArea();

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Country’s Tourist Places");

		TabPane tb = new TabPane();
		tb.autosize();
		tb.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
		Tab tab0 = new Tab("Welcome");
		Tab tab1 = new Tab("Read");
		Tab tab2 = new Tab("AVLTree");
		Tab tab3 = new Tab("HashTable");
		tb.getTabs().addAll(tab0, tab1, tab2, tab3);

		Font font = new Font("Times New Roman", 15);
		Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 15);

		Label l0 = new Label("Welcome to my Software");
		l0.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 40));
		Label l00 = new Label("********************");
		l00.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));
		Label l000 = new Label("This Software was Designed by");
		l000.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));
		Label l0000 = new Label("Mohammed Odat Issa");
		l0000.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));
		Label l00000 = new Label("Birzeit University");
		l00000.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));
		Label l000000 = new Label("ID : 1162505");
		l000000.setFont(Font.font("Times New Roman", FontWeight.BOLD, FontPosture.ITALIC, 25));

		VBox v0 = new VBox(10, l0, l00, l000, l0000, l00000, l000000);
		v0.setAlignment(Pos.BASELINE_CENTER);
		v0.setPadding(new Insets(10));
		tab0.setContent(v0);

		Label l1 = new Label("  Browse a file  \n and read from it");
		l1.setFont(font);
		Label l2 = new Label("The output of the file");
		l2.setFont(font);
		TextArea tx1 = new TextArea();
		tx1.setEditable(false);

		Button browse = new Button("Browse");
		Button read = new Button("Read & Print");
		Button c1 = new Button("Clear");

		VBox v1 = new VBox(10, l1, browse, read, c1);
		v1.setAlignment(Pos.CENTER);
		v1.setPadding(new Insets(10));
		VBox v2 = new VBox(10, l2, tx1);
		v2.setAlignment(Pos.CENTER);
		v2.setPadding(new Insets(10));

		HBox h1 = new HBox(10, v1, v2);
		h1.setAlignment(Pos.CENTER);
		h1.setPadding(new Insets(10));

		browse.setOnAction(e -> {
			FileChooser chooser = new FileChooser();
			chooser.getExtensionFilters().addAll(new ExtensionFilter("txt files", "*.txt"));
			File in = chooser.showOpenDialog(null);

			if (in == null)
				try {
					throw new Exception("No file selected");
				} catch (Exception e2) {
					Alert a2 = new Alert(Alert.AlertType.ERROR);
					a2.setTitle("ERROR");
					a2.setHeaderText("No file Selected");
					a2.setContentText("Choose a file, Please!");
					a2.showAndWait();
				}

			try {
				scan = new Scanner(in);
			} catch (FileNotFoundException e1) {
				Alert a2 = new Alert(Alert.AlertType.ERROR);
				a2.setTitle("ERROR");
				a2.setHeaderText("No file Selected");
				a2.setContentText("Choose a file, Please!");
				a2.showAndWait();
			}

		});

		read.setOnAction(e -> {
			tx1.clear();

			if (scan == null || !scan.hasNext()) {
				Alert a2 = new Alert(Alert.AlertType.ERROR);
				a2.setTitle("ERROR");
				a2.setHeaderText("No file Selected or the file is empty");
				a2.setContentText("Choose a non-empty file, Please!");
				a2.showAndWait();
			} else {
				String line = null;
				String[] tokens;
				City city = null;
				Country c = null;
				while (scan.hasNextLine()) {
					line = scan.nextLine();
					tx1.appendText(line + "\n");
					tokens = line.split("\\*");

					int rank = Integer.parseInt(tokens[0]);
					String cty = tokens[1].toLowerCase();
					double num = Double.parseDouble(tokens[3]);
					String cntry = tokens[2].toLowerCase();

					if (tree.search(cntry)) {
						city = new City(rank, cty, num);
						c = tree.get(cntry);
						c.addCity(city);
					} else {
						city = new City(rank, cty, num);
						c = new Country(cntry);
						c.addCity(city);
						tree.insert(c);
					}
				}
			}
			// hashFill(avl.getRoot());
		});

		c1.setOnAction(e -> {
			tx1.clear();
		});

		tab1.setContent(h1);

		Label l3 = new Label("AVL Tree");
		l3.setFont(font);
		Label l4 = new Label("Countries Information");
		l4.setFont(font);
		tx2.setEditable(false);

		ComboBox<String> cb = new ComboBox<>();
		cb.getItems().add("Print out countries sorted");
		cb.getItems().add("Insert a country");
		cb.getItems().add("Insert a city");
		cb.getItems().add("Delete a country");
		cb.getItems().add("Search for a country");
		cb.getItems().add("Tree height");
		cb.getItems().add("Traverse AVl tree to Hash Table");
		cb.getSelectionModel().clearSelection();

		Button show = new Button("Show operatin");

		VBox v3 = new VBox(10, l3, cb, show);
		v3.setAlignment(Pos.CENTER);
		v3.setPadding(new Insets(10));

		Button print = new Button("Print countries");
		print.setOnAction(e -> {
			try {
				tx2.clear();
				inorder(tree.getRoot());
			} catch (Exception ex) {
				Alert a2 = new Alert(Alert.AlertType.ERROR);
				a2.setTitle("ERROR");
				a2.setHeaderText("No file Selected or the file is empty");
				a2.setContentText("Choose a non-empty file, Please!");
				a2.showAndWait();
			}
		});

		Button c2 = new Button("Clear");
		c2.setOnAction(e -> {
			tx2.clear();
		});

		VBox v4 = new VBox(10, l4, print, tx2, c2);
		v4.setAlignment(Pos.CENTER);
		v4.setPadding(new Insets(10));

		Label l5 = new Label("Insert a new country");
		Button insert = new Button("Insert country");
		TextField tf1 = new TextField();
		VBox v5 = new VBox(10, l5, insert, tf1);
		v5.setAlignment(Pos.CENTER);
		v5.setPadding(new Insets(10));
		insert.setOnAction(e -> {
			String key = tf1.getText();

			if (tree.search(key)) {
				Alert a2 = new Alert(Alert.AlertType.ERROR);
				a2.setTitle("ERROR");
				a2.setHeaderText("Already Exists!!");
				a2.setContentText("This Country is already exists !!");
				a2.showAndWait();

			} else {
				Country c = new Country(key);
				tree.insert(c);

				Alert a2 = new Alert(Alert.AlertType.INFORMATION);
				a2.setTitle("Insertion");
				a2.setHeaderText("Succesfully Inserted");
				a2.setContentText("This Country had inserted succesfully..");
				a2.showAndWait();
			}
		});

		Label l6 = new Label("Insert a City");
		Button insertCity = new Button("Insert City");
		VBox v6 = new VBox(10, l6, insertCity);
		v6.setAlignment(Pos.CENTER);
		v6.setPadding(new Insets(10));
		insertCity.setOnAction(e -> {
			TextInputDialog td = new TextInputDialog();
			td.setContentText("Enter the country you wish to add a city to");
			Optional<String> result = td.showAndWait();
			String key = result.get();

			if (!tree.search(key)) {
				Alert a = new Alert(AlertType.WARNING);
				a.setContentText(key + " does not exist");
				a.showAndWait();
			} else {
				TextInputDialog tc = new TextInputDialog();
				tc.setContentText("Enter city name");
				Optional<String> resCity = tc.showAndWait();
				String ci = resCity.get();

				TextInputDialog tp = new TextInputDialog();
				tp.setContentText("Enter population of " + ci);
				Optional<String> resPop = tp.showAndWait();
				String pop = resPop.get();
				Double p = Double.parseDouble(pop);

				TextInputDialog tr = new TextInputDialog();
				tr.setContentText("Enter rank of " + ci);
				Optional<String> resRank = tr.showAndWait();
				String rank = resRank.get();
				Integer r = Integer.parseInt(rank);

				City city = new City(r, ci, p);
				Country c = tree.get(key);
				c.addCity(city);

				Alert a2 = new Alert(Alert.AlertType.INFORMATION);
				a2.setTitle("Insertion");
				a2.setHeaderText("Succesfully Inserted");
				a2.setContentText("This city have been inserted succesfully");
				a2.showAndWait();

			}
		});

		Label l7 = new Label("Delete a country");
		Button delete = new Button("delete country");
		TextField tf2 = new TextField();
		VBox v7 = new VBox(10, l7, delete, tf2);
		v7.setAlignment(Pos.CENTER);
		v7.setPadding(new Insets(10));
		delete.setOnAction(e -> {
			String key = tf2.getText();

			if (tree.search(key)) {
				tree.remove(key);
				Alert a2 = new Alert(Alert.AlertType.INFORMATION);
				a2.setTitle("Delete Country");
				a2.setHeaderText("Succesfully Deleted");
				a2.setContentText("This Country had Deleted succesfully..");
				a2.showAndWait();

			} else {
				Alert a2 = new Alert(Alert.AlertType.ERROR);
				a2.setTitle("ERROR");
				a2.setHeaderText("Invalid Country name !!");
				a2.setContentText("Enter an existing country , please!!");
				a2.showAndWait();
			}
		});

		Label l8 = new Label("Search for a country");
		Button search = new Button("Search country");
		TextField tf4 = new TextField();
		tx3.setEditable(false);
		VBox v8 = new VBox(10, l8, tf4, search, tx3);
		v8.setAlignment(Pos.CENTER);
		v8.setPadding(new Insets(10));

		search.setOnAction(e -> {
			String key = tf4.getText();

			if (tree.search(key)) {
				tx3.clear();
				Country c = tree.get(key);
				tx3.setText(
						c.getName() + "\n" + " number of cities: " + c.getCities().Size() + "\n" + " cities: {" + "\n");
				printSearch(c.getCities().getFront());
				tx3.appendText(" }" + "\n");

			} else {
				Alert a2 = new Alert(Alert.AlertType.ERROR);
				a2.setTitle("ERROR");
				a2.setHeaderText("Invalid Country name !!");
				a2.setContentText("Enter an existing country , please!!");
				a2.showAndWait();
			}

		});

		Label l9 = new Label("Print Tree Height");
		Button height = new Button("Tree Height");
		TextField tf5 = new TextField();
		VBox v9 = new VBox(10, l9, height, tf5);
		v9.setAlignment(Pos.CENTER);
		v9.setPadding(new Insets(10));
		height.setOnAction(e -> {
			tf5.clear();
			tf5.setText("AVL Tree Height is : " + tree.calculateHeight());
		});

		Label l10 = new Label("Traverse AVl tree to Hash Table");
		Button traverse = new Button("Traverse to Hash Table");
		VBox v10 = new VBox(10, l10, traverse);
		v10.setAlignment(Pos.CENTER);
		v10.setPadding(new Insets(10));
		traverse.setOnAction(e -> {
			traverseHash(tree.getRoot());
		});

		HBox h2 = new HBox(10, v3);
		h2.setAlignment(Pos.CENTER);
		h2.setPadding(new Insets(10));

		show.setOnAction(e -> {
			if (cb.getValue() == "Print out countries sorted") {
				h2.getChildren().add(v4);
				h2.getChildren().remove(v5);
				h2.getChildren().remove(v6);
				h2.getChildren().remove(v7);
				h2.getChildren().remove(v8);
				h2.getChildren().remove(v9);
				h2.getChildren().remove(v10);
			}

			else if (cb.getValue() == "Insert a country") {
				h2.getChildren().remove(v4);
				h2.getChildren().add(v5);
				h2.getChildren().remove(v6);
				h2.getChildren().remove(v7);
				h2.getChildren().remove(v8);
				h2.getChildren().remove(v9);
				h2.getChildren().remove(v10);

			}

			else if (cb.getValue() == "Insert a city") {
				h2.getChildren().remove(v4);
				h2.getChildren().remove(v5);
				h2.getChildren().add(v6);
				h2.getChildren().remove(v7);
				h2.getChildren().remove(v8);
				h2.getChildren().remove(v9);
				h2.getChildren().remove(v10);

			}

			else if (cb.getValue() == "Delete a country") {
				h2.getChildren().remove(v4);
				h2.getChildren().remove(v5);
				h2.getChildren().remove(v6);
				h2.getChildren().add(v7);
				h2.getChildren().remove(v8);
				h2.getChildren().remove(v9);
				h2.getChildren().remove(v10);

			}

			else if (cb.getValue() == "Search for a country") {
				h2.getChildren().remove(v4);
				h2.getChildren().remove(v5);
				h2.getChildren().remove(v6);
				h2.getChildren().remove(v7);
				h2.getChildren().add(v8);
				h2.getChildren().remove(v9);
				h2.getChildren().remove(v10);

			}

			else if (cb.getValue() == "Tree height") {
				h2.getChildren().remove(v4);
				h2.getChildren().remove(v5);
				h2.getChildren().remove(v6);
				h2.getChildren().remove(v7);
				h2.getChildren().remove(v8);
				h2.getChildren().add(v9);
				h2.getChildren().remove(v10);

			}

			else if (cb.getValue() == "Traverse AVl tree to Hash Table") {
				h2.getChildren().remove(v4);
				h2.getChildren().remove(v5);
				h2.getChildren().remove(v6);
				h2.getChildren().remove(v7);
				h2.getChildren().remove(v8);
				h2.getChildren().remove(v9);
				h2.getChildren().add(v10);

			}
		});

		tab2.setContent(h2);

		Label l11 = new Label("Hash Table");
		l11.setFont(font);
		Label l12 = new Label("Hash Table Information");
		l12.setFont(font);
		tx4.setEditable(false);

		ComboBox<String> cb2 = new ComboBox<>();
		cb2.getItems().add("Print Hash Table");
		cb2.getItems().add("Print Table Size");
		cb2.getItems().add("Print used Hash function");
		cb2.getItems().add("Insert a country");
		cb2.getItems().add("Delete a country");
		cb2.getItems().add("Search for a country");
		cb2.getItems().add("Save Hast Table to file");
		cb2.getSelectionModel().clearSelection();

		Button show2 = new Button("Show operatin");

		VBox v11 = new VBox(10, l11, cb2, show2);
		v11.setAlignment(Pos.CENTER);
		v11.setPadding(new Insets(10));

		Button print2 = new Button("Print Hash Table");
		VBox v12 = new VBox(10, l12, print2, tx4);
		v12.setAlignment(Pos.CENTER);
		v12.setPadding(new Insets(10));

		print2.setOnAction(e -> {
			tx4.clear();
			for (int i = 1; i < HTable.getSIZE(); i++) {
				if (HTable.atIndex(i) == null)
					tx4.appendText(i + ".) null\n");
				else
					tx4.appendText(i + ".) " + HTable.atIndex(i).getCountryName() + "\n");
			}
		});

		Label l13 = new Label("Hash Table Size");
		l13.setFont(font);
		Button size = new Button("Print Size");
		TextArea tx5 = new TextArea();
		VBox v13 = new VBox(10, l13, size, tx5);
		v13.setAlignment(Pos.CENTER);
		v13.setPadding(new Insets(10));
		size.setOnAction(e -> {
			tx5.clear();
			tx5.setText("Hash Table Size is : " + HTable.getSIZE() + "\n" + "Used Size = " + HTable.getCounter());
		});

		Label l14 = new Label("Hash Table used function");
		l14.setFont(font);
		Button function = new Button("Print function");
		TextArea tx6 = new TextArea();
		VBox v14 = new VBox(10, l14, function, tx6);
		v14.setAlignment(Pos.CENTER);
		v14.setPadding(new Insets(10));
		function.setOnAction(e -> {
			tx6.clear();
			tx6.setText("I am using Quadratc hashing : " + " h = (h + i * i) % SIZE \n\n"
					+ "	public int hash(String key) {\r\n" + "		int k = key.length();\r\n"
					+ "		int sum = 0;\r\n" + "		for (int i = 0; i < k - 1; i++) {\r\n"
					+ "			sum += key.charAt(i) << 5;\r\n" + "		}\r\n" + "		sum = sum % SIZE;\r\n" + "\r\n"
					+ "		if (sum < 0) {\r\n" + "			sum += SIZE;\r\n" + "		}\r\n" + "		return sum;\r\n"
					+ "	}");
		});

		Label l15 = new Label("Insert a country");
		l15.setFont(font);
		Button Insert = new Button("Insert");
		VBox v15 = new VBox(10, l15, Insert);
		v15.setAlignment(Pos.CENTER);
		v15.setPadding(new Insets(10));
		Insert.setOnAction(e -> {
			TextInputDialog tc = new TextInputDialog();
			tc.setContentText("Enter country name");
			Optional<String> resC = tc.showAndWait();
			String co = resC.get();

			TextInputDialog tr = new TextInputDialog();
			tr.setContentText("Enter number of cities in " + co);
			Optional<String> resCities = tr.showAndWait();
			String cities = resCities.get();
			Integer ci = Integer.parseInt(cities);

			TextInputDialog tp = new TextInputDialog();
			tp.setContentText("Enter population of " + co);
			Optional<String> resPop = tp.showAndWait();
			String pop = resPop.get();
			Double p = Double.parseDouble(pop);

			Entry en = HTable.get(co);
			if (en == null) {
				HTable.put(co, new Entry(co, ci, p));
				Alert a2 = new Alert(Alert.AlertType.INFORMATION);
				a2.setTitle("Insertion");
				a2.setHeaderText("Succesfully Inserted");
				a2.setContentText("This Country had inserted succesfully");
				a2.showAndWait();
			} else {
				Alert a2 = new Alert(Alert.AlertType.ERROR);
				a2.setTitle("ERROR");
				a2.setHeaderText("Already Exists!!");
				a2.setContentText("This Country is already exists !!");
				a2.showAndWait();

			}

		});

		Label l16 = new Label("Delete a country");
		l16.setFont(font);
		Button Delete = new Button("Delete");
		VBox v16 = new VBox(10, l16, Delete);
		v16.setAlignment(Pos.CENTER);
		v16.setPadding(new Insets(10));
		Delete.setOnAction(e -> {
			TextInputDialog tc = new TextInputDialog();
			tc.setContentText("Enter country to delete it");
			Optional<String> resC = tc.showAndWait();
			String co = resC.get();

			Entry en = HTable.get(co);
			if (en != null) {
				HTable.remove(co);
				Alert a2 = new Alert(Alert.AlertType.INFORMATION);
				a2.setTitle("Hash  Entry Delete");
				a2.setHeaderText("Succesfully Deleted");
				a2.setContentText("This Country had Deleted succesfully");
				a2.showAndWait();
			} else {
				Alert a2 = new Alert(Alert.AlertType.ERROR);
				a2.setTitle("ERROR");
				a2.setHeaderText("Invalid Country name !!");
				a2.setContentText("Insert The full name, please!!");
				a2.showAndWait();
			}
		});

		Label l17 = new Label("Search for a country");
		l17.setFont(font);
		Button Search = new Button("Search");
		TextArea tx7 = new TextArea();
		VBox v17 = new VBox(10, l17, Search, tx7);
		v17.setAlignment(Pos.CENTER);
		v17.setPadding(new Insets(10));
		Search.setOnAction(e -> {
			TextInputDialog tc = new TextInputDialog();
			tc.setContentText("Enter country to search for it");
			Optional<String> resC = tc.showAndWait();
			String co = resC.get();

			Entry en = HTable.get(co);
			if (en != null) {
				tx7.clear();
				tx7.setText(en.getCountryName() + "\n" + " number of cities : " + en.getNumOfCities() + "\n"
						+ " Total Number Of Tourists : " + en.getTotalTouristsNum());
			} else {
				Alert a2 = new Alert(Alert.AlertType.ERROR);
				a2.setTitle("ERROR");
				a2.setHeaderText("Invalid Country name !!");
				a2.setContentText("Insert The full name, please!!");
				a2.showAndWait();
			}
		});

		Label l18 = new Label("Save Hash Table to file");
		l18.setFont(font);
		Button Save = new Button("Save");
		Button exit = new Button("Exit");
		VBox v18 = new VBox(10, l18, Save, exit);
		v18.setAlignment(Pos.CENTER);
		v18.setPadding(new Insets(10));
		Save.setOnAction(e -> {
			File file = new File("Hash.txt");
			PrintWriter printer = null;
			try {
				printer = new PrintWriter(file);

				for (int i = 1; i < HTable.getSIZE(); i++) {
					if (HTable.atIndex(i) == null)
						printer.println(i + ".) null");
					else
						printer.println(i + ".) " + HTable.atIndex(i).getCountryName());
				}
			} catch (FileNotFoundException e1) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Wrong File");
				alert.setHeaderText("Error:");
				alert.setContentText(e1.getMessage());
				alert.showAndWait();
			}

			printer.close();

			Alert a2 = new Alert(Alert.AlertType.ERROR);
			a2.setTitle("Saved");
			a2.setHeaderText("You have saved the Hash Table to the file");
			a2.setContentText("<<<  Done  >>>");
			a2.showAndWait();
		});

		exit.setOnAction(e -> {
			System.exit(0);
		});

		HBox h3 = new HBox(10, v11);
		h3.setAlignment(Pos.CENTER);
		h3.setPadding(new Insets(10));

		show2.setOnAction(e -> {
			if (cb2.getValue() == "Print Hash Table") {
				h3.getChildren().add(v12);
				h3.getChildren().remove(v13);
				h3.getChildren().remove(v14);
				h3.getChildren().remove(v15);
				h3.getChildren().remove(v16);
				h3.getChildren().remove(v17);
				h3.getChildren().remove(v18);
			}

			else if (cb2.getValue() == "Print Table Size") {
				h3.getChildren().remove(v12);
				h3.getChildren().add(v13);
				h3.getChildren().remove(v14);
				h3.getChildren().remove(v15);
				h3.getChildren().remove(v16);
				h3.getChildren().remove(v17);
				h3.getChildren().remove(v18);
			}

			else if (cb2.getValue() == "Print used Hash function") {
				h3.getChildren().remove(v12);
				h3.getChildren().remove(v13);
				h3.getChildren().add(v14);
				h3.getChildren().remove(v15);
				h3.getChildren().remove(v16);
				h3.getChildren().remove(v17);
				h3.getChildren().remove(v18);
			}

			else if (cb2.getValue() == "Insert a country") {
				h3.getChildren().remove(v12);
				h3.getChildren().remove(v13);
				h3.getChildren().remove(v14);
				h3.getChildren().add(v15);
				h3.getChildren().remove(v16);
				h3.getChildren().remove(v17);
				h3.getChildren().remove(v18);
			}

			else if (cb2.getValue() == "Delete a country") {
				h3.getChildren().remove(v12);
				h3.getChildren().remove(v13);
				h3.getChildren().remove(v14);
				h3.getChildren().remove(v15);
				h3.getChildren().add(v16);
				h3.getChildren().remove(v17);
				h3.getChildren().remove(v18);
			}

			else if (cb2.getValue() == "Search for a country") {
				h3.getChildren().remove(v12);
				h3.getChildren().remove(v13);
				h3.getChildren().remove(v14);
				h3.getChildren().remove(v15);
				h3.getChildren().remove(v16);
				h3.getChildren().add(v17);
				h3.getChildren().remove(v18);
			}

			else if (cb2.getValue() == "Save Hast Table to file") {
				h3.getChildren().remove(v12);
				h3.getChildren().remove(v13);
				h3.getChildren().remove(v14);
				h3.getChildren().remove(v15);
				h3.getChildren().remove(v16);
				h3.getChildren().remove(v17);
				h3.getChildren().add(v18);
			}
		});

		tab3.setContent(h3);

		Scene scene = new Scene(tb, 700, 350);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private void inorder(AVLTreeNode r) {
		if (r != null) {
			inorder(r.getLeft());
			tx2.appendText(r.getElement().getName() + "( " + "\n");
			printList(r.getElement().getCities().getFront());
			tx2.appendText(" )" + "\n");
			inorder(r.getRight());
		}
	}

	private void printList(Node current) {

		if (current != null) {
			tx2.appendText(" " + current.getElement().getCityN() + "\n");
			printList(current.getNext());
		}

	}

	private void printSearch(Node current) {

		if (current != null) {
			tx3.appendText(" " + current.getElement().getCityN() + "\n");
			printSearch(current.getNext());
		}

	}

	private void traverseHash(AVLTreeNode t) {
		if (t != null) {
			traverseHash(t.getLeft());
			Entry e = new Entry(t.getElement().getName(), t.getElement().getCities().Size(),
					t.getElement().getTotalTourists());
			HTable.put(t.getElement().getName(), e);
			traverseHash(t.getRight());
		}
	}

	public static void main(String[] args) {
		launch(args);
	}

}
