package Service;

import Model.Coin;
import Model.CurrancyType;
import Model.Product;

import java.util.Map;
import java.util.Scanner;

public class IOService {
    Integer numberOfProducts;
    Integer numberOfCoins;
    Integer productPrice = 0;
    boolean correctProdSelection = false;
    Integer sumInserted = 0;
    Integer rest = 0;

    public static void displayProductMenu(Map<Product, Integer> productStock) {
        System.out.println("Prod.COD" + '\t' + "Prod.NAME" + '\t' + "Prod.SIZE" + '\t' + "   " + "Prod.PRICE");
        System.out.println(" ");
        for (Product product : productStock.keySet()) {
            System.out.println(product.getCod() + "\t " + "\t " + " " + product.getName() + " \t" + " " + "\t " + product.getSize() + '\t' + "\t " + product.getPrice() + " [" + CurrancyType.RON.toString() + "]");

        }
        System.out.println();

    }

    public static void displayCoinMenu(Map<Coin, Integer> coinStock) {
        System.out.println("Currancy : " + CurrancyType.RON.toString());
        System.out.println();
        System.out.println("Coin.COD" + '\t' + "Coin.Value");
        System.out.println("  ");
        for (Coin coin : coinStock.keySet()) {
            System.out.println(coin.getCod() + "\t" + "    " + coin.getValue() + " [" + CurrancyType.RON.toString() + "]");
        }
    }

    public void displayMessege() {
        System.out.println("Welcome!");

    }

    public Integer selectProduct(Map<Product, Integer> products) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the code of the product you want to buy: ");
        Integer optionProduct = scanner.nextInt();
        for (Product product : products.keySet()) {
            if (optionProduct == product.getCod()) {
                System.out.println(" You selection is >>> " + product.getName());
                System.out.println(" The price for a " + product.getName() + ">>> " + product.getPrice() + " " + CurrancyType.RON);
                numberOfProducts = (Integer) products.get(product);
                System.out.println(" We have " + numberOfProducts + " " + product.getName() + "available");
                productPrice = product.getPrice();
                correctProdSelection = true;
            }
        }
        return null;
    }

    public Integer insertCoin(Map<Coin, Integer> coins, Map<Product, Integer> products) {

        Scanner scanner = new Scanner(System.in);
        if (correctProdSelection && numberOfProducts > 0) {
            while (sumInserted < productPrice) {
                System.out.println("INSERT SUM : ");
                Integer optionCoin = scanner.nextInt();
                for (Coin coin : coins.keySet()) {
                    if (optionCoin.equals(coin.getCod())) {
                        sumInserted = sumInserted + coin.getValue();
                        System.out.println("SUM inserted >>> " + sumInserted + " " + CurrancyType.RON.toString());
                    }
                }
            }
            System.out.println("SUM inserted >>> " + sumInserted + " " + CurrancyType.RON);
            System.out.println("PRICE >>> " + productPrice + " " + CurrancyType.RON);
        }
        return sumInserted;
    }

    public Integer payRest() {
        if (sumInserted >= productPrice && correctProdSelection) {
            rest = sumInserted - productPrice;
            System.out.println("__________________________________________");
            System.out.println("Rest >>>" + rest + " " + CurrancyType.RON.toString());
        }

        return rest;
    }

    public void deliverProduct(Map<Product, Integer> products, Map<Coin, Integer> coins) {
        if (sumInserted >= productPrice && correctProdSelection) {
            System.out.println("Thank you for the purchase.");
        } else {
            while (!correctProdSelection) {
                System.out.println("INVALID PRODUCT CODE !!! ");
                selectProduct(products);
                insertCoin(coins, products);
                payRest();
                deliverProduct(products, coins);
            }
        }
    }

    public Integer manageProductStock(Map<Product, Integer> products) {
        if (numberOfProducts == 0) {
            System.out.println(" We are sorry. Product not available.");
        }

        if (sumInserted >= productPrice && numberOfProducts > 0) {
            numberOfProducts = numberOfProducts - 1;
        }

        System.out.println(" Number of product remaining in stock --> " + numberOfProducts);
        return numberOfProducts;
    }

    public Integer manageRest(Map<Coin, Integer> coins) {
        Integer coinStock = 0;


        for (Coin coin : coins.keySet()) {
            numberOfCoins = coins.get(coin);
            Integer maxCoin = (Integer) coins.values().toArray()[2];

            if (coin.getValue().equals(coins.values().toArray()[0])) {
                coinStock = coins.get(coin) * coin.getValue();

                if (coinStock >= maxCoin && coinStock >= rest) {
                    System.out.println("Rest available");
                    coins.values().toArray()[0] = (Integer) coins.values().toArray()[0] - rest;
                } else {
                    System.out.println("Rest not available.");

                }
            }

        }
        return coinStock;
    }

}
