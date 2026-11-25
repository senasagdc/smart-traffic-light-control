package trafficlight;

public class PedestrianLight implements TrafficObserver {

    @Override
    public void update(LightStatus status) {
        switch (status) {
            case GREEN:
                System.out.println("[Pedestrian Light] Status: RED - Please wait.");
                break;
            case RED:
                System.out.println("[Pedestrian Light] Status: GREEN - Safe to cross.");
                break;
            case YELLOW:
                break;
        }
    }
}
