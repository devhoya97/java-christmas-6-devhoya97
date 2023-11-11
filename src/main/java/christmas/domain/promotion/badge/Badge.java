package christmas.domain.promotion.badge;

public enum Badge {
    NOTING("없음", 0),
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String name;
    private final long acquisitionBenefit;

    Badge(String name, long acquisitionBenefit) {
        this.name = name;
        this.acquisitionBenefit = acquisitionBenefit;
    }

    public String getName() {
        return name;
    }

    public long getAcquisitionBenefit() {
        return acquisitionBenefit;
    }
}
