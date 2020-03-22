import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.image.Image;

public class OS141gui extends Application {
	// Main Frame
	VBox root = new VBox();

	// Components
	Button start = new Button("Start");
	Slider slider = new Slider();

	// Tabs
	TabPane tabs = new TabPane();

	// Users Tab
	Tab users = new Tab("Users");
	AnchorPane userPane = new AnchorPane();
	GridPane userGP = new GridPane();

	// User 1
	ImageView user1Image = new ImageView();
	TextArea user1TA = new TextArea();
	// User 2
	ImageView user2Image = new ImageView();
	TextArea user2TA = new TextArea();
	// User 3
	ImageView user3Image = new ImageView();
	TextArea user3TA = new TextArea();

	// Disks Tab
	Tab disks = new Tab("Disks");
	AnchorPane diskPane = new AnchorPane();
	GridPane diskGP = new GridPane();

	// Disk 1
	ImageView disk1Image = new ImageView();
	TextArea disk1TA = new TextArea();

	// Disk 2
	ImageView disk2Image = new ImageView();
	TextArea disk2TA = new TextArea();

	// Printers Tab
	Tab printers = new Tab("Printers");
	AnchorPane printerPane = new AnchorPane();
	GridPane printerGP = new GridPane();

	TextArea printer1TA = new TextArea();
	TextArea printer2TA = new TextArea();
	TextArea printer3TA = new TextArea();

	public void start(Stage primaryStage) {
		// set up main frame
		root.setPrefSize(600, 400);

		// Options Grid Pane
		GridPane options = new GridPane();
		options.setPrefSize(600, 20);
		ColumnConstraints optionsCol = new ColumnConstraints();
		RowConstraints optionsRow = new RowConstraints();
		optionsCol.setPrefWidth(200);
		optionsRow.setPrefHeight(30);
		options.getRowConstraints().add(optionsRow);

		// Start Button Settings
		start.setStyle("-fx-font-size:9");
		start.setDefaultButton(true);
		start.setContentDisplay(ContentDisplay.CENTER);
		start.setAlignment(Pos.CENTER);
		start.setPrefSize(50, 15);
		start.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
		options.add(start, 0, 0);
		options.setMargin(start, new Insets(5, 5, 5, 5));
		options.setValignment(start, VPos.CENTER);
		options.getColumnConstraints().add(optionsCol);

		// Slider
		Label sliderLabel = new Label("Speed Control: ");
		sliderLabel.setStyle("-fx-font-size:10");
		sliderLabel.setPadding(new Insets(5, 0, 5, 0));
		options.add(sliderLabel, 1, 0);
		options.setHalignment(sliderLabel, HPos.RIGHT);

		slider.setPrefSize(200, 20);
		slider.setValue(50);
		slider.snapToTicksProperty().set(true);
		slider.setPadding(new Insets(5, 0, 5, 0));
		options.add(slider, 2, 0);
		options.setHalignment(slider, HPos.CENTER);
		options.getColumnConstraints().add(optionsCol);

		// Tab Bar
		tabs.setPrefSize(600, 400);

		// Generally used insets
		Insets insets = new Insets(5, 5, 5, 5);

		// Users Tab
		users.setClosable(false);

		userPane.setPrefSize(200, 180);
		users.setContent(userPane);
		tabs.getTabs().add(users);

		userGP.setPrefSize(600, 350);
		userGP.gridLinesVisibleProperty().set(true);
		userPane.getChildren().add(userGP);

		// row/col constraints for user grid pane
		ColumnConstraints userCol = new ColumnConstraints();
		RowConstraints userRow = new RowConstraints();
		userRow.setPrefHeight(350);
		userCol.setPrefWidth(200);
		userGP.getRowConstraints().add(userRow);

		// User 1
		Label user1 = new Label("USER 1");
		user1.paddingProperty().set(insets);
		userGP.add(user1, 0, 0);
		userGP.setHalignment(user1, HPos.LEFT);
		userGP.setValignment(user1, VPos.TOP);
		userGP.getColumnConstraints().add(userCol);

		// User 1 icon
		Image userImage = new Image(getClass().getResource("images/user.png").toExternalForm());
		user1Image.setImage(userImage);
		user1Image.setPreserveRatio(true);
		user1Image.setFitHeight(120);
		user1Image.setFitWidth(120);

		userGP.add(user1Image, 0, 0);
		userGP.setHalignment(user1Image, HPos.CENTER);
		userGP.setValignment(user1Image, VPos.TOP);
		userGP.setMargin(user1Image, new Insets(20));

		// User 1 text area
		user1TA.setMaxSize(175, 200);
		user1TA.setEditable(false);

		userGP.add(user1TA, 0, 0);
		userGP.setHalignment(user1TA, HPos.CENTER);
		userGP.setValignment(user1TA, VPos.BOTTOM);
		userGP.setMargin(user1TA, new Insets(15));

		// User 2
		Label user2 = new Label("USER 2");
		user2.paddingProperty().set(insets);
		userGP.add(user2, 1, 0);
		userGP.setHalignment(user2, HPos.LEFT);
		userGP.setValignment(user2, VPos.TOP);
		userGP.getColumnConstraints().add(userCol);

		// User 2 icon
		user2Image.setImage(userImage);
		user2Image.setPreserveRatio(true);
		user2Image.setFitHeight(120);
		user2Image.setFitWidth(120);

		userGP.add(user2Image, 1, 0);
		userGP.setHalignment(user2Image, HPos.CENTER);
		userGP.setValignment(user2Image, VPos.TOP);
		userGP.setMargin(user2Image, new Insets(20));

		// User 2 text area
		user2TA.setMaxSize(175, 200);
		user2TA.setEditable(false);

		userGP.add(user2TA, 1, 0);
		userGP.setHalignment(user2TA, HPos.CENTER);
		userGP.setValignment(user2TA, VPos.BOTTOM);
		userGP.setMargin(user2TA, new Insets(15));

		// User 3
		Label user3 = new Label("USER 3");
		user3.paddingProperty().set(insets);
		userGP.add(user3, 2, 0);
		userGP.setHalignment(user3, HPos.LEFT);
		userGP.setValignment(user3, VPos.TOP);
		userGP.getColumnConstraints().add(userCol);

		// User 3 icon
		user3Image.setImage(userImage);
		user3Image.setPreserveRatio(true);
		user3Image.setFitHeight(120);
		user3Image.setFitWidth(120);

		userGP.add(user3Image, 2, 0);
		userGP.setHalignment(user3Image, HPos.CENTER);
		userGP.setValignment(user3Image, VPos.TOP);
		userGP.setMargin(user3Image, new Insets(20));

		// User 3 text area
		user3TA.setMaxSize(175, 200);
		user3TA.setEditable(false);

		userGP.add(user3TA, 2, 0);
		userGP.setHalignment(user3TA, HPos.CENTER);
		userGP.setValignment(user3TA, VPos.BOTTOM);
		userGP.setMargin(user3TA, new Insets(15));

		// Disks Tab
		disks.setClosable(false);

		// root of disks tab
		diskPane.setPrefSize(200, 180);
		disks.setContent(diskPane);
		tabs.getTabs().add(disks);

		// gridpane within disks tab
		diskGP.setPrefSize(600, 350);
		diskGP.gridLinesVisibleProperty().set(true);
		diskPane.getChildren().add(diskGP);

		// row/col constraints for disk grid pane
		ColumnConstraints diskCol = new ColumnConstraints();
		RowConstraints diskRow = new RowConstraints();
		diskRow.setPrefHeight(172.5);
		diskCol.setPrefWidth(600);
		diskGP.getColumnConstraints().add(diskCol);

		// Disk 1
		Label disk1 = new Label("DISK 1");
		disk1.paddingProperty().set(insets);
		diskGP.add(disk1, 0, 0);
		diskGP.setHalignment(disk1, HPos.LEFT);
		diskGP.setValignment(disk1, VPos.TOP);
		diskGP.getRowConstraints().add(diskRow);

		// disk 1 icon
		Image diskImage = new Image(getClass().getResource("images/hard-disk.png").toExternalForm());
		disk1Image.setImage(diskImage);
		disk1Image.setPreserveRatio(true);
		disk1Image.setFitHeight(120);
		disk1Image.setFitWidth(120);

		diskGP.add(disk1Image, 0, 0);
		diskGP.setHalignment(disk1Image, HPos.LEFT);
		diskGP.setValignment(disk1Image, VPos.CENTER);

		// disk 1 text area
		disk1TA.setMaxSize(425, 150);
		disk1TA.setEditable(false);

		diskGP.add(disk1TA, 0, 0);
		diskGP.setHalignment(disk1TA, HPos.RIGHT);
		diskGP.setValignment(disk1TA, VPos.CENTER);
		diskGP.setMargin(disk1TA, new Insets(0, 35, 0, 0));

		// Disk 2
		Label disk2 = new Label("DISK 2");
		disk2.paddingProperty().set(insets);
		diskGP.add(disk2, 0, 1);
		diskGP.setHalignment(disk2, HPos.LEFT);
		diskGP.setValignment(disk2, VPos.TOP);
		diskGP.getRowConstraints().add(diskRow);

		// disk 2 icon
		disk2Image.setImage(diskImage);
		disk2Image.setPreserveRatio(true);
		disk2Image.setFitHeight(120);
		disk2Image.setFitWidth(120);

		diskGP.add(disk2Image, 0, 1);
		diskGP.setHalignment(disk2Image, HPos.LEFT);
		diskGP.setValignment(disk2Image, VPos.CENTER);

		// disk 2 text area
		disk2TA.setMaxSize(425, 150);
		disk2TA.setEditable(false);

		diskGP.add(disk2TA, 0, 1);
		diskGP.setHalignment(disk2TA, HPos.RIGHT);
		diskGP.setValignment(disk2TA, VPos.CENTER);
		diskGP.setMargin(disk2TA, new Insets(0, 35, 0, 0));

		// Printers Tab
		printers.setClosable(false);

		// root of printers tab
		printerPane.setPrefSize(200, 180);
		printers.setContent(printerPane);
		tabs.getTabs().add(printers);

		// gridpane within printers tab
		printerGP.setPrefSize(600, 350);
		printerGP.gridLinesVisibleProperty().set(true);
		printerPane.getChildren().add(printerGP);

		// row/col constraints for user grid pane
		ColumnConstraints printerCol = new ColumnConstraints();
		RowConstraints printerRow = new RowConstraints();
		printerRow.setPrefHeight(350);
		printerCol.setPrefWidth(200);
		printerGP.getRowConstraints().add(printerRow);

		// Printer 1
		Label printer1 = new Label("PRINTER 1");
		printer1.paddingProperty().set(insets);
		printerGP.add(printer1, 0, 0);
		printerGP.setHalignment(printer1, HPos.LEFT);
		printerGP.setValignment(printer1, VPos.TOP);
		printerGP.getColumnConstraints().add(printerCol);

		// printer 1 text area

		printer1TA.setMaxSize(175, 300);
		printer1TA.setEditable(false);

		printerGP.add(printer1TA, 0, 0);
		printerGP.setHalignment(printer1TA, HPos.CENTER);
		printerGP.setValignment(printer1TA, VPos.CENTER);
		printerGP.setMargin(printer1TA, new Insets(15, 0, 0, 0));

		// Printer 2
		Label printer2 = new Label("PRINTER 2");
		printer2.paddingProperty().set(insets);
		printerGP.add(printer2, 1, 0);
		printerGP.setHalignment(printer2, HPos.LEFT);
		printerGP.setValignment(printer2, VPos.TOP);
		printerGP.getColumnConstraints().add(userCol);

		// printer 2 text area
		printer2TA.setMaxSize(175, 300);
		printer2TA.setEditable(false);

		printerGP.add(printer2TA, 1, 0);
		printerGP.setHalignment(printer2TA, HPos.CENTER);
		printerGP.setValignment(printer2TA, VPos.CENTER);
		printerGP.setMargin(printer2TA, new Insets(15, 0, 0, 0));

		// Printer 3
		Label printer3 = new Label("PRINTER 3");
		printer3.paddingProperty().set(insets);
		printerGP.add(printer3, 2, 0);
		printerGP.setHalignment(printer3, HPos.LEFT);
		printerGP.setValignment(printer3, VPos.TOP);
		printerGP.getColumnConstraints().add(printerCol);

		// printer 2 text area
		printer3TA.setMaxSize(175, 300);
		printer3TA.setEditable(false);

		printerGP.add(printer3TA, 2, 0);
		printerGP.setHalignment(printer3TA, HPos.CENTER);
		printerGP.setValignment(printer3TA, VPos.CENTER);
		printerGP.setMargin(printer3TA, new Insets(15, 0, 0, 0));

		// set up functions
		start.setOnAction(b -> start());
		slider.valueProperty().addListener((observable, oldValue, newValue) -> getSliderValue());

		// add children to main frame
		root.getChildren().addAll(options, tabs);
		Scene scene = new Scene(root);
		primaryStage.setTitle("OS 141 - Alexis Lauren Vu (ALEXILV1)");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void start() {
		String input[] = {"-3", "USER1", "USER2", "USER3", "-2", "-3"};
		try {
			os = new OS141(input, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public double getSliderValue() {
		return slider.getValue();
	}

	protected OS141 os = null;
	public static void main(String[] args) {
//		try {
//			if (args[args.length - 1].equals("-ng"))
//				new OS141(args, null);
//			else
//				Application.launch(args);
//		}
//		catch (IOException e) {
//			e.printStackTrace();
//		}
		Application.launch(args);
	}
}
