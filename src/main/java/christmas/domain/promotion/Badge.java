package christmas.domain.promotion;

public enum Badge {
    SANTA("산타", 20_000),
    TREE("트리", 10_000),
    STAR("별", 5_000),
    NOTING("없음", 0);

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

    public static Badge get(long totalBenefit) {
        for (Badge badge : Badge.values()) {
            if (totalBenefit >= badge.requiredTotalBenefit) {
                return badge;
            }
        }
        return NOTING;
    }
}
