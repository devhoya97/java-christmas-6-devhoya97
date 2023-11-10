package christmas.domain;

public class ChristmasPromotion {
    private static final int START_DAY = 1;
    private static final int END_DAY = 25;
    private static final int MIN_TOTAL_PRICE = 10000;
    private static final int DEFAULT_DISCOUNT = 1000;
    private static final int INCREASING_DISCOUNT = 100;
    private static final int NO_DISCOUNT = 0;

    private final VisitDay visitDay;
    private final int totalPrice;

    public ChristmasPromotion(VisitDay visitDay, int totalPrice) {
        this.visitDay = visitDay;
        this.totalPrice = totalPrice;
    }

    public int calculateDiscount() {
        if (visitDay.isInChristmasPromotion() && totalPrice >= MIN_TOTAL_PRICE) {
            return DEFAULT_DISCOUNT + (visitDay.getDifferenceFromMinDay() * INCREASING_DISCOUNT);
        }
        return NO_DISCOUNT;
    }
}
