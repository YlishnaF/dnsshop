package framework.pages;

import framework.managers.DriverManager;
import framework.managers.PageManager;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected DriverManager driverManager = DriverManager.getInstance();
    protected WebDriverWait wait = new WebDriverWait(driverManager.getDriver(), 15, 1000);
    protected PageManager pageManager = PageManager.getInstance();

    @FindBy(xpath = "//div[@class=\"header-menu-wrapper\"]//input[@class=\"ui-input-search__input ui-input-search__input_presearch\"]")
    protected WebElement searchLine;

    @FindBy(xpath = "//div[@class=\"header-menu-wrapper\"]//span[@class=\"cart-link__badge\"]")
    protected WebElement amountProductsInCart;

    @FindBy(xpath = "//span[@class=\"cart-link__price\"]")
    protected WebElement totalPriceCart;
    @FindBy(xpath = "//a[@class=\"ui-link cart-link\"]")
    protected WebElement cartBnt;

    public BasePage() {
        PageFactory.initElements(driverManager.getDriver(), this);
    }

    public int getPrice(WebElement element) {
        return Integer.parseInt(element.getText().split("₽")[0].replaceAll(" ", ""));
    }

    protected void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driverManager.getDriver()).executeScript(
                "arguments[0].scrollIntoView();", element);
    }

    public WebElement scrollWithOffset(WebElement element, int x, int y) {
        String code = "window.scroll(" + (element.getLocation().x + x) + ","
                + (element.getLocation().y + y) + ");";
        ((JavascriptExecutor) driverManager.getDriver()).executeScript(code, element, x, y);
        return element;
    }

    protected WebElement waitElementToBeClicable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

}