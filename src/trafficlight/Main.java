package trafficlight;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        TrafficLightController controller = new TrafficLightController(new NormalStrategy());

        controller.addObserver(new DigitalSign());
        controller.addObserver(new PedestrianLight());
        controller.addObserver(new TrafficCamera());

        List<TimeSlot> program = new ArrayList<>();

        program.add(new TimeSlot(0, 0, 6, 0, new NightStrategy()));
        program.add(new TimeSlot(6, 0, 9, 0, new RushHourStrategy()));
        program.add(new TimeSlot(9, 0, 16, 0, new NormalStrategy()));
        program.add(new TimeSlot(16, 0, 20, 0, new RushHourStrategy()));
        program.add(new TimeSlot(20, 0, 0, 0, new NormalStrategy()));

        System.out.println("Daily Traffic Program:");
        for (TimeSlot slot : program) {
            System.out.println("  " + slot);
        }

        System.out.println("\n===== SIMULATION MODE =====");
        runSimulation(controller, program);

        System.out.println("\n===== REAL-TIME MODE =====");
        runRealTimeMode(controller, program);
    }

    private static void runSimulation(TrafficLightController controller, List<TimeSlot> program)
            throws InterruptedException {

        for (int i = 0; i < program.size(); i++) {
            TimeSlot slot = program.get(i);

            controller.setStrategy(slot.getStrategy());

            System.out.println("\nTime Zone: " + slot);

            controller.runCycle();
        }
    }

    private static void runRealTimeMode(TrafficLightController controller, List<TimeSlot> program)
            throws InterruptedException {

        LocalTime now = LocalTime.now();

        int currentHour = now.getHour();
        int currentMinute = now.getMinute();

        TimeSlot activeSlot = null;

        for (TimeSlot slot : program) {
            if (slot.isActive(currentHour, currentMinute)) {
                activeSlot = slot;
                break;
            }
        }

        if (activeSlot != null) {
            controller.setStrategy(activeSlot.getStrategy());

            System.out.println("\n--- REAL-TIME MODE ---");
            System.out.println("Current Time: " + String.format("%02d:%02d", currentHour, currentMinute));
            System.out.println("Active Time Zone: " + activeSlot);

            controller.runCycle();
        } else {
            System.out.println("No active time slot found.");
        }
    }
}
