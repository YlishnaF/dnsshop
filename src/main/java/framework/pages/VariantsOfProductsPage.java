package framework.pages;

import framework.data.Product;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static framework.data.Product.addToShoppingCart;
import static framework.data.Product.getShoppingCart;

public class VariantsOfProductsPage extends BasePage {

    @FindBy(xpath = "//div[@data-id=\"product\"]//a[contains(@class, 'catalog-product__name')]/span")
    List<WebElement> product;

    @FindBy(xpath = "//button[@class=\"button-ui buy-btn button-ui_brand button-ui_passive\"]")
    private List<WebElement> buyProduct;

    public void chooseProduct(String name) {
        for (WebElement itemMenu :
                product) {
            if (itemMenu.getText().contains(name)) {
                String xpath = "//div[@data-id=\"product\"]//a[contains(@class, 'catalog-product__name')]/span[contains(text(), \"" + name + "\")]/..";
                WebElement element = driverManager.getDriver().findElement(By.xpath(xpath));
                waitElementToBeClicable(element);
                element.click();
                return;
            }
        }
    }

    public void buyProduct(String name) {
        for (WebElement itemMenu :
                product) {
            if (itemMenu.getText().contains(name)) {
                String xpath = "//div[@data-id=\"product\"]//a[contains(@class, 'catalog-product__name')]/span[contains(text(), \"" + name + "\")]/../..//button[contains(@class, \"button-ui buy-btn\")]";
                WebElement element = driverManager.getDriver().findElement(By.xpath(xpath));
                waitElementToBeClicable(element);
                element.click();
                String xpath2 = "//div[@data-id=\"product\"]//a[contains(@class, 'catalog-product__name')]/span[contains(text(), \"" + name + "\")]/../..//div[@class=\"product-buy__price\"]";
                WebElement price = driverManager.getDriver().findElement(By.xpath(xpath2));
                Product product = new Product(name, getPrice(price));
                addToShoppingCart(product);
                wait.until(ExpectedConditions.textToBePresentInElement(amountProductsInCart, String.valueOf(getShoppingCart().size())));
                int totalPrice = getShoppingCart().get(0).getPrice() + getShoppingCart().get(0).getPriceOfGuaranty() + getShoppingCart().get(1).getPrice();
                Assertions.assertEquals(Integer.parseInt(String.valueOf(totalPrice)), getPrice(totalPriceCart));
                return;
            }
        }
    }

    public void goIntoCart() {
        waitElementToBeClicable(cartBnt);
        cartBnt.click();
    }
}
