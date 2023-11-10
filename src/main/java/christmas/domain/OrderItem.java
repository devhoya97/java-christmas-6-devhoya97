package christmas.domain;


import static christmas.domain.utils.ErrorMessage.MIN_ORDER_ITEM_ERROR;

import christmas.domain.menu.Menu;

public class OrderItem {
    public static final int MIN_ORDER_ITEM_NUMBER = 1;

    private final Menu menu;
    private final int number;

    public OrderItem(Menu menu, int number) {
        validateNumber(number);
        this.menu = menu;
        this.number = number;
    }

    private void validateNumber(int number) {
        if (number < MIN_ORDER_ITEM_NUMBER) {
            throw new IllegalArgumentException(String.format(MIN_ORDER_ITEM_ERROR, MIN_ORDER_ITEM_NUMBER));
        }
    }

    public long calculatePrice() {
        return menu.getPrice() * number;
    }

    public int accumulate(int totalOrderItems) {
        return totalOrderItems + number;
    }
}