package database;

import java.util.ArrayList;
import java.util.HashMap;

public class Trip {

    private HashMap<Integer, Integer> steps;
    private String name;
    private int totalLength;
    private int totalTime;
    private int totalElevGain;
    private int totalElevLoss;
    private int order = 1;

    public Trip() {
        steps = new HashMap<>();
        name = "undefined";
    }

    public void addStep(int trailId, int length, int time, int elevGain, int elevLoss) {
        steps.put(order++, trailId);
        totalLength += length;
        totalTime += time;
        totalElevGain += elevGain;
        totalElevLoss += elevLoss;
    }

    public void clear() {
        steps = new HashMap<>();
        name = "undefined";
        totalLength = 0;
        totalTime = 0;
        totalElevGain = 0;
        totalElevLoss = 0;
        order = 1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<Integer, Integer> getSteps() {
        return steps;
    }

    public String getName() {
        return name;
    }

    public int getTotalLength() {
        return totalLength;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public int getTotalElevGain() {
        return totalElevGain;
    }

    public int getTotalElevLoss() {
        return totalElevLoss;
    }

    public int getOrder() {
        return order;
    }
}
