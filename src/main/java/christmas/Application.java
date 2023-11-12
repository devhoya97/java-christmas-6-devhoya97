package christmas;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.domain.promotion.badge.Badge;
import christmas.domain.promotion.badge.BadgePromotion;
import christmas.domain.promotion.benefit.BenefitManager;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        VisitDate visitDate = InputView.readDate();
        Order order = InputView.readOrder();

        BenefitManager benefitManager = new BenefitManager(visitDate, order);
        BadgePromotion badgePromotion = new BadgePromotion(benefitManager.calculateTotalBenefit());
        Badge badge = badgePromotion.getBadge();

        OutputView.printResult(visitDate.toString(), order, benefitManager, badge);
    }
}
