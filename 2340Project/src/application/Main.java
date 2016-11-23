package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * Creates the UI.
 */
public class Main extends Application {

	/**
	 * Creates the scene for the display
	 * of the javaFX files.
	 *
	 * @param primaryStage where all the javaFX elements are set
	 */
	@Override
	public final void start(final Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 400, 400);
			scene.getStylesheets().add(getClass().
					getResource("application.css").
					toExternalForm());
			primaryStage.
					getIcons().
					add(new Image("file:download.png"));
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Launches the Program.
	 * @param args allows the program to launch
     */
	public static void main(final String[] args) {
		launch(args);
	}
}
