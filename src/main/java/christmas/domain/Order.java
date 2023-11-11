package christmas.domain;

import static christmas.domain.utils.ErrorMessage.INVALID_MENU_ERROR;
import static christmas.domain.utils.ErrorMessage.MAX_TOTAL_COUNT_ERROR;
import static christmas.domain.utils.ErrorMessage.ONLY_DRINK_ERROR;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

public class Order {
    private static final int MAX_TOTAL_COUNT = 20;
    private static final String NEW_LINE = "\n";
    List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        validateTotalCount(orderItems);
        validateNotOnlyDrink(orderItems);
        validateNotDuplicatedMenu(orderItems);
        this.orderItems = orderItems;
    }

    private void validateTotalCount(List<OrderItem> orderItems) {
        int totalCount = 0;
        for (OrderItem orderItem : orderItems) {
            totalCount = orderItem.accumulateCount(totalCount);
        }
        if (totalCount > MAX_TOTAL_COUNT) {
            throw new IllegalArgumentException(String.format(MAX_TOTAL_COUNT_ERROR, MAX_TOTAL_COUNT));
        }
    }

    private void validateNotOnlyDrink(List<OrderItem> orderItems) {
        long notDrinkCount = orderItems.stream()
                .filter(OrderItem::isNotDrink)
                .count();
        if (notDrinkCount == 0) {
            throw new IllegalArgumentException(ONLY_DRINK_ERROR);
        }
    }

    private void validateNotDuplicatedMenu(List<OrderItem> orderItems) {
        Set<OrderItem> uniqueOrderItems = new HashSet<>(orderItems);
        if (uniqueOrderItems.size() != orderItems.size()) {
            throw new IllegalArgumentException(INVALID_MENU_ERROR);
        }
    }

    public long calculateTotalPrice() {
        return orderItems.stream()
                .mapToLong(OrderItem::calculatePrice)
                .sum();
    }

    public int countDesserts() {
        int dessertCount = 0;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.isDessert()) {
                dessertCount = orderItem.accumulateCount(dessertCount);
            }
         }
        return dessertCount;
    }

    public int countMains() {
        int mainCount = 0;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.isMain()) {
                mainCount = orderItem.accumulateCount(mainCount);
            }
        }
        return mainCount;
    }

    @Override
    public String toString() {
        StringJoiner orderResult = new StringJoiner(NEW_LINE);
        orderItems.stream()
                .map(OrderItem::toString)
                .forEach(orderResult::add);
        return orderResult.toString();
    }
}
