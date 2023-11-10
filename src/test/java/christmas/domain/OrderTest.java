package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderTest {

    @DisplayName("메뉴 개수가 1개 미만인 주문을 받으면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 0})
    void createOrderByLessThanOne(int number) {
        assertThatThrownBy(() -> new Order(Main.BBQ_RIBS, number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("[ERROR] 주문 가능한 최소 메뉴 개수는 %d개 입니다.", 1));
    }

    @DisplayName("주문 받은 메뉴와 개수를 고려하여 주문의 가격을 계산한다.")
    @Test
    void calculatePrice() {
        // given
        Order order = new Order(Appetizer.MUSHROOM_SOUP, 3);
        long expectedPrice = 18000;
        // when
        long price =  order.calculatePrice();
        // then
        assertThat(price).isEqualTo(expectedPrice);
    }
}