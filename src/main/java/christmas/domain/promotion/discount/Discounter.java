package christmas.domain.promotion.discount;

import christmas.domain.Order;
import christmas.domain.VisitDay;
import christmas.domain.menu.Drink;
import java.util.HashMap;
import java.util.Map;

public class Discounter {
    private static final long PROMOTION_THRESHOLD = 10000;
    private static final long GIFT_THRESHOLD = 120000;
    private static final long DEFAULT_DISCOUNT = 1000;
    private static final long INCREASING_DISCOUNT = 100;
    private static final long DISCOUNT_PER_MENU = 2023;
    private static final long NO_DISCOUNT = 0;

    private final VisitDay visitDay;
    private final Order order;
    private final Map<Discount, Long> result = new HashMap<>();

    public Discounter(VisitDay visitDay, Order order) {
        this.visitDay = visitDay;
        this.order = order;
        calculateResult();
    }

    public Map<Discount, Long> getResult() {
        return result;
    }

    private void calculateResult() {
        calculateChristmasDiscount();
        calculateSpecialDiscount();
        calculateGiftDiscount();
        if (visitDay.isInWeekDayPromotion()) {
            calculateWeekDayDiscount();
            return;
        }
        calculateWeekendDiscount();
    }

    private void calculateChristmasDiscount() {
        long discount = NO_DISCOUNT;
        if (visitDay.isInChristmasPromotion() && (order.calculateTotalPrice() >= PROMOTION_THRESHOLD)) {
            discount = DEFAULT_DISCOUNT + (visitDay.getDifferenceFromMinDay() * INCREASING_DISCOUNT);
        }
        result.put(Discount.CHRISTMAS, discount);
    }

    private void calculateWeekDayDiscount() {
        long discount = NO_DISCOUNT;
        if ((visitDay.isInWeekDayPromotion()) && (order.calculateTotalPrice() >= PROMOTION_THRESHOLD)) {
            int dessertCount = order.countDesserts();
            discount = dessertCount * DISCOUNT_PER_MENU;
        }
        result.put(Discount.WEEK_DAY, discount);
    }

    private void calculateWeekendDiscount() {
        long discount = NO_DISCOUNT;
        if ((visitDay.isInWeekendPromotion()) && (order.calculateTotalPrice() >= PROMOTION_THRESHOLD)) {
            int mainCount = order.countMains();
            discount = mainCount * DISCOUNT_PER_MENU;
        }
        result.put(Discount.WEEKEND, discount);
    }

    private void calculateSpecialDiscount() {
        long discount = NO_DISCOUNT;
        if ((visitDay.isInSpecialPromotion()) && (order.calculateTotalPrice() >= PROMOTION_THRESHOLD)) {
            discount = DEFAULT_DISCOUNT;
        }
        result.put(Discount.SPECIAL, discount);
    }

    private void calculateGiftDiscount() {
        long discount = NO_DISCOUNT;
        if (order.calculateTotalPrice() >= GIFT_THRESHOLD) {
            discount = Drink.CHAMPAGNE.getPrice();
        }
        result.put(Discount.GIFT, discount);
    }
}
