package christmas.domain;


import christmas.domain.menu.Menu;

public class Order {
    private static final int MIN_NUMBER = 1;
    private final Menu menu;
    private final int number;

    public Order(Menu menu, int number) {
        validateNumber(number);
        this.menu = menu;
        this.number = number;
    }

    private void validateNumber(int number) {
        if (number < MIN_NUMBER) {
            throw new IllegalArgumentException(String.format("[ERROR] 주문 가능한 최소 메뉴 개수는 %d개 입니다.", MIN_NUMBER));
        }
    }

    public long calculatePrice() {
        return menu.getPrice() * number;
    }

    public int getNumber() {
        return number;
    }
}
