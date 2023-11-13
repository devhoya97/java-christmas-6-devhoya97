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
    @CsvSource({"1,1_000,0,10_115,0,25_000", "3,1_200,8_092,0,1_000,25_000", "27,0,8_092,0,0,25_000"})
    void getResult(int day, long expectedChristmasDiscount, long expectedWeekDayDiscount,
                   long expectedWeekendDiscount, long expectedSpecialDiscount, long expectedGiftDiscount) {
        // given
        OrderItem tapas = new OrderItem("타파스", 1);
        OrderItem christmasPasta = new OrderItem("크리스마스파스타", 2);
        OrderItem seafoodPasta = new OrderItem("해산물파스타", 3);
        OrderItem chocoCake = new OrderItem("초코케이크", 3);
        OrderItem iceCream = new OrderItem("아이스크림", 1);
        OrderItem zeroCoke = new OrderItem("제로콜라", 4);
        Order order = new Order(List.of(tapas, christmasPasta, seafoodPasta, chocoCake, iceCream, zeroCoke));
        VisitDate visitDate = new VisitDate(day);
        BenefitManager benefitManager = new BenefitManager(visitDate, order);
        // when
        Map<Benefit, Long> discountResult = benefitManager.getBenefitResult();
        // then
        assertThat(discountResult.getOrDefault(Benefit.CHRISTMAS_DISCOUNT, 0L)).isEqualTo(expectedChristmasDiscount);
        assertThat(discountResult.getOrDefault(Benefit.WEEK_DAY_DISCOUNT, 0L)).isEqualTo(expectedWeekDayDiscount);
        assertThat(discountResult.getOrDefault(Benefit.WEEKEND_DISCOUNT, 0L)).isEqualTo(expectedWeekendDiscount);
        assertThat(discountResult.getOrDefault(Benefit.SPECIAL_DISCOUNT, 0L)).isEqualTo(expectedSpecialDiscount);
        assertThat(discountResult.getOrDefault(Benefit.GIFT_GIVING, 0L)).isEqualTo(expectedGiftDiscount);
    }

    @DisplayName("할인 후 예상 결제 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({"1,211_385", "3,212_208", "27,214_408"})
    void calculateTotalDiscountedPrice(int day, long expectedResult) {
        // given
        OrderItem tapas = new OrderItem("타파스", 1);
        OrderItem christmasPasta = new OrderItem("크리스마스파스타", 2);
        OrderItem seafoodPasta = new OrderItem("해산물파스타", 3);
        OrderItem chocoCake = new OrderItem("초코케이크", 3);
        OrderItem iceCream = new OrderItem("아이스크림", 1);
        OrderItem zeroCoke = new OrderItem("제로콜라", 4);
        Order order = new Order(List.of(tapas, christmasPasta, seafoodPasta, chocoCake, iceCream, zeroCoke));
        VisitDate visitDate = new VisitDate(day);
        BenefitManager benefitManager = new BenefitManager(visitDate, order);
        // when, then
        assertThat(benefitManager.calculateDiscountedTotalPrice()).isEqualTo(expectedResult);
    }

    @DisplayName("총혜택 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({"1,36_115", "3,35_292", "27,33_092"})
    void calculateTotalDiscount(int day, long expectedResult) {
        // given
        OrderItem tapas = new OrderItem("타파스", 1);
        OrderItem christmasPasta = new OrderItem("크리스마스파스타", 2);
        OrderItem seafoodPasta = new OrderItem("해산물파스타", 3);
        OrderItem chocoCake = new OrderItem("초코케이크", 3);
        OrderItem iceCream = new OrderItem("아이스크림", 1);
        OrderItem zeroCoke = new OrderItem("제로콜라", 4);
        Order order = new Order(List.of(tapas, christmasPasta, seafoodPasta, chocoCake, iceCream, zeroCoke));
        VisitDate visitDate = new VisitDate(day);
        BenefitManager benefitManager = new BenefitManager(visitDate, order);
        // when, then
        assertThat(benefitManager.calculateTotalBenefit()).isEqualTo(expectedResult);
    }

    @DisplayName("증정 선물을 받았는지 확인한다.")
    @ParameterizedTest
    @CsvSource({"1,false", "20,true"})
    void hasGift(int count, boolean expectedResult) {
        // given
        OrderItem christmasPasta = new OrderItem("크리스마스파스타", count);
        Order order = new Order(List.of(christmasPasta));
        VisitDate visitDate = new VisitDate(3);
        BenefitManager benefitManager = new BenefitManager(visitDate, order);
        // when, then
        assertThat(benefitManager.hasGift()).isEqualTo(expectedResult);
    }
}