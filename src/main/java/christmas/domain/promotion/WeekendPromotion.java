package christmas.domain.promotion;

import christmas.domain.Order;
import christmas.domain.VisitDay;

public class WeekendPromotion {
    private static final long MIN_TOTAL_PRICE = 10000;
    private static final long DISCOUNT_PER_MAIN = 2023;
    private static final long NO_DISCOUNT = 0;

    private final VisitDay visitDay;
    private final Order order;

    public WeekendPromotion(VisitDay visitDay, Order order) {
        this.visitDay = visitDay;
        this.order = order;
    }

    public long calculateDiscount() {
        if ((visitDay.isInWeekendPromotion()) && (order.calculateTotalPrice() >= MIN_TOTAL_PRICE)) {
            int mainCount = order.countMains();
            return mainCount * DISCOUNT_PER_MAIN;
        }
        return NO_DISCOUNT;
    }
}
