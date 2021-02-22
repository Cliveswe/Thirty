package com.example.thirty;

import java.util.HashMap;

/**
 * This is a container class that groups together a list of die.
 * <p>
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-02-03
 */
public class DiceImages {
    private HashMap<DieColourEnum, HashMap<Integer, Integer>> imageList;

    public DiceImages() {
        this.imageList = new HashMap<>();
    }

    /**
     * Insert a group of die into the list of dice. Use the parameter keyColour as a key to retreive
     * a list of dice.
     *
     * @param keyColour a key as a DieColourEnum.
     * @param dieImage  a group of die to be identified by the keyColour.
     */
    public void addImage(DieColourEnum keyColour, HashMap<Integer, Integer> dieImage) {
        imageList.put(keyColour, dieImage);
    }

    /**
     * Get a group of die given the key.
     *
     * @param key a key as a DieColourEnum.
     * @return a group of die was identified by the key. The returned group is a HashMap.
     */
    public HashMap<Integer, Integer> getImageList(DieColourEnum key) {
        return imageList.get(key);
    }
}
