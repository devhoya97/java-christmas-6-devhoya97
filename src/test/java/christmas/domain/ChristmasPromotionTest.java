package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ChristmasPromotionTest {

    @DisplayName("날짜와 금액을 기반으로 크리스마스 이벤트 조건을 충족하는지 판단한다.")
    @ParameterizedTest
    @CsvSource({"0,20000,false", "1,5000,false", "15,10000,true"})
    void canReceive(int day, int totalPrice, boolean expectedResult) {
        // given
        ChristmasPromotion christmasPromotion = new ChristmasPromotion(day, totalPrice);
        // when
        boolean canReceivePromotion = christmasPromotion.canReceive();;
        assertThat(canReceivePromotion).isEqualTo(expectedResult);
    }
}