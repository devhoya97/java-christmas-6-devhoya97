package christmas.domain.promotion.benefit;

public enum Benefit {
    CHRISTMAS("크리스마스 디데이 할인"),
    WEEK_DAY("평일 할인"),
    WEEKEND("주말 할인"),
    SPECIAL("특별 할인"),
    GIFT("증정 이벤트", "샴페인 1개");

    private final String name;
    private final String gift;

    Benefit(String name) {
        this(name, null);
    }

    Benefit(String name, String gift) {
        this.name = name;
        this.gift = gift;
    }

    public String getName() {
        return name;
    }

    public String getGift() {
        return gift;
    }
}
