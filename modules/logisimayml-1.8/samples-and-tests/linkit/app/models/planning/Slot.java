package models.planning;

import org.joda.time.LocalTime;
import org.joda.time.Minutes;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * A slot is a time period (start - end) when something can be planned.
 * 
 * @author Sryl <cyril.lacote@gmail.com>
 */
public enum Slot {

    HeightThirtyNine,
    NineTen,
    TenEleven,
    ElevenNoon,
    MidDayBreak,
    TwoThree,
    ThreeFour,
    FourFive;

    Slot() {
    };

    /**
     * Holds constants for this enum. Constants can't be declared in {@link Slot} enum class H an enum declaration must
     * start with its values, and forward reference is prohibited.
     */
    private static class Const {

        private static final DateTimeFormatter FORMAT = DateTimeFormat.forPattern("k'H'mm");
    }

    private LocalTime start;
    private LocalTime end;

    private Slot(LocalTime start, LocalTime end) {
        this.start = start;
        this.end = end;
    }

    public LocalTime getEnd() {
        return end;
    }

    public LocalTime getStart() {
        return start;
    }

    public int getStartMinutesFromMidnight() {
        return Minutes.minutesBetween(LocalTime.MIDNIGHT, start).getMinutes();
    }

    public int getEndMinutesFromMidnight() {
        return Minutes.minutesBetween(LocalTime.MIDNIGHT, end).getMinutes();
    }

    public Minutes getDuration() {
        return Minutes.minutesBetween(start, end);
    }

    @Override
    public String toString() {
        return new StringBuilder().append(start.toString(Const.FORMAT)).append(" - ")
                .append(end.toString(Const.FORMAT)).toString();
    }
}
