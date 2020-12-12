import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartAddItemTest {
    @BeforeEach
    void setUp() {

    }

    @Test
    void testAddItemWithWrongTitle() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("", 1, 1, Item.Type.REGULAR),
                "Test empty title is failed");
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem(null, 1, 1, Item.Type.REGULAR),
                "Test null title is failed");
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Item With 33 Characters Title....", 1, 1, Item.Type.REGULAR),
                "Test 33 characters title is failed");
        assertDoesNotThrow(() -> cart.addItem("1", 1, 1, Item.Type.REGULAR),
                "Test 1 character title is failed");
        assertDoesNotThrow(() -> cart.addItem("Item With 32 Characters Title...", 1, 1, Item.Type.REGULAR),
                "Test 32 characters title is failed");
    }

    @Test
    void testAddItemWithWrongPrice() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Item With 0 Price", 0, 1, Item.Type.REGULAR),
                "Test 0 price item is failed");
        assertThrows(IllegalArgumentException.class,
                () -> cart.addItem("Item With 1000 Price", 1000, 1, Item.Type.REGULAR),
                "Test 1000 price item is failed");
        assertDoesNotThrow(() -> cart.addItem("Item With 1 Price", 1, 1, Item.Type.REGULAR),
                "Test 1 price item is failed");
        assertDoesNotThrow(() -> cart.addItem("Item With 999 Price", 999, 1, Item.Type.REGULAR),
                "Test 999 price item is failed");
    }

    @Test
    void testAddItemWithWrongQuantity() {
        ShoppingCart cart = new ShoppingCart();
        assertThrows(Exception.class,
                () -> cart.addItem("Item With 0 Quantity", 1, 0, Item.Type.REGULAR),
                "Test 0 quantity item is failed");
        assertThrows(Exception.class,
                () -> cart.addItem("Item With 999 Quantity", 1, 1001, Item.Type.REGULAR),
                "Test 1001 quantity item is failed");
        assertDoesNotThrow(() -> cart.addItem("Item With 1 Quantity", 1, 1, Item.Type.REGULAR),
                "Test 1 quantity item is failed");
        assertDoesNotThrow(() -> cart.addItem("Item With 1000 Quantity", 1, 1000, Item.Type.REGULAR),
                "Test 1000 quantity item is failed");
    }

    @Test
    void testAddItemWithWrongType() {
        //impossible to set item type not from Item.Type
    }

    @Test
    void testAddHundredItems() {
        ShoppingCart cart = new ShoppingCart();
        //adding 98 items
        for (int i = 1; i < 99; i++) {
            cart.addItem("Item " + i, 1, 1, Item.Type.REGULAR);
        }
        //adding 99th item
        assertDoesNotThrow(() -> cart.addItem("Item 99", 1, 1, Item.Type.REGULAR),
                "Test adding of 99 items is failed");
        //adding 100th item
        assertThrows(IndexOutOfBoundsException.class,
                () -> cart.addItem("Item 100", 1, 1, Item.Type.REGULAR),
                "Test test adding of 100 items is failed");
    }
}