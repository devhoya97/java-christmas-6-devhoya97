package christmas.domain.promotion.discount;

import christmas.domain.VisitDay;

public class ChristmasPromotion {
    private static final int START_DAY = 1;
    private static final int END_DAY = 25;
    private static final long MIN_TOTAL_PRICE = 10000;
    private static final long DEFAULT_DISCOUNT = 1000;
    private static final long INCREASING_DISCOUNT = 100;
    private static final long NO_DISCOUNT = 0;

    private final VisitDay visitDay;
    private final long totalPrice;

    public ChristmasPromotion(VisitDay visitDay, long totalPrice) {
        this.visitDay = visitDay;
        this.totalPrice = totalPrice;
    }

    public long calculateDiscount() {
        if (visitDay.isInChristmasPromotion() && (totalPrice >= MIN_TOTAL_PRICE)) {
            return DEFAULT_DISCOUNT + (visitDay.getDifferenceFromMinDay() * INCREASING_DISCOUNT);
        }
        return NO_DISCOUNT;
    }
}
