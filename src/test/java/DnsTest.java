import base.BaseClass;
import com.google.errorprone.annotations.Var;
import framework.data.VariablesForSearch;
import org.junit.jupiter.api.Test;

public class DnsTest extends BaseClass {

    @Test
    public void test() throws InterruptedException {
        pageManager.getStartPage()
                .startSearchProduct(VariablesForSearch.COMMON_PHONE_NAME)
                .clickOnProduct(VariablesForSearch.SPECIFIC_PHONE_NAME)
                .checkThePriceChangedWithGuarantee()
                .buyProduct()
                .newSearch(VariablesForSearch.COMMON_GAME_NAME);
//                .buyProduct(VariablesForSearch.SPECIFIC_GAME_NAME)
//                .goIntoCart()
//                .checkGuarantee(VariablesForSearch.GUARANTEE_DURATION)
//                .checkTotalCartPrice()
//                .deleteItemFromCart(VariablesForSearch.SPECIFIC_GAME_NAME)
//                .checkTotalCartPrice()
//                .addAdditionalItemToCart(VariablesForSearch.SPECIFIC_PHONE_NAME)
//                .addAdditionalItemToCart(VariablesForSearch.SPECIFIC_PHONE_NAME)
//                .checkTotalCartPrice();

    }
}
