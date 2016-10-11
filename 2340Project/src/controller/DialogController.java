package controller;

import javafx.stage.Stage;

public abstract class DialogController extends Controller {
    
    protected Stage dialogStage;
    
    /**
     * @return the stage of the dialog.
     */
    public Stage stage() {
        return dialogStage;
    }
    
    protected void setDialogStage(Stage stage) {
        dialogStage = stage;
    }
}
