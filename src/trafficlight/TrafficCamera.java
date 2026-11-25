package trafficlight;

public class TrafficCamera implements TrafficObserver {

    @Override
    public void update(LightStatus status) {
        switch (status) {
            case GREEN:
                System.out.println("[Camera] Green traffic light! Passage allowed, recording off.");
                break;
            case YELLOW:
                System.out.println("[Camera] Yellow traffic light! Proceed with caution, violation tracking activating shortly.");
                break;
            case RED:
                System.out.println("[Camera] Red traffic light! Violation monitoring enabled.");
                break;
        }
    }
}
