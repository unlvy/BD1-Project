package tables;

public class TripStage {

    private final int order;
    private final String startPointName;
    private final String endPointName;
    private final int length;
    private final int time;
    private final int elevGain;
    private final int elevLoss;
    private final String color;
    private final String danger;
    private final String attraction;

    public TripStage(int order, String startPointName, String endPointName, int length, int time, int elevGain, int elevLoss, String color, String danger, String attraction) {
        this.order = order;
        this.startPointName = startPointName;
        this.endPointName = endPointName;
        this.length = length;
        this.time = time;
        this.elevGain = elevGain;
        this.elevLoss = elevLoss;
        this.color = color;
        this.danger = danger;
        this.attraction = attraction;
    }

    public String getOrder() {
        return order + "";
    }

    public String getEndPoint() {
        return endPointName;
    }

    public String getStartPoint() {
        return startPointName;
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

    public String getColor() {
        return color;
    }

    public String getDanger() {
        return danger;
    }

    public String getAttraction() {
        return attraction;
    }

}
