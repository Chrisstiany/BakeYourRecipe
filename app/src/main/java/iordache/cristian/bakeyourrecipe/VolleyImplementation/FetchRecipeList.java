package iordache.cristian.bakeyourrecipe.VolleyImplementation;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import iordache.cristian.bakeyourrecipe.RecipeList.RecipeClass;
import iordache.cristian.bakeyourrecipe.RecipeList.RecipeIngredientsClass;
import iordache.cristian.bakeyourrecipe.RecipeList.RecipeStepsClass;

/**
 * Created by cii51253 on 01/06/2017.
 */

public class FetchRecipeList {

    //Application context
    Context mContext;

    //This is the final list we are fetching from server
    ArrayList<RecipeClass> recipeClassArrayList = new ArrayList<>();

    //URL for Json request
    private String urlJasonArray = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    // temporary string to show the parsed response
    private String jsonResponse;

    //Error Handling Message
    private final static String ERROR = "Could not connect to the server";



    public FetchRecipeList(Context mContext) {
        this.mContext = mContext;
    }

    public ArrayList<RecipeClass> getRecipeList(){

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlJasonArray, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;

                        while (count < response.length()) {

                            try {
                                //JSONObject jsonObject = (JSONObject) response.get(count);


                                JSONObject recipe = (JSONObject) response.get(count);

                                String recipeName = recipe.getString("name");
                                int id = recipe.getInt("id");

                                //Fetch the ingredients List
                               // JSONObject recipeIngredients = (JSONObject) recipe.getJSONObject("ingredients");
                                JSONArray recipeIngredients = recipe.getJSONArray("ingredients");

                                //if (recipeIngredients != null) {

                                ArrayList<RecipeIngredientsClass> recipeIngredientsClass = new ArrayList<>();
                                for (int j = 0; j < recipeIngredients.length(); j ++) {
                                    JSONObject recipeIngredientsObject = recipeIngredients.getJSONObject(j);
                                    int quantity = recipeIngredientsObject.getInt("quantity");
                                    String measure = recipeIngredientsObject.getString("measure");
                                    String ingredient = recipeIngredientsObject.getString("ingredient");
                                    recipeIngredientsClass.add(new RecipeIngredientsClass(quantity, measure, ingredient));
                                }

                                //Fetch the steps list
                                //JSONObject recipeSteps = (JSONObject) recipe.getJSONObject("steps");
                                JSONArray recipeSteps = recipe.getJSONArray("steps");

                                ArrayList<RecipeStepsClass> recipeStepsClass = new ArrayList<>();
                                for (int k = 0; k <  recipeSteps.length(); k++) {

                                    JSONObject recipeStepsObject = recipeSteps.getJSONObject(k);
                                    int stepId = recipeStepsObject.getInt("id");
                                    String sDescription = recipeStepsObject.getString("shortDescription");
                                    String description = recipeStepsObject.getString("description");
                                    String videoUrl = recipeStepsObject.getString("videoURL");
                                    String thumbnailUrl = recipeStepsObject.getString("thumbnailURL");
                                    recipeStepsClass.add(new RecipeStepsClass(stepId, sDescription, description, videoUrl, thumbnailUrl));
                                }

                                int recipeServings = recipe.getInt("servings");
                                String recipeImage = recipe.getString("image");

                                RecipeClass recipeClass = new RecipeClass(recipeName, recipeIngredientsClass, recipeStepsClass, recipeServings, recipeImage, recipeSteps.length());

                                recipeClassArrayList.add(count, recipeClass);
                                count++;

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(mContext, ERROR, Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                        // As of f605da3 the following should work
                        NetworkResponse response = error.networkResponse;
                        if (error instanceof ServerError && response != null) {
                            try {
                                String res = new String(response.data,
                                        HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                                // Now you can use any deserializer to make sense of data
                                JSONObject obj = new JSONObject(res);
                            } catch (UnsupportedEncodingException e1) {
                                // Couldn't properly decode data to string
                                e1.printStackTrace();
                            } catch (JSONException e2) {
                                // returned data is not JSONObject?
                                e2.printStackTrace();
                            }
                        }
                    }
                }
        );

        VolleyFetchHandler.getmInstance(mContext).addToRequesQueue(jsonArrayRequest);

        return recipeClassArrayList;
    }

    public ArrayList<RecipeClass> returnRecipeList() {
        return recipeClassArrayList;
    }
}
