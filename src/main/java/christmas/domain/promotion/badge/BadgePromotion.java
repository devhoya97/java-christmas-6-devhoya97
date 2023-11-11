package christmas.domain.promotion.badge;

public class BadgePromotion {
    private final long totalBenefit;

    public BadgePromotion(long totalBenefit) {
        this.totalBenefit = totalBenefit;
    }

    public Badge getBadge() {
        if (totalBenefit >= Badge.SANTA.getAcquisitionBenefit()) {
            return Badge.SANTA;
        }
        if (totalBenefit >= Badge.TREE.getAcquisitionBenefit()) {
            return Badge.TREE;
        }
        if (totalBenefit >= Badge.STAR.getAcquisitionBenefit()) {
            return Badge.STAR;
        }
        return Badge.NOTING;
    }
}
