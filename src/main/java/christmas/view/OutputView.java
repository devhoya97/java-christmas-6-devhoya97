package christmas.view;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.domain.menu.Menu;
import christmas.domain.promotion.badge.Badge;
import christmas.domain.promotion.discount.Benefit;
import christmas.domain.promotion.discount.BenefitManager;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

public class OutputView {
    private final static String BENEFIT_DELIMITER = ": ";
    private final static String MINUS = "-";
    private final static String DECIMAL_FORMAT = "#,###";
    private final static String MONEY_UNIT = "원";
    private static final String NOTHING = "없음";
    private static final String NEW_LINE = "\n";
    private final static long NO_DISCOUNT = 0L;
    public static void printResult(VisitDate visitDate, Order order, BenefitManager benefitManager, Badge badge) {
        Map<Benefit, Long> benefitResult = benefitManager.getBenefitResult();
        StringJoiner result = new StringJoiner(NEW_LINE);
        result.add(String.format("12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", visitDate.toString()));
        result.add("\n<주문 메뉴>").add(order.toString());
        result.add("\n<할인 전 총주문 금액>").add(order.calculateTotalPrice() + "원");
        addGiftMenuMessage(result, benefitResult);
        addEachBenefitMessage(result, benefitResult);
        addTotalBenefitMessage(result, benefitManager);
        addTotalPriceAfterDiscountMessage(result, benefitManager);
        result.add("\n<12월 이벤트 배지>").add(badge.getName());
        System.out.println(result);
    }

    private static void addGiftMenuMessage(StringJoiner result, Map<Benefit, Long> benefitResult) {
        result.add("\n<증정 메뉴>");
        if (benefitResult.containsKey(Benefit.GIFT)) {
            result.add(Menu.CHAMPAGNE.getName() + " 1개");
            return;
        }
        result.add(NOTHING);
    }

    private static void addEachBenefitMessage(StringJoiner result, Map<Benefit, Long> benefitResult) {
        result.add("\n<혜택 내역>");
        if (benefitResult.isEmpty()) {
            result.add(NOTHING);
            return;
        }
        for (Entry<Benefit, Long> eachBenefit : benefitResult.entrySet()) {
            String benefitName = eachBenefit.getKey().getName();
            DecimalFormat thousandSeparator = new DecimalFormat(DECIMAL_FORMAT);
            String formattedBenefit = thousandSeparator.format(eachBenefit.getValue());

            result.add(benefitName + BENEFIT_DELIMITER + MINUS + formattedBenefit + MONEY_UNIT);
        }
    }

    private static void addTotalBenefitMessage(StringJoiner result, BenefitManager benefitManager) {
        result.add("\n<총혜택 금액>");
        long totalBenefit = benefitManager.calculateTotalBenefit();
        if (totalBenefit == NO_DISCOUNT) {
            result.add(NO_DISCOUNT + MONEY_UNIT);
            return;
        }
        DecimalFormat thousandSeparator = new DecimalFormat(DECIMAL_FORMAT);
        String formattedBenefit = thousandSeparator.format(totalBenefit);
        result.add(MINUS + formattedBenefit + MONEY_UNIT);
    }

    private static void addTotalPriceAfterDiscountMessage(StringJoiner result, BenefitManager benefitManager) {
        result.add("\n<할인 후 예상 결제 금액>");
        long totalPriceAfterDiscount = benefitManager.calculateTotalPriceAfterDiscount();
        DecimalFormat thousandSeparator = new DecimalFormat(DECIMAL_FORMAT);
        String formattedTotalPriceAfterDiscount = thousandSeparator.format(totalPriceAfterDiscount);
        result.add(formattedTotalPriceAfterDiscount + MONEY_UNIT);
    }
}
