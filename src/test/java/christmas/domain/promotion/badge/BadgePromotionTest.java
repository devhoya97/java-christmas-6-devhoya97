package christmas.domain.promotion.badge;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgePromotionTest {

    @DisplayName("혜택 금액에 따라 배지를 부여한다.")
    @ParameterizedTest
    @CsvSource({"3000,없음", "5000,별", "10000,트리", "20000,산타"})
    void getBadge(int totalDiscount, String expectedResult) {
        // given
        BadgePromotion badgePromotion = new BadgePromotion(totalDiscount);
        // when, then
        assertThat(badgePromotion.getBadge().getName()).isEqualTo(expectedResult);
    }
}