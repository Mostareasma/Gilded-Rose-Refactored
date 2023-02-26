import gildedrose.GildedRose;
import gildedrose.Item;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class GildedRoseTest {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";


    @Test
    public void testRegularItem() {
        
        // Given
        Item[] items = new Item[] {
                new Item("Regular Item1", 15, 25),
                new Item("Regular Item2", 3, 45),
                new Item("Regular Item3", -1, 0),
                new Item("Regular Item4", 80, 1)
        };

        GildedRose app = new GildedRose(items);

        // When

        int days = 15;
        for (int i = 0; i < days; i++) {
            app.updateQuality();
        }

        // Then
        assertEquals(10, items[0].getQuality());
        assertEquals(0, items[0].getSellIn());

        assertEquals(18, items[1].getQuality());
        assertEquals(-12, items[1].getSellIn());

        assertEquals(0, items[2].getQuality());
        assertEquals(-16, items[2].getSellIn());

        assertEquals(0, items[3].getQuality());
        assertEquals(65, items[3].getSellIn());
    }

    @Test
    public void testUpdateQualityForAgedBrie() {

        // Given
        Item[] items = new Item[] { new Item(AGED_BRIE, 10, 0) };
        GildedRose gildedRose = new GildedRose(items);

        // When
        int days = 30;
        for (int i = 0; i < days; i++) {
            gildedRose.updateQuality();
        }

        // Then
        assertEquals(50, items[0].getQuality());
        assertEquals(-20, items[0].getSellIn());
    }

    @Test
    public void testUpdateQualityForBackstagePasses() {

        // Given
        Item[] items = new Item[] {
                new Item(BACKSTAGE_PASSES, 15, 20),
                new Item(BACKSTAGE_PASSES, 14, 20)
        };
        GildedRose gildedRose = new GildedRose(items);

        // When
        int days = 15;
        for (int i = 0; i < days; i++) {
            gildedRose.updateQuality();
        }

        // Then
        assertEquals(50, items[0].getQuality());
        assertEquals(0, items[0].getSellIn());

        assertEquals(0, items[1].getQuality());
        assertEquals(-1, items[1].getSellIn());
    }

    @Test
    public void testUpdateQualityForSulfuras() { 

        // Given
        Item[] items = new Item[] {
                new Item(SULFURAS, 0, 80),
                new Item(SULFURAS, 15, 40)
        };
        GildedRose gildedRose = new GildedRose(items);

        // When
        int days = 15;
        for (int i = 0; i < days; i++) {
            gildedRose.updateQuality();
        }

        // Then
        assertEquals(80, items[0].getQuality());
        assertEquals(0, items[0].getSellIn());

        assertEquals(80, items[1].getQuality());
        assertEquals(0, items[1].getSellIn());
    }

    @Test
    public void testUpdateQualityForConjuredManaCake() {    

        // Given
        Item[] items = new Item[] {
            new Item(CONJURED_MANA_CAKE, 10, 50),
            new Item(CONJURED_MANA_CAKE, 0, 50),
            new Item(CONJURED_MANA_CAKE, 10, 15),
        };
        GildedRose gildedRose = new GildedRose(items);
    
        
        // When
        int days = 10;
        for (int i = 0; i < days; i++) {
            gildedRose.updateQuality();
        }

        // Then
        assertEquals(30, items[0].getQuality());
        assertEquals(0, items[0].getSellIn());

        assertEquals(10, items[1].getQuality());
        assertEquals(-10, items[1].getSellIn());

        assertEquals(0, items[2].getQuality());
        assertEquals(0, items[2].getSellIn());
    
    }
    
}
