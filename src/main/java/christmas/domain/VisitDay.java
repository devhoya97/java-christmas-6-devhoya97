package christmas.domain;

import static christmas.domain.utils.ErrorMessage.DAY_OUT_OF_RANGE_ERROR;

public class VisitDay {
    public static final int MIN_DAY = 1;
    public static final int MAX_DAY = 31;
    private static final int CHRISTMAS = 25;
    private static final int FRIDAY_REMAIN = 1;
    private static final int SATURDAY_REMAIN = 2;
    private static final int SUNDAY_REMAIN = 3;
    private static final int WEEK_SIZE = 7;
    private final int visitDay;
    public VisitDay(int visitDay) {
        validateDecember(visitDay);
        this.visitDay = visitDay;
    }

    private void validateDecember(int visitDay) {
        if (visitDay < MIN_DAY || visitDay > MAX_DAY) {
            throw new IllegalArgumentException(String.format(DAY_OUT_OF_RANGE_ERROR, MIN_DAY, MAX_DAY));
        }
    }

    public boolean isInChristmasPromotion() {
        return visitDay <= CHRISTMAS;
    }

    public boolean isInWeekendPromotion() {
        int remain = visitDay % WEEK_SIZE;
        return (remain == FRIDAY_REMAIN) || (remain == SATURDAY_REMAIN);
    }

    public boolean isInWeekDayPromotion() {
        return !isInWeekendPromotion();
    }
}
