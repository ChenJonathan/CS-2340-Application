package controller;

import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A controller that opens a dialog in a new window.
 * @author Jonathan Chen
 */
public abstract class DialogController extends Controller {

    /**Controls the dialogue boxes Scene. */
    protected Stage dialogStage;

    /**
     * @return the stage of the dialog.
     */
    public final Stage stage() {
        dialogStage.getIcons().add(new Image("file:water.png"));
        return dialogStage;
    }

    /**
     * @param stage the scene to be shown in dialogue boxes.
     */
    protected final void setDialogStage(final Stage stage) {
        dialogStage = stage;
    }
}
