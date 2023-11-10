package christmas.domain;


import christmas.domain.menu.Menu;

public class OrderItem {
    private static final int MIN_NUMBER = 1;
    private final Menu menu;
    private final int number;

    public OrderItem(Menu menu, int number) {
        validateNumber(number);
        this.menu = menu;
        this.number = number;
    }

    private void validateNumber(int number) {
        if (number < MIN_NUMBER) {
            throw new IllegalArgumentException(String.format("[ERROR] 선택한 메뉴의 개수가 %d개보다 작습니다.", MIN_NUMBER));
        }
    }

    public long calculatePrice() {
        return menu.getPrice() * number;
    }

    public int getNumber() {
        return number;
    }
}
