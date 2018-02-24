import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.paint.Color;

class PrgmSlctnBttn extends Button {
    private DropShadow drop;
    private boolean isSelected = false;

    PrgmSlctnBttn(String label, String size) {
        setText(label);

        switch (size) {
            case "small":
                getStyleClass().addAll("selection-button-small", "selection-button-small:hover");
                break;

            case "medium":
                getStyleClass().addAll("selection-button-medium", "selection-button-medium:hover");
                break;

            default:
                getStyleClass().addAll("selection-button-large", "selection-button-large:hover");
                break;
        }


        drop = new DropShadow(10, Color.WHITE);
        drop.setInput(new Glow());

        setAlignment(Pos.CENTER);

        getStylesheets().add("ToolTheme.css");
    }

    void setSelected() {
        if (!isSelected) {
            isSelected = true;
            setEffect(drop);
        }
        else {
            isSelected = false;
            setEffect(null);
        }
    }

    boolean getSelected() {
        return isSelected;
    }

    void setDefault() {
        setEffect(null);
        isSelected = false;
    }

    void setNewSize(double width, double height) {
        setWidth(width);
        setHeight(height);
    }
}
