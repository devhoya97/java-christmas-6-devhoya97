package christmas.domain.promotion.benefit;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.domain.menu.Menu;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BenefitManager {
    private static final long MIN_TOTAL_PRICE_FOR_DISCOUNT = 10000;
    private static final long MIN_TOTAL_PRICE_FOR_GIFT = 120000;
    private static final long DEFAULT_DISCOUNT = 1000;
    private static final long INCREASING_DISCOUNT = 100;
    private static final long DISCOUNT_PER_MENU = 2023;
    private static final long NO_COUNT = 0;

    private final VisitDate visitDate;
    private final Order order;
    private final Map<Benefit, Long> benefitResult = new HashMap<>();

    public BenefitManager(VisitDate visitDate, Order order) {
        this.visitDate = visitDate;
        this.order = order;
        calculateBenefitResult();
    }

    private void calculateBenefitResult() {
        calculateChristmasDiscount();
        calculateWeekDayDiscount();
        calculateWeekendDiscount();
        calculateSpecialDiscount();
        calculateGiftBenefit();
    }

    private void calculateChristmasDiscount() {
        if (visitDate.isInChristmasPromotion() && (order.calculateTotalPrice() >= MIN_TOTAL_PRICE_FOR_DISCOUNT)) {
            long discount = DEFAULT_DISCOUNT + (INCREASING_DISCOUNT * visitDate.getDifferenceFromFirstDay());
            benefitResult.put(Benefit.CHRISTMAS, discount);
        }
    }

    private void calculateWeekDayDiscount() {
        int dessertCount = order.countDesserts();
        if ((visitDate.isInWeekDayPromotion()) && (order.calculateTotalPrice() >= MIN_TOTAL_PRICE_FOR_DISCOUNT)
                && (dessertCount != NO_COUNT)) {
            long discount = DISCOUNT_PER_MENU * dessertCount;
            benefitResult.put(Benefit.WEEK_DAY, discount);
        }
    }

    private void calculateWeekendDiscount() {
        int mainCount = order.countMains();
        if ((visitDate.isInWeekendPromotion()) && (order.calculateTotalPrice() >= MIN_TOTAL_PRICE_FOR_DISCOUNT)
                && mainCount != NO_COUNT) {
            long discount = DISCOUNT_PER_MENU * mainCount;
            benefitResult.put(Benefit.WEEKEND, discount);
        }
    }

    private void calculateSpecialDiscount() {
        if ((visitDate.isInSpecialPromotion()) && (order.calculateTotalPrice() >= MIN_TOTAL_PRICE_FOR_DISCOUNT)) {
            benefitResult.put(Benefit.SPECIAL, DEFAULT_DISCOUNT);
        }
    }

    private void calculateGiftBenefit() {
        if (order.calculateTotalPrice() >= MIN_TOTAL_PRICE_FOR_GIFT) {
            long benefit = Menu.CHAMPAGNE.getPrice();
            benefitResult.put(Benefit.GIFT, benefit);
        }
    }

    public Map<Benefit, Long> getBenefitResult() {
        return Collections.unmodifiableMap(benefitResult);
    }

    public long calculateDiscountedTotalPrice() {
        // 증정 이벤트로 받은 혜택은 할인 후 예상 결제 금액에 영향을 미치지 않으므로 배제한다.
        long totalDiscount = benefitResult.entrySet()
                .stream()
                .filter(entry -> entry.getKey() != Benefit.GIFT)
                .mapToLong(Entry::getValue)
                .sum();
        return order.calculateTotalPrice() - totalDiscount;
    }

    public long calculateTotalBenefit() {
        return benefitResult.values()
                .stream()
                .mapToLong(benefitPrice -> benefitPrice)
                .sum();
    }
}
