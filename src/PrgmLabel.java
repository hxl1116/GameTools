import javafx.scene.control.Label;

class PrgmLabel extends Label {

    PrgmLabel() {
        getStyleClass().add("display-label");

        getStylesheets().add("ToolTheme.css");
    }

    PrgmLabel(String label) {
        setText(label);
        getStyleClass().add("display-label");

        getStylesheets().add("ToolTheme.css");
    }
}
