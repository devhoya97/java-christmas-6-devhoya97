package christmas.domain.utils;

public class ErrorMessage {
    public static final String PREFIX = "[ERROR] ";
    public static final String MAX_TOTAL_COUNT_ERROR = PREFIX + "주문한 메뉴당 개수의 총합은 최대 %d개까지 가능합니다.";
    public static final String INVALID_DATE_ERROR = PREFIX + "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    public static final String INVALID_MENU_ERROR = PREFIX + "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    public static final String ONLY_DRINK_ERROR = PREFIX + "음료만 주문하는 것은 불가능합니다.";
}
