package pl.psi.gui;

import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class MapTile extends StackPane {

    private final Rectangle rect;
    private String name;

    MapTile(String aName) {
        name = aName;
        rect = new Rectangle(60, 60);
        rect.setFill(Color.WHITE);
        rect.setStroke(Color.RED);
        getChildren().add(rect);
        getChildren().add(new Label(name));
    }


    Rectangle getRect() {
        return rect;
    }
}
