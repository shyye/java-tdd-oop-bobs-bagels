package com.booleanuk.core.inventory;

import com.booleanuk.core.enums.BagelVariant;
import com.booleanuk.core.enums.CoffeeVariant;
import com.booleanuk.core.enums.FillingVariant;
import com.booleanuk.core.enums.ProductName;
import com.booleanuk.core.printgenerator.PrintGenerator;
import com.booleanuk.core.printgenerator.PrintInventoryMenu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {

    private Map<String, InventoryItem> inventoryItems;
    private ArrayList<SpecialOfferMultiPrice> specialOffersMultiPrice;
    private ArrayList<SpecialOfferCombination> specialOffersCombination;
    // Initialized locally
    private PrintGenerator menu;

    public Inventory() {
        this.inventoryItems = new HashMap<>();
        fillInventory();
        createSpecialOffers();
    }

    /**
     * Fill inventory with pre-prepared data.
     */
    private void fillInventory() {

        // TODO: Is it okay to initialize the data here?
        // For a more general approach -> Use Dependency Injection:
        // make product list as input instead of creating it here.

        // TODO: A bit weird approach to put it in a list first and then loop. Is it a better way?
        // I do this because I auto-generate the SKU value in the superclass 'InventoryItem'.
        // So I create all objects (BagelItem, CoffeItem, FillingItem) first, and store in a temporary list
        // Then I loop through all items to get the SKU value for each
        // I put the SKU value as the key in the HashMap 'inventoryItems'.
        // (The SKU value is then both in the objects and as a key)


        // Create list with inventory items
        ArrayList<InventoryItem> items = new ArrayList<>();

        items.add(new BagelItem(0.49f, BagelVariant.ONION));
        items.add(new BagelItem(0.39f, BagelVariant.PLAIN));
        items.add(new BagelItem(0.49f, BagelVariant.EVERYTHING));
        items.add(new BagelItem(0.49f, BagelVariant.SESAME));

        items.add(new CoffeeItem(0.99f, CoffeeVariant.BLACK));
        items.add(new CoffeeItem(1.19f, CoffeeVariant.WHITE));
        items.add(new CoffeeItem(1.29f, CoffeeVariant.CAPUCCINO));
        items.add(new CoffeeItem(1.29f, CoffeeVariant.LATTE));

        items.add(new FillingItem(0.12f, FillingVariant.BACON));
        items.add(new FillingItem(0.12f, FillingVariant.EGG));
        items.add(new FillingItem(0.12f, FillingVariant.CHEESE));
        items.add(new FillingItem(0.12f, FillingVariant.CREAM_CHEESE));
        items.add(new FillingItem(0.12f, FillingVariant.SMOKED_SALMON));
        items.add(new FillingItem(0.12f, FillingVariant.HAM));

        // Add items to inventoryItems and put the SKU value as the key
        for (InventoryItem item : items) {
            inventoryItems.put(item.getSKU(), item);
        }
    }

    // Create special offers
    private void createSpecialOffers() {

        specialOffersMultiPrice = new ArrayList<>();
        specialOffersCombination = new ArrayList<>();

        // Special offers Multi-Price
        SpecialOfferMultiPrice BAGOsixMultiPriceOffer = new SpecialOfferMultiPrice("BAGO", 6,2.49);
        SpecialOfferMultiPrice BAGEsixMultiPriceOffer = new SpecialOfferMultiPrice("BAGE",6,2.49);
        SpecialOfferMultiPrice BAGPtwelveMultiPriceOffer = new SpecialOfferMultiPrice("BAGP", 12,3.99);
        this.specialOffersMultiPrice.add(BAGOsixMultiPriceOffer);
        this.specialOffersMultiPrice.add(BAGEsixMultiPriceOffer);
        this.specialOffersMultiPrice.add(BAGPtwelveMultiPriceOffer);

        // Special offers Combination
        ArrayList<ProductName> offerItems = new ArrayList<>() {{
            add(ProductName.COFFEE);
            add(ProductName.BAGEL);
        }};
        SpecialOfferCombination coffeAndBagelOffer = new SpecialOfferCombination(offerItems, 1.25);
        this.specialOffersCombination.add(coffeAndBagelOffer);
    }

    public Map<String, InventoryItem> getAllItems() {
        return inventoryItems;
    }

    // Get all special offers
    // TODO: Check this structure, dublication code
//    public ArrayList<SpecialOffer> getSpecialOffers(SpecialOffer type) {
//        if (type instanceof SpecialOfferMultiPrice) {
//            return specialOffersMultiPrice;
//        }
//        return specialOffersCombination;
//    }

    public ArrayList<SpecialOfferMultiPrice> getSpecialOffersMultiPrice() {
        return specialOffersMultiPrice;
    }

    public ArrayList<SpecialOfferCombination> getSpecialOffersCombination() {
        return specialOffersCombination;
    }

    public InventoryItem getItem(String SKU) {

        // TODO: Should SKU be converted to uppercase here?

        InventoryItem item = inventoryItems.get(SKU);
        if (item == null) {
            throw new InventoryItemException("SKU '" + SKU + "' does not exist.");
        }
        return item;
    }

    public void printMenu() {

        // TODO: Should this be refactored, not initialize it here
        // Problem if so is that I need to pass in the inventoryItems

        menu = new PrintInventoryMenu(this.inventoryItems);
        menu.print();
    }
}
