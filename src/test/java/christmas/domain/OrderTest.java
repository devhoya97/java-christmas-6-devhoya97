package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.Menu;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @DisplayName("주문한 메뉴당 개수의 총합이 최대 개수를 넘을 경우 예외를 발생시킨다.")
    @Test
    void createOrderOverMaxNumber() {
        // given
        OrderItem tapas = new OrderItem(Menu.TAPAS, 5);
        OrderItem christmasPasta = new OrderItem(Menu.CHRISTMAS_PASTA, 5);
        OrderItem chocoCake = new OrderItem(Menu.CHOCO_CAKE, 5);
        OrderItem zeroCoke = new OrderItem(Menu.ZERO_COKE, 6);
        // when, then
        assertThatThrownBy(() -> new Order(List.of(tapas, christmasPasta, chocoCake, zeroCoke)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 주문한 메뉴당 개수의 총합은 최대 20개까지 가능합니다.");
    }

    @DisplayName("주문 메뉴에 음료밖에 없을 시 예외를 발생시킨다.")
    @Test
    void createOnlyDrinkOrder() {
        // given
        OrderItem zeroCoke = new OrderItem(Menu.ZERO_COKE, 5);
        OrderItem redWine = new OrderItem(Menu.RED_WINE, 1);
        // when, then
        assertThatThrownBy(() -> new Order(List.of(zeroCoke, redWine)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 음료만 주문하는 것은 불가능합니다.");
    }

    @DisplayName("전체 주문 금액을 계산한다.")
    @Test
    void calculateTotalPrice() {
        // given
        OrderItem tapas = new OrderItem(Menu.TAPAS, 1);
        OrderItem christmasPasta = new OrderItem(Menu.CHRISTMAS_PASTA, 2);
        OrderItem chocoCake = new OrderItem(Menu.CHOCO_CAKE, 3);
        OrderItem zeroCoke = new OrderItem(Menu.ZERO_COKE, 4);
        Order order = new Order(List.of(tapas, christmasPasta, chocoCake, zeroCoke));
        long expectedResult = 5500 + 50000 + 45000 + 12000;
        // when
        long totalPrice = order.calculateTotalPrice();
        // then
        assertThat(totalPrice).isEqualTo(expectedResult);
    }

    @DisplayName("주문 중 디저트 메뉴의 총 개수를 계산한다.")
    @Test
    void countDesserts() {
        // given
        OrderItem tapas = new OrderItem(Menu.TAPAS, 1);
        OrderItem christmasPasta = new OrderItem(Menu.CHRISTMAS_PASTA, 2);
        OrderItem chocoCake = new OrderItem(Menu.CHOCO_CAKE, 3);
        OrderItem iceCream = new OrderItem(Menu.ICE_CREAM, 1);
        OrderItem zeroCoke = new OrderItem(Menu.ZERO_COKE, 4);
        Order order = new Order(List.of(tapas, christmasPasta, chocoCake, iceCream, zeroCoke));
        long expectedResult = 4;
        // when, then
        assertThat(order.countDesserts()).isEqualTo(expectedResult);
    }

    @DisplayName("주문 중 메인 메뉴의 총 개수를 계산한다.")
    @Test
    void countMains() {
        // given
        OrderItem tapas = new OrderItem(Menu.TAPAS, 1);
        OrderItem christmasPasta = new OrderItem(Menu.CHRISTMAS_PASTA, 2);
        OrderItem TBoneSteak = new OrderItem(Menu.T_BONE_STEAK, 3);
        OrderItem iceCream = new OrderItem(Menu.ICE_CREAM, 1);
        OrderItem zeroCoke = new OrderItem(Menu.ZERO_COKE, 2);
        Order order = new Order(List.of(tapas, christmasPasta, TBoneSteak, iceCream, zeroCoke));
        long expectedResult = 5;
        // when, then
        assertThat(order.countMains()).isEqualTo(expectedResult);
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