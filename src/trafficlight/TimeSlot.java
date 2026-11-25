package trafficlight;

public class TimeSlot {

    private int startHour;   
    private int startMinute; 
    private int endHour;    
    private int endMinute;  
    private TrafficStrategy strategy; 

    public TimeSlot(int startHour, int startMinute, int endHour,   int endMinute, TrafficStrategy strategy) {
        this.startHour    = startHour;
        this.startMinute  = startMinute;
        this.endHour      = endHour;
        this.endMinute    = endMinute;
        this.strategy     = strategy;
    }

    public boolean isActive(int hour, int minute) {
        int now = hour * 60 + minute;
        int start = startHour * 60 + startMinute;
        int end = endHour * 60 + endMinute;

        if (start < end) {
            return now >= start && now < end;
        } else {
            return now >= start || now < end;
        }
    }

    public TrafficStrategy getStrategy() {
        return strategy;
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d - %02d:%02d [%s]",
                startHour, startMinute, endHour, endMinute,
                strategy.getStrategyName());
    }
}
