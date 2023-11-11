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

    private void calculateResult() {
        calculateChristmasDiscount();
        calculateSpecialDiscount();
        calculateGiftDiscount();
        calculateWeekDayDiscount();
        calculateWeekendDiscount();
    }

    private void calculateChristmasDiscount() {
        if (visitDate.isInChristmasPromotion() && (order.calculateTotalPrice() >= PROMOTION_THRESHOLD)) {
            long discount = DEFAULT_DISCOUNT + (visitDate.getDifferenceFromMinDay() * INCREASING_DISCOUNT);
            result.put(Discount.CHRISTMAS, discount);
        }
    }

    private void calculateWeekDayDiscount() {
        if ((visitDate.isInWeekDayPromotion()) && (order.calculateTotalPrice() >= PROMOTION_THRESHOLD)) {
            int dessertCount = order.countDesserts();
            long discount = dessertCount * DISCOUNT_PER_MENU;
            result.put(Discount.WEEK_DAY, discount);
        }
    }

    private void calculateWeekendDiscount() {
        if ((visitDate.isInWeekendPromotion()) && (order.calculateTotalPrice() >= PROMOTION_THRESHOLD)) {
            int mainCount = order.countMains();
            long discount = mainCount * DISCOUNT_PER_MENU;
            result.put(Discount.WEEKEND, discount);
        }
    }

    private void calculateSpecialDiscount() {
        if ((visitDate.isInSpecialPromotion()) && (order.calculateTotalPrice() >= PROMOTION_THRESHOLD)) {
            long discount = DEFAULT_DISCOUNT;
            result.put(Discount.SPECIAL, discount);
        }
    }

    private void calculateGiftDiscount() {
        if (order.calculateTotalPrice() >= GIFT_THRESHOLD) {
            long discount = Menu.CHAMPAGNE.getPrice();
            result.put(Discount.GIFT, discount);
        }
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

    public long calculateTotalDiscount() {
        return result.values()
                .stream()
                .mapToLong(discountPrice -> discountPrice)
                .sum();
    }
}