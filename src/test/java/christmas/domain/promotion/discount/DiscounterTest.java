package christmas.domain.promotion.discount;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.domain.VisitDate;
import christmas.domain.menu.Menu;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiscounterTest {

    @DisplayName("모든 할인 혜택을 적용한 결과를 반환한다.")
    @ParameterizedTest
    @CsvSource({"1,1000,0,10115,0,25000", "3,1200,8092,0,1000,25000", "27,0,8092,0,0,25000"})
    void getResult(int day, long expectedChristmasDiscount, long expectedWeekDayDiscount,
                   long expectedWeekendDiscount, long expectedSpecialDiscount, long expectedGiftDiscount) {
        // given
        OrderItem tapas = new OrderItem(Menu.TAPAS, 1);
        OrderItem christmasPasta = new OrderItem(Menu.CHRISTMAS_PASTA, 2);
        OrderItem seafoodPasta = new OrderItem(Menu.SEAFOOD_PASTA, 3);
        OrderItem chocoCake = new OrderItem(Menu.CHOCO_CAKE, 3);
        OrderItem iceCream = new OrderItem(Menu.ICE_CREAM, 1);
        OrderItem zeroCoke = new OrderItem(Menu.ZERO_COKE, 4);
        Order order = new Order(List.of(tapas, christmasPasta, seafoodPasta, chocoCake, iceCream, zeroCoke));
        VisitDate visitDate = new VisitDate(day);
        Discounter discounter = new Discounter(visitDate, order);
        // when
        Map<Discount, Long> discountResult = discounter.getResult();
        // then
        assertThat(discountResult.getOrDefault(Discount.CHRISTMAS, 0L)).isEqualTo(expectedChristmasDiscount);
        assertThat(discountResult.getOrDefault(Discount.WEEK_DAY, 0L)).isEqualTo(expectedWeekDayDiscount);
        assertThat(discountResult.getOrDefault(Discount.WEEKEND, 0L)).isEqualTo(expectedWeekendDiscount);
        assertThat(discountResult.getOrDefault(Discount.SPECIAL, 0L)).isEqualTo(expectedSpecialDiscount);
        assertThat(discountResult.getOrDefault(Discount.GIFT, 0L)).isEqualTo(expectedGiftDiscount);
    }

    @DisplayName("할인 후 예상 결제 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({"1,211385", "3,212208", "27,214408"})
    void calculateTotalDiscountedPrice(int day, long expectedResult) {
        // given
        OrderItem tapas = new OrderItem(Menu.TAPAS, 1);
        OrderItem christmasPasta = new OrderItem(Menu.CHRISTMAS_PASTA, 2);
        OrderItem seafoodPasta = new OrderItem(Menu.SEAFOOD_PASTA, 3);
        OrderItem chocoCake = new OrderItem(Menu.CHOCO_CAKE, 3);
        OrderItem iceCream = new OrderItem(Menu.ICE_CREAM, 1);
        OrderItem zeroCoke = new OrderItem(Menu.ZERO_COKE, 4);
        Order order = new Order(List.of(tapas, christmasPasta, seafoodPasta, chocoCake, iceCream, zeroCoke));
        VisitDate visitDate = new VisitDate(day);
        Discounter discounter = new Discounter(visitDate, order);
        // when, then
        assertThat(discounter.calculateTotalDiscountedPrice()).isEqualTo(expectedResult);
    }
}