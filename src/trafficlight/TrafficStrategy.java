package trafficlight;

public interface TrafficStrategy {
    int getRedDuration();
    int getYellowDuration();
    int getGreenDuration();
    String getStrategyName();
}
