package christmas.view;

import christmas.domain.Order;
import christmas.domain.VisitDate;
import christmas.domain.promotion.badge.Badge;
import christmas.domain.promotion.discount.Discount;
import christmas.domain.promotion.discount.Discounter;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringJoiner;

public class OutputView {
    private final static String DISCOUNT_DELIMITER = ": ";
    private final static String MINUS = "-";
    private final static String DECIMAL_FORMAT = "#,###";
    private final static String MONEY_UNIT = "원";
    private static final String NOTHING = "없음";
    private static final String NEW_LINE = "\n";
    public void printResult(VisitDate visitDate, Order order, Discounter discounter, Badge badge) {
        Map<Discount, Long> discountResult = discounter.getResult();
        StringJoiner result = new StringJoiner(NEW_LINE);
        result.add(String.format("12월 %s일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!", visitDate.toString()));
        result.add("\n<주문 메뉴>").add(order.toString());
        result.add("\n<할인 전 총주문 금액>").add(order.calculateTotalPrice() + "원");
        addGiftMenuMessage(result, discountResult);
        addEachDiscountMessage(result, discountResult);
//        addTotalDiscountMessage(result, discountResult);
        System.out.println(result);
    }

    private void addGiftMenuMessage(StringJoiner result, Map<Discount, Long> discountResult) {
        result.add("\n<증정 메뉴>");
        if (discountResult.containsKey(Discount.GIFT)) {
            result.add("샴페인 1개");
            return;
        }
        result.add(NOTHING);
    }

    private void addEachDiscountMessage(StringJoiner result, Map<Discount, Long> discountResult) {
        result.add("\n<혜택 내역>");
        if (discountResult.isEmpty()) {
            result.add(NOTHING);
            return;
        }
        for (Entry<Discount, Long> eachDiscount : discountResult.entrySet()) {
            String discountName = eachDiscount.getKey().getName();
            DecimalFormat thousandSeparator = new DecimalFormat(DECIMAL_FORMAT);
            String formattedDiscount = thousandSeparator.format(eachDiscount.getValue());

            result.add(discountName + DISCOUNT_DELIMITER + MINUS + formattedDiscount + MONEY_UNIT);
        }
    }

    private void addTotalDiscountMessage(StringJoiner result, Discounter discounter) {
        result.add("\n<총혜택 금액>");
//        if (discounter.) {
//            result.add(0 + MONEY_UNIT);
//            return;
//        }
//        long totalDiscount =
//        String discountName = eachDiscount.getKey().getName();
//        DecimalFormat thousandSeparator = new DecimalFormat(DECIMAL_FORMAT);
//        String formattedDiscount = thousandSeparator.format(eachDiscount.getValue());
//
//        result.add(discountName + DISCOUNT_DELIMITER + MINUS + formattedDiscount + MONEY_UNIT);
    }
}
