package trafficlight;

import java.util.ArrayList;
import java.util.List;

public class TrafficLightController {

    private List<TrafficObserver> observers = new ArrayList<>();
    private TrafficStrategy strategy;
    private LightStatus currentStatus;

    public TrafficLightController(TrafficStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(TrafficStrategy strategy) {
        this.strategy = strategy;
    }

    public void addObserver(TrafficObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (TrafficObserver observer : observers) {
            observer.update(currentStatus);
        }
    }

    private void runCountdown(int seconds) throws InterruptedException {
    
        for (TrafficObserver observer : observers) {
            if (observer instanceof DigitalSign) {
                ((DigitalSign) observer).setDuration(seconds);
                ((DigitalSign) observer).showLoadingBar();
            }
        }
    }

    private void handleLight(LightStatus status, int seconds) throws InterruptedException {
        currentStatus = status;

        String name = status == LightStatus.GREEN   ? "GREEN"   :
                      status == LightStatus.YELLOW  ? "YELLOW"    : "RED";
        System.out.println("\n>> " + name + " LIGHT (" + seconds + "s)");

        notifyObservers();

        runCountdown(seconds);
    }

    public void runCycle() throws InterruptedException {
        System.out.println("\n--- " + strategy.getStrategyName() + " ---");
        System.out.println("    Green: " + strategy.getGreenDuration()  + "s  |  "
                         + "Yellow: "     + strategy.getYellowDuration() + "s  |  "
                         + "Red: "  + strategy.getRedDuration()    + "s");
        System.out.println("---------------------------------------");

        handleLight(LightStatus.GREEN,  strategy.getGreenDuration());
        handleLight(LightStatus.YELLOW, strategy.getYellowDuration());
        handleLight(LightStatus.RED,    strategy.getRedDuration());

        System.out.println("[" + strategy.getStrategyName() + "] Cycle Completed.");
    }
}
