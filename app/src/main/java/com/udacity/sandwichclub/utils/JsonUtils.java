package com.udacity.sandwichclub.utils;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) {

        Sandwich sandwich = null;

        try {
            JSONObject sandwichObject = new JSONObject(json);

            JSONObject nameObject = sandwichObject.getJSONObject("name");

            sandwich = new Sandwich();

            sandwich.setMainName(nameObject.getString("mainName"));

            JSONArray alsoKnowAsArray = nameObject.getJSONArray("alsoKnownAs");

            if (alsoKnowAsArray == null) {
                sandwich.setAlsoKnownAs(Collections.<String>emptyList());
            } else {
                List<String> alsoKnownAs = new ArrayList<>();
                for (int i = 0; i < alsoKnowAsArray.length(); i++) {
                    alsoKnownAs.add(alsoKnowAsArray.getString(i));
                }
                sandwich.setAlsoKnownAs(alsoKnownAs);
            }

            sandwich.setPlaceOfOrigin(sandwichObject.getString("placeOfOrigin"));
            sandwich.setDescription(sandwichObject.getString("description"));
            sandwich.setImage(sandwichObject.getString("image"));

            JSONArray ingredientsArray = sandwichObject.getJSONArray("ingredients");

            if (ingredientsArray == null) {
                sandwich.setIngredients(Collections.<String>emptyList());
            } else {
                List<String> ingredients = new ArrayList<>();
                for (int i = 0; i < ingredientsArray.length(); i++) {
                    ingredients.add(ingredientsArray.getString(i));
                }
                sandwich.setIngredients(ingredients);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return sandwich;
    }
}
