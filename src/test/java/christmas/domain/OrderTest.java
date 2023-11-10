package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        // when
        // then
        assertThatThrownBy(() -> new Order(List.of(tapas, christmasPasta, chocoCake, zeroCoke)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("[ERROR] 주문한 메뉴당 개수의 총합은 최대 %d개까지 가능합니다.", 20));
    }
}