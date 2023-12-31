package christmas.domain;

import static christmas.domain.utils.ErrorMessage.INVALID_DATE_ERROR;

public class VisitDate {
    private static final int FIRST_DAY = 1;
    private static final int FINAL_DAY = 31;
    private static final int CHRISTMAS = 25;
    private static final int FRIDAY_REMAIN = 1;
    private static final int SATURDAY_REMAIN = 2;
    private static final int SUNDAY_REMAIN = 3;
    private static final int WEEK_SIZE = 7;
    private final int visitDate;

    public VisitDate(int visitDate) {
        validateDecember(visitDate);
        this.visitDate = visitDate;
    }

    private void validateDecember(int visitDate) {
        if (visitDate < FIRST_DAY || visitDate > FINAL_DAY) {
            throw new IllegalArgumentException(INVALID_DATE_ERROR);
        }
    }

    public boolean isInChristmasPromotion() {
        return visitDate <= CHRISTMAS;
    }

    public boolean isInWeekendPromotion() {
        int remain = visitDate % WEEK_SIZE;
        return (remain == FRIDAY_REMAIN) || (remain == SATURDAY_REMAIN);
    }

    public boolean isInWeekDayPromotion() {
        return !isInWeekendPromotion();
    }

    public boolean isInSpecialPromotion() {
        int remain = visitDate % WEEK_SIZE;
        return (remain == SUNDAY_REMAIN) || (visitDate == CHRISTMAS);
    }

    public int getDifferenceFromFirstDay() {
        return visitDate - FIRST_DAY;
    }

    @Override
    public String toString() {
        return String.valueOf(visitDate);
    }
}
