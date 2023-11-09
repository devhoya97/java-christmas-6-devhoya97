package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.domain.menu.Main;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderTest {

    @DisplayName("개수가 1개 미만인 주문을 받으면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 0})
    void createOrderByLessThanOne(int number) {
        assertThatThrownBy(() -> new Order(Main.BBQ_RIBS, number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format("[ERROR] 최소 주문 개수는 %d개 입니다.", 1));
    }

    @Test
    void calculatePrice() {
    }
}