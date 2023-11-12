package christmas.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

class VisitDateTest {

    @DisplayName("방문 날짜가 1~31 사이가 아닐경우 예외를 발생시킨다.")
    @ParameterizedTest
    @ValueSource(ints = {-3, 0, 32, 40})
    void creatInvalidVisitDay(int visitDay) {
        assertThatThrownBy(() -> new VisitDate(visitDay))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
    }

    @DisplayName("방문 날짜가 크리스마스 이벤트 기간이 맞는지 알려준다.")
    @ParameterizedTest
    @CsvSource({"1,true", "25,true", "26,false"})
    void creatInvalidVisitDay(int day, boolean expectedResult) {
        // given
        VisitDate visitDate = new VisitDate(day);
        // when
        boolean isInChristmasPromotion = visitDate.isInChristmasPromotion();
        // then
        assertThat(isInChristmasPromotion).isEqualTo(expectedResult);
    }

    @DisplayName("방문 날짜가 주말 할인 기간이 맞는지 알려준다.")
    @ParameterizedTest
    @CsvSource({"1,true", "5,false", "9,true", "10,false", "16,true"})
    void isInWeekendPromotion(int day, boolean expectedResult) {
        // given
        VisitDate visitDate = new VisitDate(day);
        // when
        boolean isInWeekendPromotion = visitDate.isInWeekendPromotion();
        // then
        assertThat(isInWeekendPromotion).isEqualTo(expectedResult);
    }

    @DisplayName("방문 날짜가 평일 할인 기간이 맞는지 알려준다.")
    @ParameterizedTest
    @CsvSource({"1,false", "5,true", "9,false", "10,true", "16,false"})
    void isInWeekDayPromotion(int day, boolean expectedResult) {
        // given
        VisitDate visitDate = new VisitDate(day);
        // when
        boolean isInWeekDayPromotion = visitDate.isInWeekDayPromotion();
        // then
        assertThat(isInWeekDayPromotion).isEqualTo(expectedResult);
    }

    @DisplayName("방문 날짜가 특별 할인 기간이 맞는지 알려준다.")
    @ParameterizedTest
    @CsvSource({"1,false", "2,false", "3,true", "10,true", "13,false", "25,true"})
    void isInSpecialPromotion(int day, boolean expectedResult) {
        // given
        VisitDate visitDate = new VisitDate(day);
        // when
        boolean isInSpecialPromotion = visitDate.isInSpecialPromotion();
        // then
        assertThat(isInSpecialPromotion).isEqualTo(expectedResult);
    }

    @DisplayName("방문 날짜가 첫째날로부터 며칠이나 지났는지 계산한다.")
    @ParameterizedTest
    @CsvSource({"1,0", "2,1", "25,24"})
    void getDifferenceFromMinDay(int day, int expectedResult) {
        // given
        VisitDate visitDate = new VisitDate(day);
        // when
        int differenceFromMinDay = visitDate.getDifferenceFromFirstDay();
        // then
        assertThat(differenceFromMinDay).isEqualTo(expectedResult);
    }

    @DisplayName("방문 날짜를 String으로 반환한다.")
    @Test
    void getVisitDateString() {
        // given
        VisitDate visitDate = new VisitDate(3);
        // when, then
        assertThat(visitDate.toString()).isEqualTo("3");
    }
}