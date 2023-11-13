package christmas.domain;

import static christmas.domain.utils.ErrorMessage.INVALID_ORDER_ERROR;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Order {
    private static final int MAX_TOTAL_COUNT = 20;
    private static final int NO_COUNT = 0;
    List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        validateTotalCount(orderItems);
        validateNotOnlyDrink(orderItems);
        validateNotDuplicatedMenu(orderItems);
        this.orderItems = orderItems;
    }

    private void validateTotalCount(List<OrderItem> orderItems) {
        int totalCount = NO_COUNT;
        for (OrderItem orderItem : orderItems) {
            totalCount = orderItem.accumulateCount(totalCount);
        }
        if (totalCount > MAX_TOTAL_COUNT) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR);
        }
    }

    private void validateNotOnlyDrink(List<OrderItem> orderItems) {
        List<OrderItem> notDrinkOrderItems = orderItems.stream()
                .filter(orderItem -> !(orderItem.isDrink()))
                .toList();
        if (notDrinkOrderItems.isEmpty()) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR);
        }
    }

    private void validateNotDuplicatedMenu(List<OrderItem> orderItems) {
        Set<OrderItem> uniqueOrderItems = new HashSet<>(orderItems);
        if (uniqueOrderItems.size() != orderItems.size()) {
            throw new IllegalArgumentException(INVALID_ORDER_ERROR);
        }
    }

    public long calculateTotalPrice() {
        return orderItems.stream()
                .mapToLong(OrderItem::calculatePrice)
                .sum();
    }

    public int countDesserts() {
        int dessertCount = NO_COUNT;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.isDessert()) {
                dessertCount = orderItem.accumulateCount(dessertCount);
            }
         }
        return dessertCount;
    }

    public int countMains() {
        int mainCount = NO_COUNT;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.isMain()) {
                mainCount = orderItem.accumulateCount(mainCount);
            }
        }
        return mainCount;
    }

    // OutputView를 위한 getter
    public List<OrderItem> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }
}
