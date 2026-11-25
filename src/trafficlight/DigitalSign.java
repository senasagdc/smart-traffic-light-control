package trafficlight;

import java.io.PrintStream;

public class DigitalSign implements TrafficObserver {

    private int duration;

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public void update(LightStatus status) {
        System.out.println("[Traffic Light] Status: " + status + " - Please observe all traffic regulations.");
    }

    public void showLoadingBar() {
        int barLength = 20;

        PrintStream out;
        try {
            out = new PrintStream(System.out, true, "UTF-8");
        } catch (Exception e) {
            out = System.out;
        }

        for (int remaining = duration; remaining >= 0; remaining--) {
            int full = (duration > 0)
                    ? (int) ((double)(duration - remaining) / duration * barLength)
                    : barLength;
            int empty = barLength - full;

            StringBuilder bar = new StringBuilder("[");
            for (int i = 0; i < full; i++) bar.append("\u2588"); // █
            for (int i = 0; i < empty;  i++) bar.append("\u2591"); // ░
            bar.append("] ").append(remaining).append("s left.");

            out.println("[Traffic Light] " + bar);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
