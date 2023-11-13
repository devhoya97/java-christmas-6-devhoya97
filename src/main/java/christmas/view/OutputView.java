package christmas.view;

import christmas.domain.order.Order;
import christmas.domain.order.OrderItem;
import christmas.domain.promotion.Benefit;
import christmas.domain.promotion.BenefitManager;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    private static final String NEW_LINE = "\n";
    private static final String DOUBLE_NEW_LINE = "\n\n";
    private static final String EMPTY_SPACE = " ";
    private static final String COUNT_UNIT = "개";
    private final static String BENEFIT_DELIMITER = ": ";
    private final static String MINUS = "-";
    private final static String THOUSAND_FORMAT = "#,###";
    private final static String MONEY_UNIT = "원";
    private static final String NOTHING = "없음";
    private final static long NO_BENEFIT = 0L;

    public static void printResult(String visitDate, Order order, BenefitManager benefitManager, String badgeName) {
        long totalPrice = order.calculateTotalPrice();
        StringBuilder totalMessage = new StringBuilder();

        addGuideMessage(totalMessage, visitDate);
        addOrderItemsSummary(totalMessage, order);
        addTotalPriceMessage(totalMessage, totalPrice);
        addGiftMenuMessage(totalMessage, benefitManager.hasGift());
        addEachBenefitMessage(totalMessage, benefitManager.getBenefitResult());
        addTotalBenefitMessage(totalMessage, benefitManager.calculateTotalBenefit());
        addDiscountedTotalPriceMessage(totalMessage, totalPrice - benefitManager.calculateTotalDiscount());
        addBadgeMessage(totalMessage, badgeName);

        System.out.println(totalMessage);
    }

    private static void addGuideMessage(StringBuilder totalMessage, String visitDate) {
        totalMessage.append("12월 ")
                .append(visitDate)
                .append("일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!")
                .append(DOUBLE_NEW_LINE);
    }

    private static void addOrderItemsSummary(StringBuilder totalMessage, Order order) {
        totalMessage.append("<주문 메뉴>").append(NEW_LINE);
        for (OrderItem orderItem : order.getOrderItems()) {
            totalMessage.append(orderItem.getMenuName())
                    .append(EMPTY_SPACE)
                    .append(orderItem.getCount())
                    .append(COUNT_UNIT)
                    .append(NEW_LINE);
        }
        totalMessage.append(NEW_LINE);
    }

    private static void addTotalPriceMessage(StringBuilder totalMessage, long totalPrice) {
        totalMessage.append("<할인 전 총주문 금액>")
                .append(NEW_LINE)
                .append(getThousandFormatPrice(totalPrice))
                .append(MONEY_UNIT)
                .append(DOUBLE_NEW_LINE);
    }

    private static String getThousandFormatPrice(long price) {
        DecimalFormat thousandSeparator = new DecimalFormat(THOUSAND_FORMAT);
        return thousandSeparator.format(price);
    }

    private static void addGiftMenuMessage(StringBuilder totalMessage, boolean hasGift) {
        totalMessage.append("<증정 메뉴>").append(NEW_LINE);
        if (hasGift) {
            totalMessage.append(Benefit.GIFT_GIVING.getGift()).append(DOUBLE_NEW_LINE);
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
            totalMessage.append(eachBenefit.getKey().getName())
                    .append(BENEFIT_DELIMITER)
                    .append(MINUS)
                    .append(getThousandFormatPrice(eachBenefit.getValue()))
                    .append(MONEY_UNIT).append(NEW_LINE);
        }
        totalMessage.append(NEW_LINE);
    }

    private static void addTotalBenefitMessage(StringBuilder totalMessage, long totalBenefit) {
        totalMessage.append("<총혜택 금액>").append(NEW_LINE);
        if (totalBenefit == NO_BENEFIT) {
            totalMessage.append(NO_BENEFIT)
                    .append(MONEY_UNIT)
                    .append(DOUBLE_NEW_LINE);
            return;
        }
        totalMessage.append(MINUS)
                .append(getThousandFormatPrice(totalBenefit))
                .append(MONEY_UNIT)
                .append(DOUBLE_NEW_LINE);
    }

    private static void addDiscountedTotalPriceMessage(StringBuilder totalMessage, long discountedTotalPrice) {
        totalMessage.append("<할인 후 예상 결제 금액>")
                .append(NEW_LINE)
                .append(getThousandFormatPrice(discountedTotalPrice))
                .append(MONEY_UNIT)
                .append(DOUBLE_NEW_LINE);
    }

    private static void addBadgeMessage(StringBuilder totalMessage, String badgeName) {
        totalMessage.append("<12월 이벤트 배지>")
                .append(NEW_LINE)
                .append(badgeName);
    }
}
