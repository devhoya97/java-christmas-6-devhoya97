package christmas.domain;

import static christmas.domain.utils.ErrorMessage.MAX_ORDER_ITEMS_ERROR;

import java.util.List;

public class Order {
    public static final int MAX_TOTAL_ORDER_ITEMS = 20;
    List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        validateTotalOrderNumber(orderItems);
        this.orderItems = orderItems;
    }

    private void validateTotalOrderNumber(List<OrderItem> orderItems) {
        int totalOrderItems = 0;
        for (OrderItem orderItem : orderItems) {
            totalOrderItems = orderItem.accumulateNumber(totalOrderItems);
        }
        if (totalOrderItems > MAX_TOTAL_ORDER_ITEMS) {
            throw new IllegalArgumentException(
                    String.format(MAX_ORDER_ITEMS_ERROR, MAX_TOTAL_ORDER_ITEMS));
        }
    }

    public long calculateTotalPrice() {
        return orderItems.stream()
                .mapToLong(OrderItem::calculatePrice)
                .sum();
    }

    public int countDesserts() {
        int count = 0;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.isDessert()) {
                count = orderItem.accumulateNumber(count);
            }
         }
        return count;
    }

    public int countMains() {
        int count = 0;
        for (OrderItem orderItem : orderItems) {
            if (orderItem.isMain()) {
                count = orderItem.accumulateNumber(count);
            }
        }
        return count;
    }
}
