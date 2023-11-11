package christmas.view;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.domain.menu.Menu;
import christmas.domain.promotion.badge.Badge;
import christmas.domain.promotion.benefit.Benefit;
import christmas.domain.promotion.benefit.BenefitManager;
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
        StringJoiner totalMessage = new StringJoiner(NEW_LINE);
        totalMessage.add(String.format("12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", visitDate.toString()));
        totalMessage.add("\n<주문 메뉴>").add(order.toString());
        addTotalPriceMessage(totalMessage, order.calculateTotalPrice());
        addGiftMenuMessage(totalMessage, benefitResult);
        addEachBenefitMessage(totalMessage, benefitResult);
        addTotalBenefitMessage(totalMessage, benefitManager);
        addTotalPriceAfterDiscountMessage(totalMessage, benefitManager);
        totalMessage.add("\n<12월 이벤트 배지>").add(badge.getName());
        System.out.println(totalMessage);
    }

    private static void addTotalPriceMessage(StringJoiner totalMessage, long totalPrice) {
        totalMessage.add("\n<할인 전 총주문 금액>");
        String thousandFormatTotalPrice = getThousandFormatPrice(totalPrice);
        totalMessage.add(thousandFormatTotalPrice + MONEY_UNIT);
    }

    private static String getThousandFormatPrice(long price) {
        DecimalFormat thousandSeparator = new DecimalFormat(DECIMAL_FORMAT);
        return thousandSeparator.format(price);
    }

    private static void addGiftMenuMessage(StringJoiner totalMessage, Map<Benefit, Long> benefitResult) {
        totalMessage.add("\n<증정 메뉴>");
        if (benefitResult.containsKey(Benefit.GIFT)) {
            totalMessage.add(Menu.CHAMPAGNE.getName() + " 1개");
            return;
        }
        totalMessage.add(NOTHING);
    }

    private static void addEachBenefitMessage(StringJoiner totalMessage, Map<Benefit, Long> benefitResult) {
        totalMessage.add("\n<혜택 내역>");
        if (benefitResult.isEmpty()) {
            totalMessage.add(NOTHING);
            return;
        }
        for (Entry<Benefit, Long> eachBenefit : benefitResult.entrySet()) {
            String benefitName = eachBenefit.getKey().getName();
            DecimalFormat thousandSeparator = new DecimalFormat(DECIMAL_FORMAT);
            String formattedBenefit = thousandSeparator.format(eachBenefit.getValue());

            totalMessage.add(benefitName + BENEFIT_DELIMITER + MINUS + formattedBenefit + MONEY_UNIT);
        }
    }

    private static void addTotalBenefitMessage(StringJoiner totalMessage, BenefitManager benefitManager) {
        totalMessage.add("\n<총혜택 금액>");
        long totalBenefit = benefitManager.calculateTotalBenefit();
        if (totalBenefit == NO_DISCOUNT) {
            totalMessage.add(NO_DISCOUNT + MONEY_UNIT);
            return;
        }
        DecimalFormat thousandSeparator = new DecimalFormat(DECIMAL_FORMAT);
        String formattedBenefit = thousandSeparator.format(totalBenefit);
        totalMessage.add(MINUS + formattedBenefit + MONEY_UNIT);
    }

    private static void addTotalPriceAfterDiscountMessage(StringJoiner totalMessage, BenefitManager benefitManager) {
        totalMessage.add("\n<할인 후 예상 결제 금액>");
        long totalPriceAfterDiscount = benefitManager.calculateTotalPriceAfterDiscount();
        String thousandFormatTotalPriceAfterDiscount = getThousandFormatPrice(totalPriceAfterDiscount);
        totalMessage.add(thousandFormatTotalPriceAfterDiscount + MONEY_UNIT);
    }
}
