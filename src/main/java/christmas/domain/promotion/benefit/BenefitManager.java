package christmas.domain.promotion.benefit;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.domain.menu.Menu;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BenefitManager {
    private static final long MIN_TOTAL_PRICE_FOR_DISCOUNT = 10_000;
    private static final long MIN_TOTAL_PRICE_FOR_GIFT = 120_000;
    private static final long DEFAULT_DISCOUNT = 1_000;
    private static final long INCREASING_DISCOUNT = 100;
    private static final long DISCOUNT_PER_MENU = 2_023;
    private static final long NO_COUNT = 0;

    private final Map<Benefit, Long> benefitResult = new HashMap<>();
    private final long totalPrice;

    public BenefitManager(VisitDate visitDate, Order order) {
        totalPrice = order.calculateTotalPrice();
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
            benefitResult.put(Benefit.CHRISTMAS_DISCOUNT, discount);
        }
    }

    private void addWeekDayDiscount(boolean isInWeekDayPromotion, int dessertCount) {
        if (isInWeekDayPromotion && (dessertCount != NO_COUNT)) {
            long discount = DISCOUNT_PER_MENU * dessertCount;
            benefitResult.put(Benefit.WEEK_DAY_DISCOUNT, discount);
        }
    }

    private void addWeekendDiscount(boolean isInWeekendPromotion, int mainCount) {
        if (isInWeekendPromotion && (mainCount != NO_COUNT)) {
            long discount = DISCOUNT_PER_MENU * mainCount;
            benefitResult.put(Benefit.WEEKEND_DISCOUNT, discount);
        }
    }

    private void addSpecialDiscount(boolean isInSpecialPromotion) {
        if (isInSpecialPromotion) {
            benefitResult.put(Benefit.SPECIAL_DISCOUNT, DEFAULT_DISCOUNT);
        }
    }

    private void addGiftBenefit() {
        long giftPrice = Menu.CHAMPAGNE.getPrice();
        benefitResult.put(Benefit.GIFT_GIVING, giftPrice);
    }

    public long calculateDiscountedTotalPrice() {
        // 증정 이벤트로 받은 혜택은 할인 후 예상 결제 금액에 영향을 미치지 않으므로 배제한다.
        long totalDiscount = benefitResult.entrySet()
                .stream()
                .filter(entry -> entry.getKey() != Benefit.GIFT_GIVING)
                .mapToLong(Entry::getValue)
                .sum();
        return totalPrice - totalDiscount;
    }

    public long calculateTotalBenefit() {
        return benefitResult.values()
                .stream()
                .mapToLong(benefitPrice -> benefitPrice)
                .sum();
    }

    public boolean hasGift() {
        return benefitResult.containsKey(Benefit.GIFT_GIVING);
    }

    public Map<Benefit, Long> getBenefitResult() {
        return Collections.unmodifiableMap(benefitResult);
    }
}
