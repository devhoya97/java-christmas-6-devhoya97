package christmas;

import christmas.domain.VisitDate;
import christmas.domain.order.Order;
import christmas.domain.promotion.Badge;
import christmas.domain.promotion.BenefitResult;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        VisitDate visitDate = InputView.readDate();
        Order order = InputView.readOrder();

        BenefitResult benefitResult = new BenefitResult(visitDate, order);
        Badge badge = Badge.get(benefitResult.calculateTotalBenefit());

        OutputView.printResult(visitDate.toString(), order, benefitResult, badge.getName());
    }
}
