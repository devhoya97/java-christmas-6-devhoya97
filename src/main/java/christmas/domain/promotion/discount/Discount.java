package christmas.domain.promotion.discount;

public enum Discount {
    CHRISTMAS("크리스마스 디데이 할인"),
    WEEK_DAY("평일 할인"),
    WEEKEND("평일 할인"),
    SPECIAL("특별 할인"),
    GIFT("증정 이벤트");

    private final String name;

    Discount(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
