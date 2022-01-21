package framework.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StartPage extends BasePage{


    @FindBy(xpath = "//h1[contains(text(), \"Найдено\")]")
    private WebElement listOfFinedProducts;

@Step
    public VariantsOfProductsPage startSearchProduct(String s)  {
        waitElementToBeClicable(searchLine);
        searchLine.click();
        searchLine.sendKeys(s);
        searchLine.submit();
        wait.until(ExpectedConditions.visibilityOf(listOfFinedProducts));
        return pageManager.getVariantsOfProductsPage();
    }

}
