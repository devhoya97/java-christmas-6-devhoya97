package christmas.domain.utils;

public class ErrorMessage {
    public static final String PREFIX = "[ERROR] ";
    public static final String MAX_ORDER_ITEMS_ERROR = PREFIX + "주문한 메뉴당 개수의 총합은 최대 %d개까지 가능합니다.";
    public static final String MIN_ORDER_ITEM_ERROR = PREFIX + "선택한 메뉴의 개수가 %d개보다 작습니다.";
    public static final String DAY_OUT_OF_RANGE_ERROR = PREFIX + "방문 날짜는 %d일~%d일 사이만 가능합니다.";
    public static final String NOT_INTEGER_INPUT_ERROR = PREFIX + "입력이 숫자가 아닙니다.";
    public static final String NOT_PREPARED_MENU_ERROR = PREFIX + "주문 중 메뉴리스트에 없는 메뉴가 포함되어 있습니다.";
}
