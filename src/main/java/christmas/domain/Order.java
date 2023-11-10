package christmas.domain;

import static christmas.domain.utils.ConstantValue.MAX_TOTAL_ORDER_ITEMS;
import static christmas.domain.utils.ErrorMessage.MAX_ORDER_ITEMS_ERROR;

import java.util.List;

public class Order {
    List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        validateTotalOrderNumber(orderItems);
        this.orderItems = orderItems;
    }

    private void validateTotalOrderNumber(List<OrderItem> orderItems) {
        int totalOrderItems = orderItems.stream()
                .mapToInt(OrderItem::getNumber)
                .sum();
        if (totalOrderItems > MAX_TOTAL_ORDER_ITEMS) {
            throw new IllegalArgumentException(
                    String.format(MAX_ORDER_ITEMS_ERROR, MAX_TOTAL_ORDER_ITEMS));
        }
    }
}
