package christmas.domain;


import static christmas.domain.utils.ErrorMessage.INVALID_MENU_ERROR;

import christmas.domain.menu.Menu;
import java.util.Objects;

public class OrderItem {
    private static final String MAIN = "메인";
    private static final String DESSERT = "디저트";
    private static final String DRINK = "음료";
    private static final int MIN_ORDER_ITEM_NUMBER = 1;

    private final Menu menu;
    private final int number;

    public OrderItem(Menu menu, int number) {
        validateNumber(number);
        this.menu = menu;
        this.number = number;
    }

    private void validateNumber(int number) {
        if (number < MIN_ORDER_ITEM_NUMBER) {
            throw new IllegalArgumentException(String.format(INVALID_MENU_ERROR));
        }
    }

    public long calculatePrice() {
        return menu.getPrice() * number;
    }

    public int accumulateNumber(int totalOrderItems) {
        return totalOrderItems + number;
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

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof OrderItem)) {
            return false;
        }
        String menuName = menu.getName();

        OrderItem otherOrderItem = (OrderItem) other;
        String otherMenuName = otherOrderItem.menu.getName();

        return menuName.equals(otherMenuName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(menu.getName());
    }

    @Override
    public String toString() {
        return menu.getName() + " " + number + "개";
    }
}
