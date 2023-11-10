package christmas.domain;

public class ChristmasPromotion {
    private static final int START_DAY = 1;
    private static final int END_DAY = 25;
    private static final int MIN_TOTAL_PRICE = 10000;
    private static final int DEFAULT_DISCOUNT = 1000;
    private static final int INCREASING_DISCOUNT = 100;

    private final int day;
    private final int totalPrice;

    public ChristmasPromotion(int day, int totalPrice) {
        this.day = day;
        this.totalPrice = totalPrice;
    }

    public int calculateDiscountedPrice() {
        if (canReceive()) {
            int totalDiscount = DEFAULT_DISCOUNT + ((day - START_DAY) * INCREASING_DISCOUNT);
            return totalPrice - totalDiscount;
        }
        return totalPrice;
    }

    private boolean canReceive() {
        return (day >= START_DAY && day <= END_DAY && totalPrice >= MIN_TOTAL_PRICE);
    }
}
