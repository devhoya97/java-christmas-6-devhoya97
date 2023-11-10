package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChristmasPromotionTest {

    @DisplayName("크리스마스 이벤트로 할인 받은 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({"1,10000,1000", "25,10000,3400", "26,10000,0"})
    void calculateDiscount(int day, long totalPrice, long expectedResult) {
        // given
        VisitDay visitDay = new VisitDay(day);
        ChristmasPromotion christmasPromotion = new ChristmasPromotion(visitDay, totalPrice);
        // when
        long discount = christmasPromotion.calculateDiscount();
        // then
        assertThat(discount).isEqualTo(expectedResult);
    }
}