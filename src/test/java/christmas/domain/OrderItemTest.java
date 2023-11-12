package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class OrderItemTest {

    @DisplayName("메뉴 개수가 1개 미만인 주문을 받으면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 0})
    void createOrderByLessThanOne(int number) {
        assertThatThrownBy(() -> new OrderItem(Menu.BBQ_RIBS, number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 받은 메뉴와 개수를 고려하여 주문의 가격을 계산한다.")
    @Test
    void calculatePrice() {
        // given
        OrderItem orderItem = new OrderItem(Menu.MUSHROOM_SOUP, 3);
        long expectedPrice = 18_000;
        // when
        long price =  orderItem.calculatePrice();
        // then
        assertThat(price).isEqualTo(expectedPrice);
    }

    @DisplayName("디저트 메뉴 주문인지 알려준다.")
    @Test
    void isDessert() {
        // given
        OrderItem orderItem = new OrderItem(Menu.CHOCO_CAKE, 1);
        OrderItem otherOrderItem = new OrderItem(Menu.CHRISTMAS_PASTA, 1);
        // when, then
        assertThat(orderItem.isDessert()).isEqualTo(true);
        assertThat(otherOrderItem.isDessert()).isEqualTo(false);
    }

    @DisplayName("메인 메뉴 주문인지 알려준다.")
    @Test
    void isMain() {
        // given
        OrderItem orderItem = new OrderItem(Menu.BBQ_RIBS, 1);
        OrderItem otherOrderItem = new OrderItem(Menu.TAPAS, 1);
        // when, then
        assertThat(orderItem.isMain()).isEqualTo(true);
        assertThat(otherOrderItem.isMain()).isEqualTo(false);
    }

    @DisplayName("메뉴이름 개수 포멧으로 String을 반환한다.")
    @Test
    void returnFormattedString() {
        // given
        OrderItem christmasPasta = new OrderItem(Menu.CHRISTMAS_PASTA, 2);
        String expectedResult = "크리스마스파스타 2개";
        // when, then
        assertThat(christmasPasta.toString()).isEqualTo(expectedResult);
    }
}