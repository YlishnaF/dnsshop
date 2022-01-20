package framework.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class StartPage extends BasePage{


    @FindBy(xpath = "//h1[contains(text(), \"Найдено\")]")
    private WebElement listOfFinedProducts;

    public VariantsOfProductsPage startSearchProduct(String s)  {
        waitElementToBeClicable(searchLine);
        searchLine.click();
        searchLine.sendKeys(s);
        searchLine.submit();
        wait.until(ExpectedConditions.visibilityOf(listOfFinedProducts));
        return pageManager.getVariantsOfProductsPage();
    }

}
