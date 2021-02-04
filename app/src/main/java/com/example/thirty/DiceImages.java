/**
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-02-03
 */
package com.example.thirty;

import java.util.HashMap;

public class DiceImages {
    private HashMap<DieColourEnum, HashMap<Integer, Integer>> imageList;

    public DiceImages() {
        this.imageList = new HashMap<DieColourEnum, HashMap<Integer, Integer>>();
    }

    public void addImage(DieColourEnum keyColour, HashMap<Integer, Integer> dieImage) {
        //System.out.println("dieImage " + dieImage);
        //System.out.println("key " + keyColour);
        imageList.put(keyColour, dieImage);
    }

    public HashMap<Integer, Integer> getImageList(DieColourEnum key) {
        return imageList.get(key);
    }
}
