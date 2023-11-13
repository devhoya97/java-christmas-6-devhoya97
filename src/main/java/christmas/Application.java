package christmas;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.promotion.Badge;
import christmas.domain.promotion.BenefitManager;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        VisitDate visitDate = InputView.readDate();
        Order order = InputView.readOrder();

        BenefitManager benefitManager = new BenefitManager(visitDate, order);
        Badge badge = Badge.getBadge(benefitManager.calculateTotalBenefit());

        OutputView.printResult(visitDate.toString(), order, benefitManager, badge.getName());
    }
}
