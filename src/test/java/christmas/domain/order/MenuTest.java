package christmas.domain.order;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MenuTest {

    @DisplayName("특정 메뉴가 메인에 속하는지 판단한다.")
    @Test
    void isMain() {
        assertThat(Menu.BBQ_RIBS.isMain()).isEqualTo(true);
        assertThat(Menu.CHOCO_CAKE.isMain()).isEqualTo(false);
    }

    @DisplayName("특정 메뉴가 디저트에 속하는지 판단한다.")
    @Test
    void isDessert() {
        assertThat(Menu.ICE_CREAM.isDessert()).isEqualTo(true);
        assertThat(Menu.SEAFOOD_PASTA.isDessert()).isEqualTo(false);
    }

    @DisplayName("특정 메뉴가 음료에 속하는지 판단한다.")
    @Test
    void isDrink() {
        assertThat(Menu.CHAMPAGNE.isDrink()).isEqualTo(true);
        assertThat(Menu.TAPAS.isDrink()).isEqualTo(false);
    }
}