package christmas.domain.promotion.badge;

public enum Badge {
    NOTING("없음", 0),
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String name;
    private final long requiredTotalBenefit;

    Badge(String name, long requiredTotalBenefit) {
        this.name = name;
        this.requiredTotalBenefit = requiredTotalBenefit;
    }

    public String getName() {
        return name;
    }

    public long getRequiredTotalBenefit() {
        return requiredTotalBenefit;
    }
}
