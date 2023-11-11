package christmas.domain.promotion.badge;

public class BadgePromotion {
    private final long totalBenefit;

    public BadgePromotion(long totalBenefit) {
        this.totalBenefit = totalBenefit;
    }

    public Badge getBadge() {
        if (totalBenefit >= Badge.SANTA.getRequiredTotalBenefit()) {
            return Badge.SANTA;
        }
        if (totalBenefit >= Badge.TREE.getRequiredTotalBenefit()) {
            return Badge.TREE;
        }
        if (totalBenefit >= Badge.STAR.getRequiredTotalBenefit()) {
            return Badge.STAR;
        }
        return Badge.NOTING;
    }
}
