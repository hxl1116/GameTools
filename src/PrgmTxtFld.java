import javafx.scene.control.TextField;

class PrgmTxtFld extends TextField {

    PrgmTxtFld(String prompt) {
        setPromptText(prompt);
        getStyleClass().add("textfield");
        getStylesheets().add("ToolTheme.css");
    }
}
