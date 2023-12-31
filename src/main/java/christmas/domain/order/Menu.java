package christmas.domain.order;

public enum Menu {
    MUSHROOM_SOUP("애피타이저", "양송이수프", 6_000),
    TAPAS("애피타이저", "타파스", 5_500),
    CAESAR_SALAD("애피타이저", "시저샐러드", 8_000),
    T_BONE_STEAK("메인", "티본스테이크", 55_000),
    BBQ_RIBS("메인", "바비큐립", 54_000),
    SEAFOOD_PASTA("메인", "해산물파스타", 35_000),
    CHRISTMAS_PASTA("메인", "크리스마스파스타", 25_000),
    CHOCO_CAKE("디저트", "초코케이크", 15_000),
    ICE_CREAM("디저트", "아이스크림", 5_000),
    ZERO_COKE("음료", "제로콜라", 3_000),
    RED_WINE("음료", "레드와인", 60_000),
    CHAMPAGNE("음료", "샴페인", 25_000);

    private final String kind;
    private final String name;
    private final long price;

    Menu(String kind, String name, long price) {
        this.kind = kind;
        this.name = name;
        this.price = price;
    }

    public String getKind() {
        return kind;
    }

    public String getName() {
        return name;
    }

    public long getPrice() {
        return price;
    }

    public boolean isMain() {
        return "메인".equals(this.getKind());
    }

    public boolean isDessert() {
        return "디저트".equals(this.getKind());
    }

    public boolean isDrink() {
        return "음료".equals(this.getKind());
    }
}
