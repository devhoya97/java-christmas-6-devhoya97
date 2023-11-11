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

public class OutputView {
    private static final String NEW_LINE = "\n";
    private static final String DOUBLE_NEW_LINE = "\n\n";
    private static final String GIFT_COUNT = " 1개";
    private final static String BENEFIT_DELIMITER = ": ";
    private final static String MINUS = "-";
    private final static String DECIMAL_FORMAT = "#,###";
    private final static String MONEY_UNIT = "원";
    private static final String NOTHING = "없음";
    private final static long NO_BENEFIT = 0L;
    public static void printResult(VisitDate visitDate, Order order, BenefitManager benefitManager, Badge badge) {
        Map<Benefit, Long> benefitResult = benefitManager.getBenefitResult();
        StringBuilder totalMessage = new StringBuilder();
        totalMessage.append(String.format("12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", visitDate.toString()))
                .append(DOUBLE_NEW_LINE);
        totalMessage.append("<주문 메뉴>").append(NEW_LINE).append(order.toString()).append(DOUBLE_NEW_LINE);
        addTotalPriceMessage(totalMessage, order.calculateTotalPrice());
        addGiftMenuMessage(totalMessage, benefitResult);
        addEachBenefitMessage(totalMessage, benefitResult);
        addTotalBenefitMessage(totalMessage, benefitManager.calculateTotalBenefit());
        addDiscountedTotalPriceMessage(totalMessage, benefitManager.calculateDiscountedTotalPrice());
        totalMessage.append("<12월 이벤트 배지>").append(NEW_LINE).append(badge.getName());
        System.out.println(totalMessage);
    }

    private static void addTotalPriceMessage(StringBuilder totalMessage, long totalPrice) {
        totalMessage.append("<할인 전 총주문 금액>").append(NEW_LINE);
        String thousandFormatTotalPrice = getThousandFormatPrice(totalPrice);
        totalMessage.append(thousandFormatTotalPrice).append(MONEY_UNIT).append(DOUBLE_NEW_LINE);
    }

    private static String getThousandFormatPrice(long price) {
        DecimalFormat thousandSeparator = new DecimalFormat(DECIMAL_FORMAT);
        return thousandSeparator.format(price);
    }

    private static void addGiftMenuMessage(StringBuilder totalMessage, Map<Benefit, Long> benefitResult) {
        totalMessage.append("<증정 메뉴>").append(NEW_LINE);
        if (benefitResult.containsKey(Benefit.GIFT)) {
            totalMessage.append(Menu.CHAMPAGNE.getName()).append(GIFT_COUNT).append(DOUBLE_NEW_LINE);
            return;
        }
        totalMessage.append(NOTHING).append(DOUBLE_NEW_LINE);
    }

    private static void addEachBenefitMessage(StringBuilder totalMessage, Map<Benefit, Long> benefitResult) {
        totalMessage.append("<혜택 내역>").append(NEW_LINE);
        if (benefitResult.isEmpty()) {
            totalMessage.append(NOTHING).append(DOUBLE_NEW_LINE);
            return;
        }
        for (Entry<Benefit, Long> eachBenefit : benefitResult.entrySet()) {
            String benefitName = eachBenefit.getKey().getName();
            String thousandFormatBenefitPrice = getThousandFormatPrice(eachBenefit.getValue());

            totalMessage.append(benefitName).append(BENEFIT_DELIMITER).append(MINUS)
                    .append(thousandFormatBenefitPrice).append(MONEY_UNIT).append(NEW_LINE);
        }
        totalMessage.append(NEW_LINE);
    }

    private static void addTotalBenefitMessage(StringBuilder totalMessage, long totalBenefit) {
        totalMessage.append("<총혜택 금액>").append(NEW_LINE);
        if (totalBenefit == NO_BENEFIT) {
            totalMessage.append(NO_BENEFIT).append(MONEY_UNIT).append(DOUBLE_NEW_LINE);
            return;
        }
        String thousandFormatTotalBenefit = getThousandFormatPrice(totalBenefit);
        totalMessage.append(MINUS).append(thousandFormatTotalBenefit).append(MONEY_UNIT).append(DOUBLE_NEW_LINE);
    }

    private static void addDiscountedTotalPriceMessage(StringBuilder totalMessage, long discountedTotalPrice) {
        totalMessage.append("<할인 후 예상 결제 금액>").append(NEW_LINE);
        String thousandFormatDiscountedTotalPrice = getThousandFormatPrice(discountedTotalPrice);
        totalMessage.append(thousandFormatDiscountedTotalPrice).append(MONEY_UNIT).append(DOUBLE_NEW_LINE);
    }
}
