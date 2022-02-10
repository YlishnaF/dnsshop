package framework.pages;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static framework.data.Product.*;

public class ShoppingCartPage extends BasePage {
    @FindBy(xpath = "//span[contains(@class, 'base-ui-radio-button__icon_checked')]")
    private WebElement guarantee;

    @FindBy(xpath = "//div[@class=\"cart-items__product\"]//span[@class=\"price__current\"]")
    private List<WebElement> currentPrices;

    @FindBy(xpath = "//span[@class=\"additional-warranties-row__price\"]")
    private List<WebElement> guaranteePrices;

    @FindBy(xpath = "//div[@class=\"total-amount__label\"]//span[@class=\"price__current\"]")
    private WebElement totalPriceOnPage;

    @FindBy(xpath = "//div[@class=\"cart-items__product\"]")
    private List<WebElement> items;

    @FindBy(xpath = "//div[@class=\"header-menu-wrapper\"]//span[@class=\"cart-link__badge\"]")
    private WebElement amountProductsInCart;

    @Step(value = "проверить наличие гарантии на {s}")
    public ShoppingCartPage checkGuarantee(String s) {
        Assertions.assertTrue(guarantee.getText().contains(s));
        return pageManager.getShoppingCartPage();
    }

    @Step (value = "проверить корректность общей суммы корзины")
    public ShoppingCartPage checkTotalCartPrice() {
        int totalPrice = 0;

        for (WebElement item : currentPrices) {
            totalPrice += getPrice(item);

        }

        for (WebElement gar : guaranteePrices) {
            WebElement webElement = driverManager.getDriver().findElement(By.xpath("//div[@class=\"cart-items__product\"]//div[contains(text(), \"Доп. гарантия\")]/../..//span[@class=\"base-ui-radio-button__icon base-ui-radio-button__icon_checked\"]/../../span/../../../../../../..//input"));
            int count = Integer.parseInt(webElement.getAttribute("value"));
            totalPrice += getPrice(gar) * count;
        }
        Assertions.assertEquals(totalPrice, getPrice(totalPriceOnPage));
        return pageManager.getShoppingCartPage();
    }

    @Step (value = "удалть из корзины {name}")
    public ShoppingCartPage deleteItemFromCart(String name) throws InterruptedException {
        for (WebElement item : items) {
            if (item.getText().contains(name)) {
                WebElement delete = item.findElement(By.xpath(".//i[@class=\"count-buttons__icon count-buttons__icon-minus\"]"));
                delete.click();
                Thread.sleep(2000);
                removeProductFromCart(name);
                wait.until(ExpectedConditions.textToBePresentInElement(amountProductsInCart, String.valueOf(getShoppingCart().size())));
                Thread.sleep(2000);
                return pageManager.getShoppingCartPage();
            }
        }
        return pageManager.getShoppingCartPage();
    }

    @Step (value = "добавть в корзину {name}")
    public ShoppingCartPage addAdditionalItemToCart(String name) throws InterruptedException {
        for (WebElement item : items) {
            if (item.getText().contains(name)) {
                WebElement add = item.findElement(By.xpath(".//i[@class=\"count-buttons__icon count-buttons__icon-plus\"]"));
                scrollWithOffset(item, 0, -100);
                waitElementToBeClicable(add);
                ;
                add.click();
                addToCartByName(name);
                wait.until(ExpectedConditions.textToBePresentInElement(amountProductsInCart, String.valueOf(getShoppingCart().size())));
                Thread.sleep(2000);
                return pageManager.getShoppingCartPage();
            }
        }
        return pageManager.getShoppingCartPage();
    }
}
