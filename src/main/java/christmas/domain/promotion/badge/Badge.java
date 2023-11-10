package christmas.domain.promotion.badge;

public enum Badge {
    STAR("별", 5000),
    TREE("트리", 10000),
    SANTA("산타", 20000);

    private final String name;
    private final long acquisitionPrice;

    Badge(String name, long acquisitionPrice) {
        this.name = name;
        this.acquisitionPrice = acquisitionPrice;
    }

    public String getName() {
        return name;
    }

    public long getAcquisitionPrice() {
        return acquisitionPrice;
    }
}
