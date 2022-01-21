package tables;

public class Point {

    private final String name;
    private final String coordinates;
    private final String type;
    private final String neighbours;
    private final String info;

    public String getName() {
        return name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public String getType() {
        return type;
    }

    public String getNeighbours() {
        return neighbours;
    }

    public String getInfo() {
        return info;
    }

    public Point(String name, String coordinates, String type, String neighbours, String info) {
        this.name = name;
        this.coordinates = coordinates;
        this.type = type;
        this.neighbours = neighbours;
        this.info = info;
    }

}



