package christmas.view;

import static christmas.domain.utils.ErrorMessage.NOT_INTEGER_INPUT_ERROR;
import static christmas.domain.utils.ErrorMessage.NOT_PREPARED_MENU_ERROR;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.domain.VisitDate;
import christmas.domain.menu.Menu;
import java.util.ArrayList;
import java.util.List;

public class InputView {
    private static final String INPUT_DELIMITER = ",";
    private static final String ORDER_ITEM_DELIMITER = "-";
    private static final int MENU_INDEX = 0;
    private static final int NUMBER_INDEX = 1;
    public static VisitDate readDate() {
        while(true) {
            System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
            String input = getTrimmedInput();
            try {
                int date = parseInputToInteger(input);
                return new VisitDate(date);
            } catch (IllegalArgumentException illegalArgumentException) {
                // 오류 발생시 while문을 다시 돌며 재입력받는다.
            }
        }
    }

    public static Order readOrder() {
        while(true) {
            System.out.println("주문하실 메뉴를 메뉴와 개수를 알려주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
            String input = getTrimmedInput();
            try {
                return parseInputToOrder(input);
            } catch (IllegalArgumentException illegalArgumentException) {
                // 오류 발생시 while문을 다시 돌며 재입력받는다.
            }
        }
    }

    private static Order parseInputToOrder(String input) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (String splitInput : input.split(INPUT_DELIMITER)) {
            String[] menuWithNumber = splitInput.split(ORDER_ITEM_DELIMITER);

            String menuBeforeParse = menuWithNumber[MENU_INDEX].trim();
            Menu menu = parseInputToMenu(menuBeforeParse);

            String numberBeforeParse = menuWithNumber[NUMBER_INDEX].trim();
            int number = parseInputToInteger(numberBeforeParse);

            orderItems.add(new OrderItem(menu, number));
        }
        return new Order(orderItems);
    }

    private static Menu parseInputToMenu(String input) {
        for (Menu menu : Menu.values()) {
            if (input.equals(menu.getName())) {
                return menu;
            }
        }
        throw new IllegalArgumentException(NOT_PREPARED_MENU_ERROR);
    }

    private static String getTrimmedInput() {
        String input = Console.readLine();
        return input.trim();
    }

    private static int parseInputToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(NOT_INTEGER_INPUT_ERROR);
        }
    }
}
