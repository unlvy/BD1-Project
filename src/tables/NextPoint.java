package tables;

import javafx.scene.control.Button;

public class NextPoint {

    private final String name;
    private final String coordinates;
    private final String color;
    private final int length;
    private final int time;
    private final String elevation;
    private final String details;
    private final Button button;
    private final int endPointId;
    private final int trailId;
    private final int elevGain;
    private final int elevLoss;

    public NextPoint(String name, String coordinates, String color, int length, int time, int elevGain, int elevLoss, String details, Button button, int endPointId, int trailId) {
        this.name = name;
        this.coordinates = coordinates;
        this.color = color;
        this.length = length;
        this.time = time;
        this.elevGain = elevGain;
        this.elevLoss = elevLoss;
        this.elevation = "+" + elevGain + "m\n-" + elevLoss + "m";
        this.details = details;
        this.button = button;
        this.endPointId = endPointId;
        this.trailId = trailId;
    }

    public String getName() {
        return name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public String getColor() {
        return color;
    }

    public String getLength() {
        return length + "m";
    }

    public String getTime() {
        return (time / 60) + "h " + (time % 60) + "min";
    }

    public int getTimeInt() {
        return time;
    }

    public int getLengthInt() {
        return length;
    }

    public String getElevation() {
        return elevation;
    }

    public String getDetails() {
        return details;
    }

    public Button getButton() {
        return button;
    }

    public int getEndPointId() {
        return endPointId;
    }

    public int getTrailId() {
        return trailId;
    }

    public int getElevGain() {
        return elevGain;
    }

    public int getElevLoss() {
        return elevLoss;
    }

}
