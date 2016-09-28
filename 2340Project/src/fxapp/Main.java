package fxapp;

import java.io.IOException;

import controller.Controller;
import controller.LoginController;
import controller.MainScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.layout.BorderPane;

public class Main extends Application {

	private Stage mainScreen;
	
    @Override
    public void start(Stage primaryStage) throws Exception{
    	mainScreen = primaryStage;
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/WelcomeWindow.fxml"));
     	Parent root = loader.load();
    	primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        primaryStage.setMaximized(true);

        
        Controller controller = loader.getController();
        controller.setMainApp(this);
    }

    public void showLoginDialog() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("../view/LoginDialog.fxml"));
    		Pane page = loader.load();
    		
    		Stage dialogStage = new Stage();
    		dialogStage.setTitle("Login");
    		dialogStage.initModality(Modality.WINDOW_MODAL);
    		dialogStage.initOwner(mainScreen);
    		Scene scene = new Scene(page);
    		dialogStage.setScene(scene);
    		
    		LoginController controller = loader.getController();
    		controller.setDialogStage(dialogStage);
    		controller.setMainApp(this);

    		dialogStage.showAndWait();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void showRegisterDialog() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("../view/RegisterDialog.fxml"));
    		Pane page = loader.load();
    		
    		Stage dialogStage = new Stage();
    		dialogStage.setTitle("Register");
    		dialogStage.initModality(Modality.WINDOW_MODAL);
    		dialogStage.initOwner(mainScreen);
    		Scene scene = new Scene(page);
    		dialogStage.setScene(scene);
    		
    		LoginController controller = loader.getController();
    		controller.setDialogStage(dialogStage);
    		controller.setMainApp(this);

    		dialogStage.showAndWait();
    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void showMainScreen() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("../view/MainScreen.fxml"));

         	Parent root = loader.load();
        	mainScreen.setTitle("Hello World");
        	mainScreen.setScene(new Scene(root, 300, 275));
        	mainScreen.show();

    		MainScreenController controller = loader.getController();
    		controller.setMainApp(this);

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public void showWelcomeScreen() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("../view/WelcomeWindow.fxml"));

         	Parent root = loader.load();
        	mainScreen.setTitle("Hello World");
        	mainScreen.setScene(new Scene(root, 300, 275));
        	mainScreen.show();

    		Controller controller = loader.getController();
    		controller.setMainApp(this);

    	} catch (IOException e) {
    		e.printStackTrace();
    	}
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
