package framework.pages;

import framework.data.Product;
import io.qameta.allure.Step;
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

    public ProductInformationPage clickOnProduct(String name) {
        for (WebElement itemMenu : product) {
            if (itemMenu.getText().contains(name)) {
                String xpath = "//div[@data-id=\"product\"]//a[contains(@class, 'catalog-product__name')]/span[contains(text(), \"" + name + "\")]/..";
                WebElement item = driverManager.getDriver().findElement(By.xpath(xpath));
                waitElementToBeClicable(item);
                item.click();
                return pageManager.getProductInformationPage();
            }
        }
        return pageManager.getProductInformationPage();
    }

    @Step
    public VariantsOfProductsPage buyProduct(String name) {
        for (WebElement itemMenu : product) {
            if (itemMenu.getText().contains(name)) {
                String buyBtnXpath = "//div[@data-id=\"product\"]//a[contains(@class, 'catalog-product__name')]/span[contains(text(), \"" + name + "\")]/../..//button[contains(@class, \"button-ui buy-btn\")]";
                WebElement buyBtn = driverManager.getDriver().findElement(By.xpath(buyBtnXpath));
                waitElementToBeClicable(buyBtn);
                buyBtn.click();
                createNewProduct(name);
                wait.until(ExpectedConditions.textToBePresentInElement(amountProductsInCart, String.valueOf(getShoppingCart().size())));
                Assertions.assertTrue(false);
                Assertions.assertEquals(Integer.parseInt(String.valueOf(totalPriceShouldBe())), getPrice(totalPriceCart));
                return pageManager.getVariantsOfProductsPage();
            }
        }
        return pageManager.getVariantsOfProductsPage();
    }

    public ShoppingCartPage goIntoCart() {
        waitElementToBeClicable(cartBnt);
        cartBnt.click();
        return pageManager.getShoppingCartPage();
    }

    public int totalPriceShouldBe(){
        int sum=0;
        for (Product product: getShoppingCart()) {
            sum+=product.getPrice();
            sum+=product.getPriceOfGuaranty();
        }
        return sum;
    }

    public void createNewProduct(String name){
        String productPriceXpath = "//div[@data-id=\"product\"]//a[contains(@class, 'catalog-product__name')]/span[contains(text(), \"" + name + "\")]/../..//div[@class=\"product-buy__price\"]";
        WebElement price = driverManager.getDriver().findElement(By.xpath(productPriceXpath));
        addToShoppingCart(new Product(name, getPrice(price)));
    }
}