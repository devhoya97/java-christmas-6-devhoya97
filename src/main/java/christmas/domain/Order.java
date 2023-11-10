package christmas.domain;

import java.util.List;

public class Order {
    private static final int MAX_TOTAL_ORDER_ITEMS = 20;
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
                    String.format("[ERROR] 주문한 메뉴당 개수의 총합은 최대 %d개까지 가능합니다.", MAX_TOTAL_ORDER_ITEMS));
        }
    }
}
