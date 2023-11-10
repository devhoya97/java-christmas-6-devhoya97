package christmas.domain.promotion.present;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.VisitDay;
import christmas.domain.menu.Drink;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class GiftPromotionTest {

    @DisplayName("할인 전 총주문 금액이 12만원 이상이라면 샴페인 1개를 증정한다.")
    @ParameterizedTest
    @CsvSource({"50000,false", "120000,true"})
    void getGift(int totalPrice, boolean expectedResult) {
        // given
        VisitDay visitDay = new VisitDay(1);
        GiftPromotion giftPromotion = new GiftPromotion(visitDay, totalPrice);
        // when, then
        assertThat(giftPromotion.getGift() == Drink.CHAMPAGNE).isEqualTo(expectedResult);
    }
}