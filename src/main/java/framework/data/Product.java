package framework.data;

import java.util.ArrayList;
import java.util.List;

public class Product {
    String name;
    int price;
    String guarantyDuration;
    int priceOfGuaranty;
    boolean guaranteeChoose;
    static List<Product> shoppingCart= new ArrayList<>();

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, int price, int priceOfGuaranty, boolean guaranteeChoose) {

        this.name = name;
        this.price = price;
        this.priceOfGuaranty = priceOfGuaranty;
        this.guaranteeChoose = guaranteeChoose;
    }

    public static void addToShoppingCart(Product product){
        shoppingCart.add(product);
    }

    public static void removeFromShoppingCart(Product product){
        shoppingCart.remove(product);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getGuarantyDuration() {
        return guarantyDuration;
    }

    public int getPriceOfGuaranty() {
        return priceOfGuaranty;
    }

    public boolean isGuaranteeChoose() {
        return guaranteeChoose;
    }

    public static List<Product> getShoppingCart() {
        return shoppingCart;
    }

    public static void removeProductFromCart(String name){
        getShoppingCart().removeIf(product -> product.getName().contains(name));
    }

    public static void addToCartByName(String name){
        Product item=null;
        for (Product product:getShoppingCart()) {
            if(product.getName().contains(name)){
                item=new Product(product.getName(), product.getPrice(), product.getPriceOfGuaranty(), product.isGuaranteeChoose());

            }
        }
        getShoppingCart().add(item);
        System.out.println("item " + item.getPriceOfGuaranty());

    }
}

