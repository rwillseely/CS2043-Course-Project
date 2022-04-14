import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.*;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import java.util.ArrayList;


public class Stagui extends Application  {
	
	public static void begin(){
		launch("Stagui");
	}
	
	@Override    
	public void start(Stage primaryStage) throws Exception { 
		
		
		VBox mainMenu = new VBox();
		mainMenu.setAlignment(Pos.CENTER);
		Scene mainScene = new Scene(mainMenu, 300, 300);
		Label mainLabel = new Label("Select an Option:");
		
		
		
		VBox output = new VBox();
		output.setAlignment(Pos.CENTER);
		Scene opScene = new Scene(output, 600, 700);
		Button backToMainop = new Button("Back");
		backToMainop.setOnAction(e -> primaryStage.setScene(mainScene));
		Text opLabel = new Text("Output Here");
		Button writeToFile = new Button("Write to File");
		writeToFile.setOnAction(e -> StaguiDriver.writeOutput());
		
		VBox inputFiles = new VBox();
		inputFiles.setAlignment(Pos.CENTER);
		Scene fileScene = new Scene(inputFiles, 300, 300);
		Label fileLabel = new Label("Please choose your input files in the following textfields\nIn order, specify course data, course list, and course equivalents\n(All .txt files without .txt extensions)");		
		TextField fileOne = new TextField();
		fileOne.setMaxWidth(100);
		TextField fileTwo = new TextField();
		fileTwo.setMaxWidth(100);
		TextField fileThree = new TextField();
		fileThree.setMaxWidth(100);
		Button submitFiles = new Button("Next");
		submitFiles.setOnAction(e -> {
			StaguiDriver.start(fileOne.getText(), fileTwo.getText(), fileThree.getText());
			primaryStage.setScene(mainScene);
		});
		
		
		
		
		VBox distributions = new VBox();
		distributions.setAlignment(Pos.CENTER);
		Scene distScene = new Scene(distributions, 300, 300);
		Button backToMaindist = new Button("Back");
		backToMaindist.setOnAction(e -> primaryStage.setScene(mainScene));
		Label distributionLabel = new Label("Choose a Distribution:");
		Button runRaw = new Button("Raw Distribution");
		runRaw.setOnAction(e -> {
			primaryStage.setScene(opScene);
			opLabel.setText(StaguiDriver.rawDist());
		});
		Button runRawCustom = new Button("Raw Distribution (custom)");
		runRawCustom.setOnAction(e -> {
			primaryStage.setScene(opScene);
			opLabel.setText(StaguiDriver.custRawDist());
		});
		Button runArea = new Button("Area Distribution");
		runArea.setOnAction(e -> {
			primaryStage.setScene(opScene);
			opLabel.setText(StaguiDriver.areaDist());
		});
		
		
		
		VBox customSchema = new VBox();
		customSchema.setAlignment(Pos.CENTER);
		Scene csScene = new Scene(customSchema, 300, 400);
		Button backToMaincs = new Button("Back");
		backToMaincs.setOnAction(e -> primaryStage.setScene(mainScene));
		Label csLabel = new Label("Create a Custom Schema:");
		Label csLabelInstructions = new Label("Use the smaller textfields to name the two levels\nmake space separated lists of accepted bounds\nor toggle the switch to start with a default base\n(will overwrite any existing custom schema)");
		TextField level1Name = new TextField();
		level1Name.setMaxWidth(100);
		TextField level1Bounds = new TextField();
		level1Bounds.setMaxWidth(300);
		TextField level2Name = new TextField();
		level2Name.setMaxWidth(100);
		TextField level2Bounds = new TextField();
		level2Bounds.setMaxWidth(300);
		Button defaultLevels = new Button("Input Default Levels");
		defaultLevels.setOnAction(e -> StaguiDriver.newDefCust());
		Button createCustom = new Button("Create Custom Schema");
		createCustom.setOnAction(e -> {
			StaguiDriver.newCust(
			level1Name.getText(), level1Bounds.getText(), 
			level2Name.getText(), level2Bounds.getText());
		});
		
		
		
		VBox addLevels = new VBox();
		addLevels.setAlignment(Pos.CENTER);
		Scene alScene = new Scene(addLevels, 300, 400);
		Button backToMainal = new Button("Back");
		backToMainal.setOnAction(e -> primaryStage.setScene(mainScene));
		Label alLabel = new Label("Enter level name and accepted bounds");
		TextField leveltoAdd = new TextField();
		leveltoAdd.setMaxWidth(100);
		TextField leveltoAddBounds = new TextField();
		leveltoAddBounds.setMaxWidth(300);
		Button addLevel = new Button("Add level");
		addLevel.setOnAction(e -> {
			StaguiDriver.addLevel(
			leveltoAdd.getText(), leveltoAddBounds.getText());
		});
		
		
		
		VBox editLevels = new VBox();
		editLevels.setAlignment(Pos.CENTER);
		Scene elScene = new Scene(editLevels, 300, 400);
		Button backToMainel = new Button("Back");
		backToMainel.setOnAction(e -> primaryStage.setScene(mainScene));
		Label elLabel = new Label("Edit Levels in Custom Schema");
		Label elLabelInstructions = new Label("Input the name of the level to be edited (top textfield)\nNext, enter either the level's new name or new bounds\nuse the corresponding button");
		TextField leveltoEdit = new TextField();
		leveltoEdit.setMaxWidth(100);
		TextField leveltoEditNewName = new TextField();
		leveltoEditNewName.setMaxWidth(100);
		TextField leveltoEditNewBounds = new TextField();
		leveltoEditNewBounds.setMaxWidth(300);
		Button deleteLevel = new Button("Delete Level");
		deleteLevel.setOnAction(e -> {
			StaguiDriver.deleteLevel(leveltoEdit.getText());
		});
		Button setName = new Button("New Name");
		setName.setOnAction(e -> {
			StaguiDriver.renameLevel(leveltoEdit.getText(),
			leveltoEditNewName.getText());
		});
		Button setBounds = new Button("New Bounds");
		setBounds.setOnAction(e -> {
			StaguiDriver.setLevelBounds(leveltoEdit.getText(),
			leveltoEditNewBounds.getText());
		});
		
		
		VBox studentTranscript = new VBox();
		studentTranscript.setAlignment(Pos.CENTER);
		Scene stScene = new Scene(studentTranscript, 300, 300);
		Button backToMainst = new Button("Back");
		backToMainst.setOnAction(e -> primaryStage.setScene(mainScene));
		Label stLabel = new Label("Enter a student's ID");
		TextField studentToGet = new TextField();
		studentToGet.setMaxWidth(100);
		Button getTranscript = new Button("Get Transcript");
		getTranscript.setOnAction(e -> {
			primaryStage.setScene(opScene);
			opLabel.setText(StaguiDriver.getStudentTranscript(
			Integer.valueOf(studentToGet.getText())));
		});
		
		
		
		VBox changeWriteFile = new VBox();
		changeWriteFile.setAlignment(Pos.CENTER);
		Scene wfScene = new Scene(changeWriteFile, 300, 200);
		Button backToMainwf = new Button("Back");
		backToMainwf.setOnAction(e -> primaryStage.setScene(mainScene));
		Label wfLabel = new Label("Enter file name to write to");
		TextField newWriteFile = new TextField();
		newWriteFile.setMaxWidth(100);
		Button changeFile = new Button("Set File");
		changeFile.setOnAction(e -> {
			StaguiDriver.changeOutputFile(newWriteFile.getText());
		});
		
		Button toDistribution = new Button("Run Distributions");
		toDistribution.setOnAction(e -> primaryStage.setScene(distScene));
		Button toCSCreate = new Button("Create Custom Schema");
		toCSCreate.setOnAction(e -> primaryStage.setScene(csScene));
		Button toAddLevels = new Button("Add Levels");
		toAddLevels.setOnAction(e -> primaryStage.setScene(alScene));
		Button toEditLevels = new Button("Edit Levels");
		toEditLevels.setOnAction(e -> primaryStage.setScene(elScene));
		Button toStudentTranscript = new Button("Student Transcript");
		toStudentTranscript.setOnAction(e -> primaryStage.setScene(stScene));
		Button toChangeWriteFile = new Button("Change Output File");
		toChangeWriteFile.setOnAction(e -> primaryStage.setScene(wfScene));
		
		
		inputFiles.getChildren().addAll(fileLabel, fileOne, fileTwo, fileThree, submitFiles);
		mainMenu.getChildren().addAll(mainLabel, toDistribution, toCSCreate, toAddLevels, toEditLevels, toStudentTranscript, toChangeWriteFile);
		distributions.getChildren().addAll(distributionLabel, runRaw, runRawCustom, runArea, backToMaindist);
		customSchema.getChildren().addAll(csLabel, csLabelInstructions, defaultLevels, level1Name, level1Bounds, level2Name, level2Bounds, createCustom, backToMaincs);
		addLevels.getChildren().addAll(alLabel, leveltoAdd, leveltoAddBounds, addLevel, backToMainal);
		editLevels.getChildren().addAll(elLabel, elLabelInstructions, leveltoEdit, deleteLevel, leveltoEditNewName, setName, leveltoEditNewBounds, setBounds, backToMainel);
		studentTranscript.getChildren().addAll(stLabel, studentToGet, getTranscript, backToMainst);
		output.getChildren().addAll(backToMainop, writeToFile, opLabel);
		changeWriteFile.getChildren().addAll(wfLabel, newWriteFile, changeFile, backToMainwf);
		
		primaryStage.setTitle("Student Transcript Analizer");
		primaryStage.setScene(fileScene);   
		primaryStage.show();
    }
}