package com.example.thirty.util;

import android.app.Activity;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thirty.AllDieImages;
import com.example.thirty.DieColourEnum;
import com.example.thirty.R;
import com.example.thirty.dice.Dice;

import java.util.HashMap;

/**
 * This is a static class is used to choose the colour of the dice used in the game.
 * <p>
 * Author: Clive Leddy
 * Email: clive@cliveleddy.com
 * Date: 2021-02-04
 */
public class SetImageButtonsDice extends AppCompatActivity {

    /**
     * Change the image button image in the HashMap of image buttons. The image displayed is
     * determined by the parameters primaryColour and secondaryColour.
     *
     * @param activity        the activity sent as Activity.
     * @param primaryColour   a colour as an Enum in DieColourEnum
     * @param secondaryColour a colour as an Enum in DieColourEnum
     * @param mImageButtons   a list of image buttons that is identified bya a set of keys mapped to
     *                        the ImageButton widget id in the application.
     * @param d               an object that contains a number of die.
     */
    public static void setDiceImageButtonsImage(Activity activity,
                                                DieColourEnum primaryColour,
                                                DieColourEnum secondaryColour,
                                                final HashMap<Integer, Integer> mImageButtons,
                                                final Dice d) {

        //all the die images
        AllDieImages allDieImageButtons = new AllDieImages();

        for (Integer element : mImageButtons.keySet()) {
            ImageButton ib = activity.findViewById(mImageButtons.get(element));
            if (!d.getDie(element).isSelected()) {
                ib.setImageResource(
                        getDieImageButton(element, allDieImageButtons, primaryColour, d));
            } else {
                ib.setImageResource(
                        getDieImageButton(element, allDieImageButtons, secondaryColour, d));
            }
        }
    }

    /**
     * Get a specific die button image.
     *
     * @param element            an identifier to choose the die image as int.
     * @param allDieImageButtons a collections of all the available die images as AllDieImages.
     * @param colour             the colour of the die to choose as DieColourEnum.
     * @param d                  a set of die as Dice.
     * @return the drawable id of an die image as int.
     */
    private static int getDieImageButton(Integer element,
                                         AllDieImages allDieImageButtons,
                                         DieColourEnum colour, Dice d) {

        return allDieImageButtons.getDieImage(
                colour,
                d.getDie(element).getDieValue()
        );
    }

    /**
     * Create a set that contains all the die image buttons. Each image button will have a identification
     * as a key mapped to the image buttons R.id value.
     *
     * @param mAllImageButtons a collection button id's identified with a key set as a type HashMap.
     */
    public static void dieImageButtonsId(HashMap<Integer, Integer> mAllImageButtons) {
        mAllImageButtons.put(1, R.id.die_Button_1);
        mAllImageButtons.put(2, R.id.die_Button_2);
        mAllImageButtons.put(3, R.id.die_Button_3);
        mAllImageButtons.put(4, R.id.die_Button_4);
        mAllImageButtons.put(5, R.id.die_Button_5);
        mAllImageButtons.put(6, R.id.die_Button_6);
    }
}
