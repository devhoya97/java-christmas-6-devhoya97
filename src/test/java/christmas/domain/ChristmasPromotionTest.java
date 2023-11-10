package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChristmasPromotionTest {

    @DisplayName("크리스마스 이벤트를 적용한 금액을 계산한다.")
    @ParameterizedTest
    @CsvSource({"1,10000,9000", "25,10000,6600", "0,10000,10000, 26,10000,10000"})
    void canReceive(int day, int totalPrice, int expectedResult) {
        // given
        ChristmasPromotion christmasPromotion = new ChristmasPromotion(day, totalPrice);
        // when
        int promotionAppliedPrice = christmasPromotion.calculateDiscountedPrice();
        assertThat(promotionAppliedPrice).isEqualTo(expectedResult);
    }
}