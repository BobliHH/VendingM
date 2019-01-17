package VM;

import Model.Coin;
import Model.CurrancyType;
import Model.Product;
import Service.IOService;

import java.util.LinkedHashMap;
import java.util.Map;

public class VendingMachine {

    private CurrancyType currancyType = CurrancyType.RON;

    private Map<Coin, Integer> coinStock = new LinkedHashMap();

    private Map<Product, Integer> productStock = new LinkedHashMap();

    private IOService ioService;


    public VendingMachine() {
        ioService = new IOService();
        Product cola = new Product(100, "Cola", 5, "0.5l");
        Product fanta = new Product(101, "Fanta", 5, "0.5l");
        Product dorna = new Product(102, "Dorna", 3, "0.5l");
        Product corn = new Product(200, "Corn", 3, "100g");

        productStock.put(cola, 10);
        productStock.put(fanta, 3);
        productStock.put(dorna, 8);
        productStock.put(corn, 6);

        Coin unRon = new Coin(1001, 1);
        Coin cinciRon = new Coin(1002, 5);
        Coin zeceRon = new Coin(1003, 10);
        coinStock.put(unRon, 100);
        coinStock.put(cinciRon, 20);
        coinStock.put(zeceRon, 10);

    }

    public void run() {
        ioService.displayMessege();
        ioService.displayProductMenu(productStock);
        ioService.displayCoinMenu(coinStock);
        ioService.selectProduct(productStock);
        ioService.insertCoin(coinStock, productStock);
        ioService.payRest();
        ioService.deliverProduct(productStock, coinStock);
        ioService.manageProductStock(productStock);
        ioService.manageRest(coinStock);
        this.run();

    }
}
