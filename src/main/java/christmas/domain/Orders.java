package christmas.domain;

import java.util.List;

public class Orders {
    private static final int MAX_TOTAL_ORDERS_NUMBER = 20;
    List<Order> orders;

    public Orders(List<Order> orders) {
        validateTotalOrderNumber(orders);
        this.orders = orders;
    }

    private void validateTotalOrderNumber(List<Order> orders) {
        int totalOrderNumber = orders.stream()
                .mapToInt(Order::getNumber)
                .sum();
        if (totalOrderNumber > MAX_TOTAL_ORDERS_NUMBER) {
            throw new IllegalArgumentException(
                    String.format("[ERROR] 주문 가능한 메뉴 개수는 최대 %d개 입니다.", MAX_TOTAL_ORDERS_NUMBER));
        }
    }
}
