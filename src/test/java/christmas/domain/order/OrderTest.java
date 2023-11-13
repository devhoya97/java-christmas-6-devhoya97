package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class OrderTest {

    @DisplayName("주문한 메뉴당 개수의 총합이 최대 개수를 넘을 경우 예외를 발생시킨다.")
    @Test
    void createOrderOverMaxNumber() {
        // given
        OrderItem tapas = new OrderItem("타파스", 5);
        OrderItem christmasPasta = new OrderItem("크리스마스파스타", 5);
        OrderItem chocoCake = new OrderItem("초코케이크", 5);
        OrderItem zeroCoke = new OrderItem("제로콜라", 6);
        // when, then
        assertThatThrownBy(() -> new Order(List.of(tapas, christmasPasta, chocoCake, zeroCoke)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("주문 메뉴에 음료밖에 없을 시 예외를 발생시킨다.")
    @Test
    void createOnlyDrinkOrder() {
        // given
        OrderItem zeroCoke = new OrderItem("제로콜라", 5);
        OrderItem redWine = new OrderItem("레드와인", 1);
        // when, then
        assertThatThrownBy(() -> new Order(List.of(zeroCoke, redWine)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("중복된 주문 메뉴를 받은 경우 예외를 발생시킨다.")
    @Test
    void createOrderByDuplicatedMenu() {
        // given
        OrderItem caesarSalad = new OrderItem("시저샐러드", 5);
        OrderItem otherCaesarSalad = new OrderItem("시저샐러드", 1);
        // when, then
        assertThatThrownBy(() -> new Order(List.of(caesarSalad, otherCaesarSalad)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
    }

    @DisplayName("전체 주문 금액을 계산한다.")
    @Test
    void calculateTotalPrice() {
        // given
        OrderItem tapas = new OrderItem("타파스", 1);
        OrderItem christmasPasta = new OrderItem("크리스마스파스타", 2);
        OrderItem chocoCake = new OrderItem("초코케이크", 3);
        OrderItem zeroCoke = new OrderItem("제로콜라", 4);
        Order order = new Order(List.of(tapas, christmasPasta, chocoCake, zeroCoke));
        long expectedResult = 5_500L + 50_000L + 45_000L + 12_000L;
        // when
        long totalPrice = order.calculateTotalPrice();
        // then
        assertThat(totalPrice).isEqualTo(expectedResult);
    }

    @DisplayName("주문 중 디저트 메뉴의 총 개수를 계산한다.")
    @Test
    void countDesserts() {
        // given
        OrderItem tapas = new OrderItem("타파스", 1);
        OrderItem christmasPasta = new OrderItem("크리스마스파스타", 2);
        OrderItem chocoCake = new OrderItem("초코케이크", 3);
        OrderItem iceCream = new OrderItem("아이스크림", 1);
        OrderItem zeroCoke = new OrderItem("제로콜라", 4);
        Order order = new Order(List.of(tapas, christmasPasta, chocoCake, iceCream, zeroCoke));
        long expectedResult = 4;
        // when, then
        assertThat(order.countDesserts()).isEqualTo(expectedResult);
    }

    @DisplayName("주문 중 메인 메뉴의 총 개수를 계산한다.")
    @Test
    void countMains() {
        // given
        OrderItem tapas = new OrderItem("타파스", 1);
        OrderItem christmasPasta = new OrderItem("크리스마스파스타", 2);
        OrderItem TBoneSteak = new OrderItem("티본스테이크", 3);
        OrderItem iceCream = new OrderItem("아이스크림", 1);
        OrderItem zeroCoke = new OrderItem("제로콜라", 2);
        Order order = new Order(List.of(tapas, christmasPasta, TBoneSteak, iceCream, zeroCoke));
        long expectedResult = 5;
        // when, then
        assertThat(order.countMains()).isEqualTo(expectedResult);
    }
}