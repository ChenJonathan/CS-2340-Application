package fxapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 * Entry point into the application.
 */
public class Main extends Application {

    /**Where all the FX stuff is displayed. */
    private static Stage mainStage;

    /**
     * @return the single instance of the main stage.
     */
    public static Stage stage() {
        return mainStage;
    }

    /**
     * Draws a rectangle as the screen bounds.
     */
    public static final Rectangle2D primaryScreenBounds =
            Screen.getPrimary().getVisualBounds();

    /**
     * Starts the application.
     * @param stage what the FX stuff is drawn on.
     * @throws Exception if something is null.
     */
    @Override
    public final void start(final Stage stage) throws Exception {
        mainStage = stage;

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.
                getResource("../view/WelcomeScreen.fxml"));

        Parent root = loader.load();
        stage.setScene(new Scene(root,
                Main.primaryScreenBounds.getWidth(),
                Main.primaryScreenBounds.getHeight()));
        stage.getIcons().add(new Image("file:water.png"));
        stage.setMaximized(true);
        stage.show();
    }

    /**
     * How the app is launched.
     * @param args needed to launch app.
     */
    public static void main(final String[] args) {
        launch(args);
    }
}
