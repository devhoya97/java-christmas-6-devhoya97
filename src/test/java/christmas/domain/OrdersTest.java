package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Main;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrdersTest {

    @DisplayName("주문 개수의 총 합이 20개가 넘을 경우 예외를 발생시킨다.")
    @Test
    void createOrdersOverMaxNumber() {
        // given
        Order tapas = new Order(Appetizer.TAPAS, 5);
        Order christmasPasta = new Order(Main.CHRISTMAS_PASTA, 5);
        Order chocoCake = new Order(Dessert.CHOCO_CAKE, 5);
        Order zeroCoke = new Order(Drink.ZERO_COKE, 6);
        // when
        // then
        assertThatThrownBy(() -> new Orders(List.of(tapas, christmasPasta, chocoCake, zeroCoke)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("[ERROR] 주문 개수 총합은 최대 %d개 입니다.", 20));
    }
}