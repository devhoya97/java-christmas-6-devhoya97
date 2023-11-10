package christmas.domain;

import static christmas.domain.utils.ErrorMessage.DAY_OUT_OF_RANGE_ERROR;
import static christmas.domain.utils.ErrorMessage.MAX_ORDER_ITEMS_ERROR;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDayTest {

    @DisplayName("방문 날짜가 1~31 사이가 아닐경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-3, 0, 32, 40})
    void creatInvalidVisitDay(int visitDay) {
        assertThatThrownBy(() -> new VisitDay(visitDay))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(String.format(DAY_OUT_OF_RANGE_ERROR, VisitDay.MIN_DAY, VisitDay.MAX_DAY));
    }

    @DisplayName("방문 날짜가 크리스마스 이벤트 기간이 맞는지 알려준다.")
    @ParameterizedTest
    @CsvSource({"1,true", "25,true", "26,false"})
    void creatInvalidVisitDay(int day, boolean expectedResult) {
        // given
        VisitDay visitDay = new VisitDay(day);
        // when
        boolean isInChristmasPromotion = visitDay.isInChristmasPromotion();
        // then
        assertThat(isInChristmasPromotion).isEqualTo(expectedResult);
    }
}