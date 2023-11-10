package christmas.domain.utils;

public class ErrorMessage {
    public static final String PREFIX = "[ERROR] ";
    public static final String MAX_ORDER_ITEMS_ERROR = PREFIX + "주문한 메뉴당 개수의 총합은 최대 %d개까지 가능합니다.";
    public static final String MIN_ORDER_ITEM_ERROR = PREFIX + "선택한 메뉴의 개수가 %d개보다 작습니다.";
}
