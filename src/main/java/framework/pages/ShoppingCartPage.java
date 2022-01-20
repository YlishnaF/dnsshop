package framework.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static framework.data.Product.*;

public class ShoppingCartPage extends BasePage{
    @FindBy(xpath = "//span[contains(@class, 'base-ui-radio-button__icon_checked')]")
    private WebElement guarantee;

    @FindBy(xpath = "//div[@class=\"cart-items__product\"]//span[@class=\"price__current\"]")
    private List<WebElement> currentPrices;

    @FindBy(xpath = "//div[@class=\"cart-items__product\"]//div[contains(text(), \"Доп. гарантия\")]/../..//span[@class=\"base-ui-radio-button__icon base-ui-radio-button__icon_checked\"]/../../span")
    private List<WebElement> guaranteePrices;

    @FindBy(xpath = "//div[@class=\"total-amount__label\"]//span[@class=\"price__current\"]")
    private WebElement totalPriceOnPage;

    @FindBy(xpath = "//i[@class=\"count-buttons__icon count-buttons__icon-minus\"]")
    private WebElement deleteItemFromCart;

    @FindBy(xpath = "//a[@class=\"cart-items__product-name-link\"]")
    private List<WebElement> items;

    @FindBy(xpath = "//div[@class=\"header-menu-wrapper\"]//span[@class=\"cart-link__badge\"]")
    private WebElement amountProductsInCart;

    public void checkGuarantee(String s){
        System.out.println(guarantee.getText());
        Assertions.assertTrue(guarantee.getText().contains(s));
    }

    public void checkTotalCartPrice(){
        int totalPrice=0;
        for (WebElement item:items) {
   //         String xpath = "//a[@class=\"cart-items__product-name-link\" and contains(text(), \""+name+ "\")]/../../../../..//i[@class=\"count-buttons__icon count-buttons__icon-plus\"]";


        }
        for (WebElement item: currentPrices) {
            totalPrice+=getPrice(item);

        }
        System.out.println(totalPrice);
        for (WebElement gar: guaranteePrices) {
            WebElement webElement = driverManager.getDriver().findElement(By.xpath("//div[@class=\"cart-items__product\"]//div[contains(text(), \"Доп. гарантия\")]/../..//span[@class=\"base-ui-radio-button__icon base-ui-radio-button__icon_checked\"]/../../span/../../../../../../..//input"));
            System.out.println("input " + webElement.getAttribute("value"));
            int count=Integer.parseInt(webElement.getAttribute("value"));
            totalPrice+=getPrice(gar)*count;
        }
        System.out.println(totalPrice);
        Assertions.assertEquals(totalPrice, getPrice(totalPriceOnPage));
    }

    public void deleteItemFromCart(String name) throws InterruptedException {
        for (WebElement item: items) {
            if(item.getText().contains(name)){
                String xpath = "//a[@class=\"cart-items__product-name-link\" and contains(text(), \""+name+ "\")]/../../../../..//i[@class=\"count-buttons__icon count-buttons__icon-minus\"]";
                WebElement delete = driverManager.getDriver().findElement(By.xpath(xpath));
                delete.click();
                Thread.sleep(2000);
                removeProductFromCart(name);
                System.out.println(getShoppingCart().size());
                wait.until(ExpectedConditions.textToBePresentInElement(amountProductsInCart,String.valueOf(getShoppingCart().size())));

                Thread.sleep(2000);
                return;
            }
        }
    }

    public void addAdditionalItemToCart(String name) throws InterruptedException {
        for (WebElement item: items) {
            if(item.getText().contains(name)){
                String xpath = "//a[@class=\"cart-items__product-name-link\" and contains(text(), \""+name+ "\")]/../../../../..//i[@class=\"count-buttons__icon count-buttons__icon-plus\"]";
                WebElement added = driverManager.getDriver().findElement(By.xpath(xpath));
                scrollWithOffset(item,0,-100);
                waitElementToBeClicable(added);;
                added.click();
                addToCartByName(name);
                System.out.println(getShoppingCart().size());
                wait.until(ExpectedConditions.textToBePresentInElement(amountProductsInCart,String.valueOf(getShoppingCart().size())));
                Thread.sleep(2000);
                return;
            }
        }

    }
}
