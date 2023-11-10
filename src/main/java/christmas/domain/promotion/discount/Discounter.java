package christmas.domain.promotion.discount;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.domain.menu.Menu;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Discounter {
    private static final long PROMOTION_THRESHOLD = 10000;
    private static final long GIFT_THRESHOLD = 120000;
    private static final long DEFAULT_DISCOUNT = 1000;
    private static final long INCREASING_DISCOUNT = 100;
    private static final long DISCOUNT_PER_MENU = 2023;
    private static final long NO_DISCOUNT = 0;

    private final VisitDate visitDate;
    private final Order order;
    private final Map<Discount, Long> result = new HashMap<>();

    public Discounter(VisitDate visitDate, Order order) {
        this.visitDate = visitDate;
        this.order = order;
        calculateResult();
    }

    public Map<Discount, Long> getResult() {
        return result;
    }

    public long calculateTotalDiscountedPrice() {
        // 증정 이벤트로 받은 혜택은 할인 후 예상 결제 금액에 영향을 미치지 않으므로 배제한다.
        long totalDiscount = result.entrySet()
                .stream()
                .filter(entry -> entry.getKey() != Discount.GIFT)
                .mapToLong(Entry::getValue)
                .sum();
        return order.calculateTotalPrice() - totalDiscount;
    }

    private void calculateResult() {
        calculateChristmasDiscount();
        calculateSpecialDiscount();
        calculateGiftDiscount();
        if (visitDate.isInWeekDayPromotion()) {
            calculateWeekDayDiscount();
            return;
        }
        calculateWeekendDiscount();
    }

    private void calculateChristmasDiscount() {
        long discount = NO_DISCOUNT;
        if (visitDate.isInChristmasPromotion() && (order.calculateTotalPrice() >= PROMOTION_THRESHOLD)) {
            discount = DEFAULT_DISCOUNT + (visitDate.getDifferenceFromMinDay() * INCREASING_DISCOUNT);
        }
        result.put(Discount.CHRISTMAS, discount);
    }

    private void calculateWeekDayDiscount() {
        long discount = NO_DISCOUNT;
        if ((visitDate.isInWeekDayPromotion()) && (order.calculateTotalPrice() >= PROMOTION_THRESHOLD)) {
            int dessertCount = order.countDesserts();
            discount = dessertCount * DISCOUNT_PER_MENU;
        }
        result.put(Discount.WEEK_DAY, discount);
    }

    private void calculateWeekendDiscount() {
        long discount = NO_DISCOUNT;
        if ((visitDate.isInWeekendPromotion()) && (order.calculateTotalPrice() >= PROMOTION_THRESHOLD)) {
            int mainCount = order.countMains();
            discount = mainCount * DISCOUNT_PER_MENU;
        }
        result.put(Discount.WEEKEND, discount);
    }

    private void calculateSpecialDiscount() {
        long discount = NO_DISCOUNT;
        if ((visitDate.isInSpecialPromotion()) && (order.calculateTotalPrice() >= PROMOTION_THRESHOLD)) {
            discount = DEFAULT_DISCOUNT;
        }
        result.put(Discount.SPECIAL, discount);
    }

    private void calculateGiftDiscount() {
        long discount = NO_DISCOUNT;
        if (order.calculateTotalPrice() >= GIFT_THRESHOLD) {
            discount = Menu.CHAMPAGNE.getPrice();
        }
        result.put(Discount.GIFT, discount);
    }
}
