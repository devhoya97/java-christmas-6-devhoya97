package christmas.domain.promotion.discount;

import christmas.domain.Order;
import christmas.domain.VisitDay;

public class SpecialPromotion {
    private static final long MIN_TOTAL_PRICE = 10000;
    private static final long DISCOUNT = 1000;
    private static final long NO_DISCOUNT = 0;

    private final VisitDay visitDay;
    private final long totalPrice;

    public SpecialPromotion(VisitDay visitDay, long totalPrice) {
        this.visitDay = visitDay;
        this.totalPrice = totalPrice;
    }

    public long calculateDiscount() {
        if ((visitDay.isInSpecialPromotion()) && (totalPrice >= MIN_TOTAL_PRICE)) {
            return DISCOUNT;
        }
        return NO_DISCOUNT;
    }
}
