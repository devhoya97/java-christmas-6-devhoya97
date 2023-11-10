package christmas.domain.promotion;

import christmas.domain.Order;
import christmas.domain.VisitDay;

public class WeekDayPromotion {
    private static final long MIN_TOTAL_PRICE = 10000;
    public static final long DISCOUNT_PER_DESSERT = 2023;
    private static final long NO_DISCOUNT = 0;

    private final VisitDay visitDay;
    private final Order order;

    public WeekDayPromotion(VisitDay visitDay, Order order) {
        this.visitDay = visitDay;
        this.order = order;
    }

    public long calculateDiscount() {
        if ((visitDay.isInWeekDayPromotion()) && (order.calculateTotalPrice() >= MIN_TOTAL_PRICE)) {
            int dessertCount = order.countDesserts();
            return dessertCount * DISCOUNT_PER_DESSERT;
        }
        return NO_DISCOUNT;
    }
}
