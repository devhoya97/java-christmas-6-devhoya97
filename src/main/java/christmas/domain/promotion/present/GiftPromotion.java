package christmas.domain.promotion.present;

import christmas.domain.VisitDay;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Menu;

public class GiftPromotion {
    private static final long MIN_TOTAL_PRICE = 120000;
    private final VisitDay visitDay;
    private final long totalPrice;

    public GiftPromotion(VisitDay visitDay, long totalPrice) {
        this.visitDay = visitDay;
        this.totalPrice = totalPrice;
    }

    public Menu getGift() {
        if (totalPrice >= MIN_TOTAL_PRICE) {
            return Drink.CHAMPAGNE;
        }
        return null;
    }
}
