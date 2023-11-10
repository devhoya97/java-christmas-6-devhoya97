package christmas.view;

import static christmas.domain.utils.ErrorMessage.NOT_INTEGER_INPUT_ERROR;

import camp.nextstep.edu.missionutils.Console;
import christmas.domain.VisitDate;

public class InputView {
    public VisitDate readDate() {
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

    private int parseInputToInteger(String input) {
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException numberFormatException) {
            throw new IllegalArgumentException(NOT_INTEGER_INPUT_ERROR);
        }
    }

    private String getTrimmedInput() {
        String input = Console.readLine();
        return input.trim();
    }
}
