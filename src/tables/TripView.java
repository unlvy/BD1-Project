package tables;

import javafx.scene.control.Button;

public class TripView {

    private final String name;
    private final int length;
    private final int time;
    private final int elevGain;
    private final int elevLoss;
    private final Button button;
    private final int tripId;

    public TripView(String name, int length, int time, int elevGain, int elevLoss, int tripId) {
        this.name = name;
        this.length = length;
        this.time = time;
        this.elevGain = elevGain;
        this.elevLoss = elevLoss;
        this.button = new Button("wybierz");
        this.tripId = tripId;
    }

    public String getName() {
        return name;
    }

    public String getLength() {
        return length + "m";
    }

    public String getTime() {
        return (time / 60) + "h " + (time % 60) + "min";
    }

    public String getElevation() {
        return "+" + elevGain + "m\n-" + elevLoss + "m";
    }

    public Button getButton() {
        return button;
    }

    public int getTripId() {
        return tripId;
    }

}
