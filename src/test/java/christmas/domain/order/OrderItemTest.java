package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderItemTest {

    @DisplayName("준비되지 않은 메뉴를 주문할 시 예외를 발생시킨다.")
    @Test
    void createOrderByNotPreparedMenu() {
        assertThatThrownBy(() -> new OrderItem("깐풍기", 1))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("메뉴 개수가 1개 미만인 주문을 받으면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 0})
    void createOrderByLessThanOne(int number) {
        assertThatThrownBy(() -> new OrderItem("바비큐립", number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 받은 메뉴와 개수를 고려하여 주문의 가격을 계산한다.")
    @Test
    void calculatePrice() {
        // given
        OrderItem orderItem = new OrderItem("양송이수프", 3);
        long expectedPrice = 18_000;
        // when
        long price = orderItem.calculatePrice();
        // then
        assertThat(price).isEqualTo(expectedPrice);
    }

    @DisplayName("주문 받은 메뉴의 개수를 외부 값과 합하여 반환한다.")
    @Test
    void accumulateCount() {
        // given
        OrderItem orderItem = new OrderItem("양송이수프", 3);
        int countBeforeAccumulation = 5;
        int expectedResult = 8;
        // when, then
        assertThat(orderItem.accumulateCount(countBeforeAccumulation)).isEqualTo(expectedResult);
    }

    @DisplayName("메인 메뉴 주문인지 알려준다.")
    @Test
    void isMain() {
        // given
        OrderItem orderItem = new OrderItem("바비큐립", 1);
        OrderItem otherOrderItem = new OrderItem("타파스", 1);
        // when, then
        assertThat(orderItem.isMain()).isEqualTo(true);
        assertThat(otherOrderItem.isMain()).isEqualTo(false);
    }

    @DisplayName("디저트 메뉴 주문인지 알려준다.")
    @Test
    void isDessert() {
        // given
        OrderItem orderItem = new OrderItem("초코케이크", 1);
        OrderItem otherOrderItem = new OrderItem("크리스마스파스타", 1);
        // when, then
        assertThat(orderItem.isDessert()).isEqualTo(true);
        assertThat(otherOrderItem.isDessert()).isEqualTo(false);
    }

    @DisplayName("음료 메뉴 주문인지 알려준다.")
    @Test
    void isDrink() {
        // given
        OrderItem orderItem = new OrderItem("제로콜라", 1);
        OrderItem otherOrderItem = new OrderItem("크리스마스파스타", 1);
        // when, then
        assertThat(orderItem.isDrink()).isEqualTo(true);
        assertThat(otherOrderItem.isDrink()).isEqualTo(false);
    }

    @DisplayName("같은 메뉴면, 같은 주문이라고 판단한다.")
    @Test
    void equals() {
        // given
        OrderItem orderItem = new OrderItem("바비큐립", 1);
        OrderItem otherOrderItem = new OrderItem("바비큐립", 2);
        OrderItem anotherOrderItem = new OrderItem("제로콜라", 1);
        // when, then
        assertThat(orderItem.equals(otherOrderItem)).isEqualTo(true);
        assertThat(orderItem.equals(anotherOrderItem)).isEqualTo(false);
    }

    @DisplayName("같은 메뉴면, 같은 해시코드를 갖는다.")
    @Test
    void gethashCode() {
        // given
        OrderItem orderItem = new OrderItem("바비큐립", 1);
        OrderItem otherOrderItem = new OrderItem("바비큐립", 2);
        OrderItem anotherOrderItem = new OrderItem("제로콜라", 1);
        // when, then
        assertThat(orderItem.hashCode()).isEqualTo(otherOrderItem.hashCode());
        assertThat(orderItem.hashCode()).isNotEqualTo(anotherOrderItem.hashCode());
    }
}