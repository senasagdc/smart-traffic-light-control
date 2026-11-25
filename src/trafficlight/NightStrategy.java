package trafficlight;

public class NightStrategy implements TrafficStrategy {
    public int getRedDuration() { return 3; }
    public int getYellowDuration() { return 3; }
    public int getGreenDuration() { return 7; }
    public String getStrategyName() { return "NIGHT MODE"; }
}
