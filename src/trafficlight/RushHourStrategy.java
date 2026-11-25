package trafficlight;

public class RushHourStrategy implements TrafficStrategy {
    public int getRedDuration() { return 3; }
    public int getYellowDuration() { return 2; }
    public int getGreenDuration() { return 8; }
    public String getStrategyName() { return "RUSH HOUR MODE"; }
}
