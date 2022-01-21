package tables;

import javafx.scene.control.Button;

public class ShortPoint {

    private final String name;
    private final String coordinates;
    private final Button button;
    private final int pointId;

    public String getName() {
        return name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public Button getButton() {
        return button;
    }

    public int getPointId() {
        return pointId;
    }

    public ShortPoint(String name, String coordinates, Button button, int pointId) {
        this.name = name;
        this.coordinates = coordinates;
        this.button = button;
        this.pointId = pointId;
    }

}
