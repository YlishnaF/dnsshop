package framework.pages;

import framework.data.Product;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static framework.data.Product.addToShoppingCart;
import static framework.data.Product.getShoppingCart;

public class ProductInformationPage extends BasePage {
    private int price = 0;
    private int priceWithGuarantee = 0;

    @FindBy(xpath = "//div[@class=\"product-buy__price product-buy__price_active\"]")
    private WebElement productPrice;
    @FindBy(xpath = "//div[@class=\"additional-sales-tabs__title\" and contains(text(), \"Гарантия: \")]")
    private WebElement guaranteeТxt;
    @FindBy(xpath = "//div[@class=\"product-card-top__buy\" ]")
    private WebElement productCard;
    @FindBy(xpath = "//span[@class=\"ui-radio__content\" and contains(text(),  \"Доп. гарантия\")]")
    private WebElement chooseGuaranteeBnt;
    @FindBy(xpath = "//div[@class=\"product-card-top__buy\"]//div[@class=\"product-buy__sub\"]")
    private WebElement isPriceChanged;
    @FindBy(xpath = "//div[ @class=\"product-card-top product-card-top_full\"]")
    private WebElement fullCard;
    @FindBy(xpath = "//div[@class=\"product-card-top__buy\"]//button[contains(text(), \"Купить\")]")
    private WebElement buyBtn;
    @FindBy(xpath = "//h1[@class= \"product-card-top__title\"]")
    private WebElement productName;

    @FindBy(xpath = "//h1[contains(text(), \"Найдено\")]")
    private WebElement amountOfFinedProducts;

    @Step
    public ProductInformationPage addGuarantee(){
        scrollToElement(productCard);
        if(guaranteeТxt.isDisplayed()){
            waitElementToBeClicable(guaranteeТxt);
            guaranteeТxt.click();
            waitElementToBeClicable(chooseGuaranteeBnt);
            chooseGuaranteeBnt.click();
            Assertions.assertEquals(isPriceChanged.getText(), "цена изменена");
        }
        return pageManager.getProductInformationPage();
    }
    @Step
    public ProductInformationPage checkThePriceChangedWithGuarantee() throws InterruptedException {
        Thread.sleep(2000);
        price = getPrice(productPrice);
        scrollToElement(productCard);
        addGuarantee();
        priceWithGuarantee = getPrice(productCard);
        scrollToElement(fullCard);
        Assertions.assertNotEquals(price,priceWithGuarantee);
        return pageManager.getProductInformationPage();
    }
    @Step
    public ProductInformationPage buyProduct() {
        waitElementToBeClicable(buyBtn);
        buyBtn.click();
        Product product = new Product(productName.getText(), price, (priceWithGuarantee - price), priceWithGuarantee != 0);
        addToShoppingCart(product);
        wait.until(ExpectedConditions.textToBePresentInElement(amountProductsInCart, String.valueOf(getShoppingCart().size())));
        return pageManager.getProductInformationPage();
    }
    @Step
    public VariantsOfProductsPage newSearch(String name) {
        waitElementToBeClicable(searchLine);
        searchLine.click();
        searchLine.sendKeys(name);
        searchLine.submit();
        wait.until(ExpectedConditions.visibilityOf(amountOfFinedProducts));
        return pageManager.getVariantsOfProductsPage();
    }
}
