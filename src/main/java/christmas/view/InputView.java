package christmas.view;

import static christmas.domain.utils.ErrorMessage.INVALID_DATE_ERROR;
import static christmas.domain.utils.ErrorMessage.INVALID_MENU_ERROR;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.Order;
import christmas.domain.OrderItem;
import christmas.domain.VisitDate;
import christmas.domain.menu.Menu;
import java.util.ArrayList;
import java.util.List;

public class InputView {
    private static final String INPUT_ORDER_DELIMITER = ",";
    private static final String MENU_NAME_COUNT_DELIMITER = "-";
    private static final int MENU_NAME_INDEX = 0;
    private static final int COUNT_INDEX = 1;
    private static final int MENU_NAME_AND_COUNT_ARRAY_SIZE = 2;
    public static VisitDate readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        while(true) {
            String inputDate = getTrimmedInput();
            try {
                int date = parseInputDateToInteger(inputDate);
                return new VisitDate(date);
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException.getMessage());
            }
        }
    }

    private static String getTrimmedInput() {
        String input = Console.readLine();
        return input.trim();
    }

    private static int parseInputDateToInteger(String inputDate) {
        try {
            return Integer.parseInt(inputDate);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(INVALID_DATE_ERROR);
        }
    }

    public static Order readOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
        while(true) {
            String inputOrder = getTrimmedInput();
            try {
                return parseInputToOrder(inputOrder);
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException.getMessage());
            }
        }
    }

    private static Order parseInputToOrder(String inputOrder) {
        List<OrderItem> orderItems = new ArrayList<>();
        for (String menuNameWithCount : inputOrder.split(INPUT_ORDER_DELIMITER)) {
            orderItems.add(organizeOrderItem(menuNameWithCount));
        }
        return new Order(orderItems);
    }

    private static OrderItem organizeOrderItem(String menuNameWithCount) {
        String[] menuNameAndCount = menuNameWithCount.split(MENU_NAME_COUNT_DELIMITER);
        if (menuNameAndCount.length != MENU_NAME_AND_COUNT_ARRAY_SIZE) {
            throw new IllegalArgumentException(INVALID_MENU_ERROR);
        }

        String menuName = menuNameAndCount[MENU_NAME_INDEX].trim();
        Menu menu = findPreparedMenu(menuName);

        String inputCount = menuNameAndCount[COUNT_INDEX].trim();
        int count = parseInputCountToInteger(inputCount);

        return new OrderItem(menu, count);
    }

    private static Menu findPreparedMenu(String menuName) {
        for (Menu menu : Menu.values()) {
            if (menuName.equals(menu.getName())) {
                return menu;
            }
        }
        throw new IllegalArgumentException(INVALID_MENU_ERROR);
    }

    private static int parseInputCountToInteger(String inputCount) {
        try {
            return Integer.parseInt(inputCount);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(INVALID_MENU_ERROR);
        }
    }
}
