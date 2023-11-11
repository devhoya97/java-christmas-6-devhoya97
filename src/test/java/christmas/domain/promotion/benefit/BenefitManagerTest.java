package christmas.domain.promotion.benefit;

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

class BenefitManagerTest {

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
        BenefitManager benefitManager = new BenefitManager(visitDate, order);
        // when
        Map<Benefit, Long> discountResult = benefitManager.getBenefitResult();
        // then
        assertThat(discountResult.getOrDefault(Benefit.CHRISTMAS, 0L)).isEqualTo(expectedChristmasDiscount);
        assertThat(discountResult.getOrDefault(Benefit.WEEK_DAY, 0L)).isEqualTo(expectedWeekDayDiscount);
        assertThat(discountResult.getOrDefault(Benefit.WEEKEND, 0L)).isEqualTo(expectedWeekendDiscount);
        assertThat(discountResult.getOrDefault(Benefit.SPECIAL, 0L)).isEqualTo(expectedSpecialDiscount);
        assertThat(discountResult.getOrDefault(Benefit.GIFT, 0L)).isEqualTo(expectedGiftDiscount);
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
        BenefitManager benefitManager = new BenefitManager(visitDate, order);
        // when, then
        assertThat(benefitManager.calculateTotalPriceAfterDiscount()).isEqualTo(expectedResult);
    }

    @DisplayName("총혜택 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({"1,36115", "3,35292", "27,33092"})
    void calculateTotalDiscount(int day, long expectedResult) {
        // given
        OrderItem tapas = new OrderItem(Menu.TAPAS, 1);
        OrderItem christmasPasta = new OrderItem(Menu.CHRISTMAS_PASTA, 2);
        OrderItem seafoodPasta = new OrderItem(Menu.SEAFOOD_PASTA, 3);
        OrderItem chocoCake = new OrderItem(Menu.CHOCO_CAKE, 3);
        OrderItem iceCream = new OrderItem(Menu.ICE_CREAM, 1);
        OrderItem zeroCoke = new OrderItem(Menu.ZERO_COKE, 4);
        Order order = new Order(List.of(tapas, christmasPasta, seafoodPasta, chocoCake, iceCream, zeroCoke));
        VisitDate visitDate = new VisitDate(day);
        BenefitManager benefitManager = new BenefitManager(visitDate, order);
        // when, then
        assertThat(benefitManager.calculateTotalBenefit()).isEqualTo(expectedResult);
    }
}