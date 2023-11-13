package christmas.domain.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BenefitResultTest {

    @DisplayName("모든 할인 혜택을 적용한 결과를 반환한다.")
    @ParameterizedTest
    @CsvSource({"1,1_000,0,10_115,0,25_000", "3,1_200,8_092,0,1_000,25_000", "27,0,8_092,0,0,25_000"})
    void getResult(int day, long expectedChristmasDiscount, long expectedWeekDayDiscount,
                   long expectedWeekendDiscount, long expectedSpecialDiscount, long expectedGiftBenefit) {
        // given
        OrderItem tapas = new OrderItem("타파스", 1);
        OrderItem christmasPasta = new OrderItem("크리스마스파스타", 2);
        OrderItem seafoodPasta = new OrderItem("해산물파스타", 3);
        OrderItem chocoCake = new OrderItem("초코케이크", 3);
        OrderItem iceCream = new OrderItem("아이스크림", 1);
        OrderItem zeroCoke = new OrderItem("제로콜라", 4);
        Order order = new Order(List.of(tapas, christmasPasta, seafoodPasta, chocoCake, iceCream, zeroCoke));
        VisitDate visitDate = new VisitDate(day);
        BenefitResult benefitResult = new BenefitResult(visitDate, order);
        // when
        Map<Benefit, Long> discountResult = benefitResult.getResult();
        // then
        assertThat(discountResult.getOrDefault(Benefit.CHRISTMAS_DISCOUNT, 0L)).isEqualTo(expectedChristmasDiscount);
        assertThat(discountResult.getOrDefault(Benefit.WEEK_DAY_DISCOUNT, 0L)).isEqualTo(expectedWeekDayDiscount);
        assertThat(discountResult.getOrDefault(Benefit.WEEKEND_DISCOUNT, 0L)).isEqualTo(expectedWeekendDiscount);
        assertThat(discountResult.getOrDefault(Benefit.SPECIAL_DISCOUNT, 0L)).isEqualTo(expectedSpecialDiscount);
        assertThat(discountResult.getOrDefault(Benefit.GIFT_GIVING, 0L)).isEqualTo(expectedGiftBenefit);
    }

    @DisplayName("전체 할인 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({"1,11_115", "3,10_292", "27,8_092"})
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
        BenefitResult benefitResult = new BenefitResult(visitDate, order);
        // when, then
        assertThat(benefitResult.calculateTotalDiscount()).isEqualTo(expectedResult);
    }

    @DisplayName("총혜택 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({"1,36_115", "3,35_292", "27,33_092"})
    void calculateTotalBenefit(int day, long expectedResult) {
        // given
        OrderItem tapas = new OrderItem("타파스", 1);
        OrderItem christmasPasta = new OrderItem("크리스마스파스타", 2);
        OrderItem seafoodPasta = new OrderItem("해산물파스타", 3);
        OrderItem chocoCake = new OrderItem("초코케이크", 3);
        OrderItem iceCream = new OrderItem("아이스크림", 1);
        OrderItem zeroCoke = new OrderItem("제로콜라", 4);
        Order order = new Order(List.of(tapas, christmasPasta, seafoodPasta, chocoCake, iceCream, zeroCoke));
        VisitDate visitDate = new VisitDate(day);
        BenefitResult benefitResult = new BenefitResult(visitDate, order);
        // when, then
        assertThat(benefitResult.calculateTotalBenefit()).isEqualTo(expectedResult);
    }

    @DisplayName("증정 선물을 받았는지 확인한다.")
    @ParameterizedTest
    @CsvSource({"1,false", "20,true"})
    void hasGift(int count, boolean expectedResult) {
        // given
        OrderItem christmasPasta = new OrderItem("크리스마스파스타", count);
        Order order = new Order(List.of(christmasPasta));
        VisitDate visitDate = new VisitDate(3);
        BenefitResult benefitResult = new BenefitResult(visitDate, order);
        // when, then
        assertThat(benefitResult.hasGift()).isEqualTo(expectedResult);
    }
}