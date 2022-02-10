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

    @FindBy(xpath = "//div[@data-id=\"product\"]")
    List<WebElement> products;

    @FindBy(xpath = "//button[@class=\"button-ui buy-btn button-ui_brand button-ui_passive\"]")
    private List<WebElement> buyProduct;

    @Step(value = "Выбрать продукт {name} по текстовой ссылке")
    public ProductInformationPage clickOnProduct(String name) {
        for (WebElement itemMenu : products) {
            if (itemMenu.getText().contains(name)) {
                WebElement item=itemMenu.findElement(By.xpath(".//a[contains(@class, 'catalog-product__name')]"));
                waitElementToBeClicable(item);
                item.click();
                return pageManager.getProductInformationPage();
            }
        }
        return pageManager.getProductInformationPage();
    }

    @Step (value = "Добавть {name} в корзину")
    public VariantsOfProductsPage buyProduct(String name) {
        for (WebElement itemMenu : products) {
            if (itemMenu.getText().contains(name)) {
                WebElement buyBtn=itemMenu.findElement(By.xpath(".//button[contains(@class, \"button-ui buy-btn\")]"));
                waitElementToBeClicable(buyBtn);
                buyBtn.click();
                createNewProduct(name);
                wait.until(ExpectedConditions.textToBePresentInElement(amountProductsInCart, String.valueOf(getShoppingCart().size())));
                Assertions.assertEquals(Integer.parseInt(String.valueOf(totalPriceShouldBe())), getPrice(totalPriceCart));
                return pageManager.getVariantsOfProductsPage();
            }
        }
        return pageManager.getVariantsOfProductsPage();
    }

    @Step(value = "Перейти в корзину")
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
        for (WebElement product : products) {
            if (product.getText().contains(name)) {
                WebElement price= product.findElement(By.xpath(".//div[@class=\"product-buy__price\"]"));
                addToShoppingCart(new Product(name, getPrice(price)));
            }
        }
    }
}