package christmas.domain;

import static christmas.domain.utils.ErrorMessage.MIN_ORDER_ITEM_ERROR;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Main;
import christmas.domain.menu.Menu;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class OrderItemTest {

    @DisplayName("메뉴 개수가 1개 미만인 주문을 받으면 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-5, -1, 0})
    void createOrderByLessThanOne(int number) {
        assertThatThrownBy(() -> new OrderItem(Main.BBQ_RIBS, number))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format(MIN_ORDER_ITEM_ERROR, OrderItem.MIN_ORDER_ITEM_NUMBER));
    }

    @DisplayName("주문 받은 메뉴와 개수를 고려하여 주문의 가격을 계산한다.")
    @Test
    void calculatePrice() {
        // given
        OrderItem orderItem = new OrderItem(Appetizer.MUSHROOM_SOUP, 3);
        long expectedPrice = 18000;
        // when
        long price =  orderItem.calculatePrice();
        // then
        assertThat(price).isEqualTo(expectedPrice);
    }

    @DisplayName("디저트 메뉴 주문인지 알려준다.")
    @Test
    void isDessert() {
        // given
        OrderItem orderItem = new OrderItem(Dessert.CHOCO_CAKE, 1);
        OrderItem otherOrderItem = new OrderItem(Main.CHRISTMAS_PASTA, 1);
        // when, then
        assertThat(orderItem.isDessert()).isEqualTo(true);
        assertThat(otherOrderItem.isDessert()).isEqualTo(false);
    }

    @DisplayName("메인 메뉴 주문인지 알려준다.")
    @Test
    void isMain() {
        // given
        OrderItem orderItem = new OrderItem(Main.BBQ_RIBS, 1);
        OrderItem otherOrderItem = new OrderItem(Appetizer.TAPAS, 1);
        // when, then
        assertThat(orderItem.isMain()).isEqualTo(true);
        assertThat(otherOrderItem.isMain()).isEqualTo(false);
    }
}