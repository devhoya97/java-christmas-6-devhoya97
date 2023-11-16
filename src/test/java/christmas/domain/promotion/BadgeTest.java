package christmas.domain.promotion;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class BadgeTest {

    @DisplayName("혜택 금액에 따라 배지를 부여한다.")
    @ParameterizedTest
    @CsvSource({"3_000,없음", "5_000,별", "10_000,트리", "20_000,산타"})
    void getBadge(long totalBenefit, String expectedResult) {
        // given
        Badge badge = Badge.get(totalBenefit);
        // when, then
        assertThat(badge.getName()).isEqualTo(expectedResult);
    }
}