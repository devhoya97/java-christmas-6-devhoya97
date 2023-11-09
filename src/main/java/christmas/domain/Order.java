package christmas.domain;


import christmas.domain.menu.Menu;

public class Order {
    private static void int MIN_NUMBER = 1;
    private Menu menu;
    private int number;

    public Order(Menu menu, int number) {
        validateNumber(number);
        this.menu = menu;
        this.number = number;
    }

    private void validateNumber(int number) {
        if (number < MIN_NUMBER) {
            throw new IllegalArgumentException(String.format("[ERROR] 최소 주문 개수는 %d개 입니다.", MIN_NUMBER));
        }
    }
}
