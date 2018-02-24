import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;

class PrgmBttn extends Button {
    PrgmBttn(String label) {
        setText(label);
        getStyleClass().addAll("prgm-button", "prgm-button:hover");

        DropShadow drop = new DropShadow(10, Color.WHITE);
        drop.setInput(new Glow());

        setOnMousePressed(e -> setEffect(drop));
        setOnMouseReleased(e -> setEffect(null));

        getStylesheets().add("ToolTheme.css");
    }
}
