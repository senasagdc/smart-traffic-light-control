package trafficlight;

public class NormalStrategy implements TrafficStrategy {
    public int getRedDuration() { return 5; }
    public int getYellowDuration() { return 3; }
    public int getGreenDuration() { return 5; }
    public String getStrategyName() { return "NORMAL MODE"; }
}
