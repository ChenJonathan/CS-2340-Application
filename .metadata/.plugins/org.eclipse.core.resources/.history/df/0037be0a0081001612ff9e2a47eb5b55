package sample;

import java.io.IOException;

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
        loader.setLocation(Main.class.getResource("WelcomeWindow.fxml"));
        
    	//Parent root = FXMLLoader.load(getClass().getResource("WelcomeWindow.fxml"));
    	Parent root = loader.load();
    	primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        
        Controller controller = loader.getController();
        controller.setMainApp(this);
    }

    public boolean showLoginDialog() {
    	try {
    		FXMLLoader loader = new FXMLLoader();
    		loader.setLocation(Main.class.getResource("LoginDialog.fxml"));
    		Pane page = loader.load();
    		
    		Stage dialogStage = new Stage();
    		dialogStage.setTitle("Login");
    		dialogStage.initModality(Modality.WINDOW_MODAL);
    		dialogStage.initOwner(mainScreen);
    		Scene scene = new Scene(page);
    		dialogStage.setScene(scene);
    		dialogStage.showAndWait();
    		return true;
    	} catch (IOException e) {
    		e.printStackTrace();
    		return false;
    	}
    }
    public static void main(String[] args) {
        launch(args);
    }
}
