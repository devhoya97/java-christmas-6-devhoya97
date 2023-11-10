package christmas.domain;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.domain.menu.Appetizer;
import christmas.domain.menu.Dessert;
import christmas.domain.menu.Drink;
import christmas.domain.menu.Main;
import christmas.domain.promotion.WeekDayPromotion;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class WeekDayPromotionTest {

    @DisplayName("평일 할인 적용 시 할인 금액을 계산한다.")
    @Test
    void calculateDiscount() {
        // given
        OrderItem tapas = new OrderItem(Appetizer.TAPAS, 1);
        OrderItem christmasPasta = new OrderItem(Main.CHRISTMAS_PASTA, 2);
        OrderItem chocoCake = new OrderItem(Dessert.CHOCO_CAKE, 3);
        OrderItem iceCream = new OrderItem(Dessert.ICE_CREAM, 1);
        OrderItem zeroCoke = new OrderItem(Drink.ZERO_COKE, 4);
        Order order = new Order(List.of(tapas, christmasPasta, chocoCake, iceCream, zeroCoke));
        VisitDay visitDay = new VisitDay(14);
        WeekDayPromotion weekDayPromotion = new WeekDayPromotion(visitDay, order);
        long expectedResult = 2023L * 4;
        // when, then
        assertThat(weekDayPromotion.calculateDiscount()).isEqualTo(expectedResult);
    }
}