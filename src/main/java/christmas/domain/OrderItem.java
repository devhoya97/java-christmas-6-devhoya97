package christmas.domain;


import static christmas.domain.utils.ErrorMessage.INVALID_ORDER_ERROR;

import christmas.domain.menu.Menu;
import java.util.Objects;

public class OrderItem {
    private static final String MAIN = "메인";
    private static final String DESSERT = "디저트";
    private static final String DRINK = "음료";
    private static final int MIN_COUNT = 1;

    private final Menu menu;
    private final int count;

    public OrderItem(Menu menu, int count) {
        validateCount(count);
        this.menu = menu;
        this.count = count;
    }

    private void validateCount(int count) {
        if (count < MIN_COUNT) {
            throw new IllegalArgumentException(String.format(INVALID_ORDER_ERROR));
        }
    }

    public long calculatePrice() {
        return menu.getPrice() * count;
    }

    public int accumulateCount(int countBeforeAccumulation) {
        return countBeforeAccumulation + count;
    }

    public boolean isMain() {
        return MAIN.equals(menu.getKind());
    }

    public boolean isDessert() {
        return DESSERT.equals(menu.getKind());
    }

    public boolean isNotDrink() {
        return !(DRINK.equals(menu.getKind()));
    }

    public String getMenuName() {
        return menu.getName();
    }

    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OrderItem)) {
            return false;
        }
        OrderItem otherOrderItem = (OrderItem) other;

        return menu == otherOrderItem.menu;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(menu.getName());
    }
}
