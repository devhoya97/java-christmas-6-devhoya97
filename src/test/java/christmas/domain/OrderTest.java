package christmas.domain;

import static christmas.domain.utils.ErrorMessage.MAX_ORDER_ITEMS_ERROR;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Main;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @DisplayName("주문한 메뉴당 개수의 총합이 최대 개수를 넘을 경우 예외를 발생시킨다.")
    @Test
    void createOrdersOverMaxNumber() {
        // given
        OrderItem tapas = new OrderItem(Appetizer.TAPAS, 5);
        OrderItem christmasPasta = new OrderItem(Main.CHRISTMAS_PASTA, 5);
        OrderItem chocoCake = new OrderItem(Dessert.CHOCO_CAKE, 5);
        OrderItem zeroCoke = new OrderItem(Drink.ZERO_COKE, 6);
        // when, then
        assertThatThrownBy(() -> new Order(List.of(tapas, christmasPasta, chocoCake, zeroCoke)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format(MAX_ORDER_ITEMS_ERROR, Order.MAX_TOTAL_ORDER_ITEMS));
    }

    @DisplayName("전체 주문 금액을 계산한다.")
    @Test
    void calculateTotalPrice() {
        // given
        OrderItem tapas = new OrderItem(Appetizer.TAPAS, 1);
        OrderItem christmasPasta = new OrderItem(Main.CHRISTMAS_PASTA, 2);
        OrderItem chocoCake = new OrderItem(Dessert.CHOCO_CAKE, 3);
        OrderItem zeroCoke = new OrderItem(Drink.ZERO_COKE, 4);
        Order order = new Order(List.of(tapas, christmasPasta, chocoCake, zeroCoke));
        long expectedResult = 5500 + 50000 + 45000 + 12000;
        // when
        long totalPrice = order.calculateTotalPrice();
        // then
        assertThat(totalPrice).isEqualTo(expectedResult);
    }
}