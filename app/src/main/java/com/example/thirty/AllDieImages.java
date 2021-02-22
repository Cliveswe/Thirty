package com.example.thirty;

import com.example.thirty.dice.Die;

import java.util.HashMap;

/**
 * This is where the R.drawable.id of a die drawable in the application is mapped according to its
 * colour (grey, red or white) and its value (1 to 6).
 * <p>
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-02-03
 */
public class AllDieImages {
    private DiceImages allImagesOfDice;

    public AllDieImages() {
        allImagesOfDice = new DiceImages();
        this.addAllDieImages();
    }

    /**
     * Map all the die drawable id's to a key.
     */
    private void addAllDieImages() {
        addAllGreyDieImages();
        addAllRedDieImages();
        addAllWhiteDieImages();
    }

    /**
     * Mapping all red die drawable id to a key in a HashMap. The mapping key is the enum DieColourEnum.
     */
    private void addAllRedDieImages() {
        allImagesOfDice.addImage(DieColourEnum.RED, new HashMap<Integer, Integer>() {{
            put(1, R.drawable.red1);
            put(2, R.drawable.red2);
            put(3, R.drawable.red3);
            put(4, R.drawable.red4);
            put(5, R.drawable.red5);
            put(6, R.drawable.red6);
        }});
    }


    /**
     * Mapping all Grey die drawable id to a key in a HashMap. The mapping key is the enum DieColourEnum.
     */
    private void addAllGreyDieImages() {
        allImagesOfDice.addImage(DieColourEnum.GREY, new HashMap<Integer, Integer>() {{
            put(1, R.drawable.grey1);
            put(2, R.drawable.grey2);
            put(3, R.drawable.grey3);
            put(4, R.drawable.grey4);
            put(5, R.drawable.grey5);
            put(6, R.drawable.grey6);
        }});
    }


    /**
     * Mapping all white die drawable id to a key in a HashMap. The mapping key is the enum DieColourEnum.
     */
    public void addAllWhiteDieImages() {
        allImagesOfDice.addImage(DieColourEnum.WHITE, new HashMap<Integer, Integer>() {{
            put(1, R.drawable.white1);
            put(2, R.drawable.white2);
            put(3, R.drawable.white3);
            put(4, R.drawable.white4);
            put(5, R.drawable.white5);
            put(6, R.drawable.white6);
        }});
    }

    /**
     * Get the drawable id of a die given a key value pair for a die. The die can have a colour of
     * grey, red or white.
     *
     * @param key the colour of the die as a DieColourEnum.
     * @param num the number on the die, 1 to 6 as an int to be inbounds.
     * @return the drawable id for a die as an int, otherwise -1 on out of bounds.
     */
    public int getDieImage(DieColourEnum key, int num) {
        if ((num >= Die.die_min) && (num <= Die.die_max)) {
            return allImagesOfDice.getImageList(key).get(num);
        }
        return -1;
    }
}
