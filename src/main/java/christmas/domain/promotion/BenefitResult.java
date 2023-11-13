package christmas.domain.promotion;

import christmas.domain.VisitDate;
import christmas.domain.order.Menu;
import christmas.domain.order.Order;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BenefitResult {
    private static final long MIN_TOTAL_PRICE_FOR_DISCOUNT = 10_000;
    private static final long MIN_TOTAL_PRICE_FOR_GIFT = 120_000;
    private static final long DEFAULT_DISCOUNT = 1_000;
    private static final long INCREASING_DISCOUNT = 100;
    private static final long DISCOUNT_PER_MENU = 2_023;
    private static final long NO_COUNT = 0;

    private final Map<Benefit, Long> result = new HashMap<>();

    public BenefitResult(VisitDate visitDate, Order order) {
        long totalPrice = order.calculateTotalPrice();
        if (totalPrice >= MIN_TOTAL_PRICE_FOR_DISCOUNT) {
            addChristmasDiscount(visitDate.isInChristmasPromotion(), visitDate.getDifferenceFromFirstDay());
            addWeekDayDiscount(visitDate.isInWeekDayPromotion(), order.countDesserts());
            addWeekendDiscount(visitDate.isInWeekendPromotion(), order.countMains());
            addSpecialDiscount(visitDate.isInSpecialPromotion());
        }
        if (totalPrice >= MIN_TOTAL_PRICE_FOR_GIFT) {
            addGiftBenefit();
        }
    }

    private void addChristmasDiscount(boolean isInChristmasPromotion, int differenceFromFirstDay) {
        if (isInChristmasPromotion) {
            long discount = DEFAULT_DISCOUNT + (INCREASING_DISCOUNT * differenceFromFirstDay);
            result.put(Benefit.CHRISTMAS_DISCOUNT, discount);
        }
    }

    private void addWeekDayDiscount(boolean isInWeekDayPromotion, int dessertCount) {
        if (isInWeekDayPromotion && (dessertCount != NO_COUNT)) {
            long discount = DISCOUNT_PER_MENU * dessertCount;
            result.put(Benefit.WEEK_DAY_DISCOUNT, discount);
        }
    }

    private void addWeekendDiscount(boolean isInWeekendPromotion, int mainCount) {
        if (isInWeekendPromotion && (mainCount != NO_COUNT)) {
            long discount = DISCOUNT_PER_MENU * mainCount;
            result.put(Benefit.WEEKEND_DISCOUNT, discount);
        }
    }

    private void addSpecialDiscount(boolean isInSpecialPromotion) {
        if (isInSpecialPromotion) {
            result.put(Benefit.SPECIAL_DISCOUNT, DEFAULT_DISCOUNT);
        }
    }

    private void addGiftBenefit() {
        long giftPrice = Menu.CHAMPAGNE.getPrice();
        result.put(Benefit.GIFT_GIVING, giftPrice);
    }

    public long calculateTotalDiscount() {
        // 증정 이벤트로 받은 혜택은 할인 후 예상 결제 금액에 영향을 미치지 않으므로 배제한다.
        return result.entrySet()
                .stream()
                .filter(entry -> entry.getKey() != Benefit.GIFT_GIVING)
                .mapToLong(Entry::getValue)
                .sum();
    }

    public long calculateTotalBenefit() {
        return result.values()
                .stream()
                .mapToLong(benefitPrice -> benefitPrice)
                .sum();
    }

    public boolean hasGift() {
        return result.containsKey(Benefit.GIFT_GIVING);
    }

    // OutputView를 위한 getter
    public Map<Benefit, Long> get() {
        return Collections.unmodifiableMap(result);
    }
}
