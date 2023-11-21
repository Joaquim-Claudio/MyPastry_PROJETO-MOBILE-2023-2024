package pt.iade.mypastry.repositories;

import java.util.ArrayList;

import pt.iade.mypastry.R;
import pt.iade.mypastry.enums.ProductType;
import pt.iade.mypastry.models.Product;

public class ProductRepository {
    private static ArrayList<Product> products = new ArrayList<Product>();

    public static void populate() {

        //  Populating Menu Products
        products.add(new Product(1, ProductType.MENU,"Menu Pastel de Queijo", "Pastel de Queijo + Bebida + Sobremesa", 5f, R.drawable.pastel));
        products.add(new Product(2, ProductType.MENU,"Menu Pastel de Carne", "Pastel de Carne + Bebida + Sobremesa", 5f, R.drawable.meat_pastel_image));
        products.add(new Product(3, ProductType.MENU,"Menu Pastel de Frango", "Pastel de Frango + Bebida + Sobremesa", 5f, R.drawable.chicken_pastel_image));
        products.add(new Product(4, ProductType.MENU,"Menu Pastel de Pizza", "Pastel de Pizza + Bebida + Sobremesa", 5f, R.drawable.pizza_pastel_image));
        products.add(new Product(5, ProductType.MENU,"Menu Cachorro", "Cachorro + Bebida + Sobremesa", 7f, R.drawable.hot_dog_promo));
        products.add(new Product(6, ProductType.MENU,"Menu Hamburger", "Hamburger + Bebida + Sobremesa", 10f, R.drawable.hamburguer_image));
        products.add(new Product(7, ProductType.MENU,"Menu X-Tudo", "X-Tudo + Bebida + Sobremesa", 10f, R.drawable.xtudo_image));
        products.add(new Product(8, ProductType.MENU,"Menu X-Picanha", "X-Picanha + Bebida + Sobremesa", 11f, R.drawable.xpicanha_image));
        products.add(new Product(9, ProductType.MENU,"Menu Coxinha", "Coxinha + Bebida", 4f, R.drawable.coxinha_image));
        products.add(new Product(10, ProductType.MENU,"Menu Kibe", "Kibe + Bebida", 4f, R.drawable.kibe_image));
        products.add(new Product(11, ProductType.MENU,"Menu Esfiha", "Esfiha + Bebida", 4f, R.drawable.esfiha_image));

        //  Populating Drink Products
        products.add(new Product(12, ProductType.DRINK,"Guaraná Antártica", "Lata 33cl", 1.8f, R.drawable.guarana_image));
        products.add(new Product(13, ProductType.DRINK,"Coca-Cola", "Lata 33cl", 1.8f, R.drawable.coca_cola_image));
        products.add(new Product(14, ProductType.DRINK,"Fanta Laranja", "Lata 33cl", 1.8f, R.drawable.fanta_image));
        products.add(new Product(15, ProductType.DRINK,"Caldo de Cana", "Copo 40cl", 3f, R.drawable.canejuice_image));
        products.add(new Product(16, ProductType.DRINK,"Sumo de Laranja", "Copo 40cl", 3f, R.drawable.orange_juice_image));
        products.add(new Product(17, ProductType.DRINK,"Sumo de Goiaba", "Copo 40cl", 3f, R.drawable.guava_juice_image));
        products.add(new Product(18, ProductType.DRINK,"Sumo de manga", "Copo 40cl", 3f, R.drawable.mango_juice_image));
        products.add(new Product(19, ProductType.DRINK,"Super Bock", "Garrafa 33cl", 2f, R.drawable.superbock_image));
        products.add(new Product(20, ProductType.DRINK,"Heineken", "Garrafa 33cl", 2f, R.drawable.heineken_image));
        products.add(new Product(21, ProductType.DRINK,"Somersby", "Lata 50cl", 1.5f, R.drawable.somersby_image));

        //  Populating Dessert Products
        products.add(new Product(22, ProductType.DESSERT,"Bolo de Cenoura", "Fatia de bolo", 2f, R.drawable.carrot_cake_image));
        products.add(new Product(23, ProductType.DESSERT,"Bolo de Chocolate", "Fatia de bolo", 2f, R.drawable.chocolate_cake_image));
        products.add(new Product(24, ProductType.DESSERT,"Bolo de Fubá", "Fatia de bolo", 2f, R.drawable.fuba_cake_image));
        products.add(new Product(25, ProductType.DESSERT,"Mousse de Lima", "Embalagem 25cl", 3f, R.drawable.lemon_mousse_image));
        products.add(new Product(26, ProductType.DESSERT,"Mousse de Maracujá", "Embalagem 25cl", 3f, R.drawable.passionfruit_mousse_image));
        products.add(new Product(27, ProductType.DESSERT,"Pudim", "Embalagem 25cl", 3f, R.drawable.pudding_image));
        products.add(new Product(28, ProductType.DESSERT,"Baba de Camelo", "Embalagem 25cl", 3f, R.drawable.babacamelo_image));

        //  Populating Delicacies Products
        products.add(new Product(29, ProductType.DELICACY,"Açaí", "Pote 100g", 3f, R.drawable.acai_image));
        products.add(new Product(30, ProductType.DELICACY,"Pamonha", "Pote 100g", 3f, R.drawable.pamonha_image));
        products.add(new Product(31, ProductType.DELICACY,"Mungunzá/Canjica", "Pote 100g", 3f, R.drawable.mungunza_image));
        products.add(new Product(32, ProductType.DELICACY,"Acarajé", "Pote 100g", 3f, R.drawable.acaraje_image));
        products.add(new Product(33, ProductType.DELICACY,"Bolo de Rolo", "Pote 100g", 3f, R.drawable.boloderolo_image));
        products.add(new Product(34, ProductType.DELICACY,"Calabresa Acebolada", "Pote 100g", 3f, R.drawable.calabresa_image));
        products.add(new Product(35, ProductType.DELICACY,"Cuscuz", "Pote 100g", 3f, R.drawable.cuscuz_image));
        products.add(new Product(36, ProductType.DELICACY,"Tapioca", "Pote 100g", 3f, R.drawable.tapioca_images));
        products.add(new Product(37, ProductType.DELICACY,"Queijo Coalho", "Pote 100g", 3f, R.drawable.coalhocheese_image));

    }

    public static ArrayList<Product> getProducts(){
        return products;
    }

    public static Product getProduct(int id){
        for (Product p : products){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    public static void addProduct(Product product){
        products.add(product);
    }

    public static boolean removeProduct(int id){
        return products.removeIf(p -> p.getId() == id);
    }
}
