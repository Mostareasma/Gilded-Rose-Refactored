package gildedrose;

public class GildedRose {

    private static final int MAX_QUALITY = 50;
    private static final int MIN_QUALITY = 0;

    public Item[] items;

    private static final String AGED_BRIE = "Aged Brie";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String CONJURED_MANA_CAKE = "Conjured Mana Cake";

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            updateItemQuality(item);
            if (!item.getName().equals(SULFURAS)) {
                item.setSellIn(item.getSellIn() - 1);
            }
        }
    }

    private void updateItemQuality(Item item) {
        switch (item.getName()) {
            case AGED_BRIE:
                updateAgedBrie(item);
                break;
            case BACKSTAGE_PASSES:
                updateBackstagePasses(item);
                break;
            case CONJURED_MANA_CAKE:

                updateConjured(item);

                break;
            case SULFURAS:
                item.setSellIn(0);
                item.setQuality(80);
                break;
            default:
                updateNormalItem(item);
                break;
        }
    }

    private void updateAgedBrie(Item item) {
        increaseQuality(item, 1);
        if (item.getSellIn() < 0) {
            increaseQuality(item, 1);
        }
    }

    private void updateBackstagePasses(Item item) {
        if (item.getSellIn() < 0) {
            item.setQuality(0);
        } else if (item.getSellIn() < 5) {
            increaseQuality(item, 3);
        } else if (item.getSellIn() < 10) {
            increaseQuality(item, 2);
        } else {
            increaseQuality(item, 1);
        }
    }

    private void updateConjured(Item item) {
        decreaseQuality(item, 2);
        if (item.getSellIn() <= 0) {
            decreaseQuality(item, 2);
        }
    }

    private void updateNormalItem(Item item) {
        decreaseQuality(item, 1);
        if (item.getSellIn() < 0) {
            decreaseQuality(item, 1);
        }
    }

    private void increaseQuality(Item item, int value) {
        int newQuality = item.getQuality() + value;
        item.setQuality(Math.min(newQuality, MAX_QUALITY));
    }

    private void decreaseQuality(Item item, int value) {
        int newQuality = item.getQuality() - value;
        item.setQuality(Math.max(newQuality, MIN_QUALITY));
    }
}
