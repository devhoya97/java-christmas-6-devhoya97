package christmas.domain.promotion.badge;

public class BadgePromotion {
    private final long totalDiscount;

    public BadgePromotion(long totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public Badge getBadge() {
        if (totalDiscount >= Badge.SANTA.getAcquisitionPrice()) {
            return Badge.SANTA;
        }
        if (totalDiscount >= Badge.TREE.getAcquisitionPrice()) {
            return Badge.TREE;
        }
        if (totalDiscount >= Badge.STAR.getAcquisitionPrice()) {
            return Badge.STAR;
        }
        return null;
    }
}
