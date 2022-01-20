package framework.managers;

import framework.pages.ProductInformationPage;
import framework.pages.ShoppingCartPage;
import framework.pages.StartPage;
import framework.pages.VariantsOfProductsPage;

public class PageManager {
    private static PageManager INSTANCE =null;
    private StartPage startPage;
    private VariantsOfProductsPage variantsOfProductsPage;
    private ProductInformationPage productInformationPage;
    private ShoppingCartPage shoppingCartPage;
    private PageManager(){

    }
    public static PageManager getInstance(){
        if(INSTANCE==null){
            INSTANCE= new PageManager();
        }
        return INSTANCE;
    }

    public StartPage getStartPage(){
        if(startPage==null){
            startPage=new StartPage();
        }
        return startPage;
    }

    public VariantsOfProductsPage getVariantsOfProductsPage(){
        if(variantsOfProductsPage==null){
            variantsOfProductsPage=new VariantsOfProductsPage();
        }
        return variantsOfProductsPage;
    }
    public ProductInformationPage getProductInformationPage(){
        if(productInformationPage==null){
            productInformationPage=new ProductInformationPage();
        }
        return productInformationPage;
    }
    public ShoppingCartPage getShoppingCartPage(){
        if(shoppingCartPage==null){
            shoppingCartPage=new ShoppingCartPage();
        }
        return shoppingCartPage;
    }
}
