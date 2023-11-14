package christmas.domain.order;


import static christmas.domain.utils.ErrorMessage.INVALID_ORDER_ERROR;

import java.util.Objects;

public class OrderItem {
    private static final int MIN_COUNT = 1;

    private final Menu menu;
    private final int count;

    public OrderItem(String menuName, int count) {
        this.menu = findPreparedMenu(menuName);
        validateCount(count);
        this.count = count;
    }

    private Menu findPreparedMenu(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menuName.equals(menu.getName())) {
                return menu;
            }
        }
        throw new IllegalArgumentException(INVALID_ORDER_ERROR);
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
        return menu.isMain();
    }

    public boolean isDessert() {
        return menu.isDessert();
    }

    public boolean isDrink() {
        return menu.isDrink();
    }

    // OutputView를 위한 getter
    public String getMenuName() {
        return menu.getName();
    }

    // OutputView를 위한 getter
    public int getCount() {
        return count;
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof OrderItem otherOrderItem) {
            return menu == otherOrderItem.menu;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(menu.getName());
    }
}
