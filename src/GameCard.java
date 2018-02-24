import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

class GameCard extends VBox {
    GameCard(String url, int x, int y, int width, int height, String name) {
        Image image = new Image(url, 1920, 1080, true, true);
        WritableImage writableImage = new WritableImage(image.getPixelReader(), x, y, width, height);
        ImageView imageView = new ImageView(writableImage);
        imageView.getStyleClass().addAll("image", "image:hover");

        Label label = new Label(name);
        label.getStyleClass().add("gc-label");

        DropShadow drop = new DropShadow(50, Color.WHITE);
        drop.setInput(new Glow());

        setOnMousePressed(e -> setEffect(drop));
        setOnMouseReleased(e -> setEffect(null));

        getChildren().addAll(imageView, label);
        getStylesheets().add("ToolTheme.css");
    }
}
