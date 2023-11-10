package christmas.domain;

public class ChristmasPromotion {
    public static final int START_DAY = 1;
    public static final int END_DAY = 25;
    public static final int MIN_TOTAL_PRICE = 10000;
    private final int day;
    private final int totalPrice;

    public ChristmasPromotion(int day, int totalPrice) {
        this.day = day;
        this.totalPrice = totalPrice;
    }

    public boolean canReceive() {
        return (day >= START_DAY && day <= END_DAY && totalPrice >= MIN_TOTAL_PRICE);
    }
}
