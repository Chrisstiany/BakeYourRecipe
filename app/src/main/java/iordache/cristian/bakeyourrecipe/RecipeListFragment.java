package iordache.cristian.bakeyourrecipe;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ProgressBar;
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
import iordache.cristian.bakeyourrecipe.RecipeList.RecipeListRecycleViewAdapter;
import iordache.cristian.bakeyourrecipe.RecipeList.RecipeRecyclerViewItemClickListener;
import iordache.cristian.bakeyourrecipe.RecipeList.RecipeStepsClass;
import iordache.cristian.bakeyourrecipe.VolleyImplementation.VolleyFetchHandler;

/**
 * Created by cii51253 on 05/06/2017.
 */

public class RecipeListFragment extends Fragment {


    private static final String KEY_LAYOUT_MANAGER = "layoutManager";

    //Parameters for displaying the list of recipes in the RecycleView
    protected RecyclerView recipeListRV;
    protected RecipeListRecycleViewAdapter adapter;
    GridLayoutManager layoutManager;

    //This is to store our recipe list in
    ArrayList<RecipeClass> arrayList = new ArrayList<>();

    //This is the final list we are fetching from server
    final static ArrayList<RecipeClass> recipeClassArrayList = new ArrayList<>();

    //URL for Json request
    private String urlJasonArray = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    //Error Handling Message
    private final static String ERROR = "Could not connect to the server";

    protected LayoutManagerType mCurrentLayoutManagerType;

    //We define an interface OnRecipeClickListener that triggers a callback in the host activity
    //We define an interface OnRecipeClickListener that triggers a callback in the host activity
    OnRecipeClickListener mCallback;


    //OnRecipeClickListener interfae, calls a method in the host activity named onImageSelected
    public interface OnRecipeClickListener{
        void onRecipeSelected(ArrayList<RecipeClass> recipeList, int position);
    }

    private void onRecipeSelected(ArrayList<RecipeClass> recipeList, int position){
        if(mCallback != null) {
            mCallback.onRecipeSelected(recipeClassArrayList, position);
        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        //This makes sure that the host activity has implemented the callback interface
        //If not, it throws an exception
        try{
            mCallback = (OnRecipeClickListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnRecipeClickListener");
        }
    }

    //Mandatory constructor for instantiating the fragment
    public RecipeListFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Inflates the fragment layout
     * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, final Bundle savedInstanceState) {

        //Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.activity_display_recipe_list, container, false);

        ProgressBar progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
        progressBar.setVisibility(View.GONE);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, urlJasonArray, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int count = 0;

                        while (count < response.length()) {

                            try {
                                JSONObject recipe = (JSONObject) response.get(count);

                                String recipeName = recipe.getString("name");
                                int id = recipe.getInt("id");

                                //Fetch the ingredients List
                                JSONArray recipeIngredients = recipe.getJSONArray("ingredients");

                                ArrayList<RecipeIngredientsClass> recipeIngredientsClass = new ArrayList<>();
                                for (int j = 0; j < recipeIngredients.length(); j++) {
                                    JSONObject recipeIngredientsObject = recipeIngredients.getJSONObject(j);
                                    int quantity = recipeIngredientsObject.getInt("quantity");
                                    String measure = recipeIngredientsObject.getString("measure");
                                    String ingredient = recipeIngredientsObject.getString("ingredient");
                                    recipeIngredientsClass.add(new RecipeIngredientsClass(quantity, measure, ingredient));
                                }

                                //Fetch the steps list
                                JSONArray recipeSteps = recipe.getJSONArray("steps");

                                ArrayList<RecipeStepsClass> recipeStepsClass = new ArrayList<>();
                                for (int k = 0; k < recipeSteps.length(); k++) {

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

                                final RecipeClass recipeClass = new RecipeClass(recipeName, recipeIngredientsClass, recipeStepsClass, recipeServings, recipeImage, recipeSteps.length());

                                recipeClassArrayList.add(count, recipeClass);
                                count++;


                                layoutManager = new GridLayoutManager(getContext(), 1);

                                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER.LINEAR_LAYOUT_MANAGER;

                                recipeListRV = (RecyclerView) rootView.findViewById(R.id.recipeListRecycleView);
                                if (savedInstanceState != null) {
                                    // Restore saved layout manager type.
                                    mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                                            .getSerializable(KEY_LAYOUT_MANAGER);
                                }

                                //Setting up the number of columns
                                int gridViewEntrySize = getResources().getDimensionPixelSize(R.dimen.grip_view_entry_size);
                                ;
                                int gridViewSpacing = getResources().getDimensionPixelSize(R.dimen.grip_view_spacing);
                                ;

                                WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
                                Display display = wm.getDefaultDisplay();

                                int numColumns = (display.getWidth() - gridViewSpacing) / (gridViewEntrySize + gridViewSpacing);
                                //this enforces that there are atleast two columns in the grid
                                //numColumns = numColumns > 1 ? numColumns : 2;

                                layoutManager.setSpanCount(1);

                                //setRecyclerViewLayoutManager(mCurrentLayoutManagerType);
                                recipeListRV.setLayoutManager(layoutManager);
                                adapter = new RecipeListRecycleViewAdapter(getContext(), recipeClassArrayList);

                                adapter.notifyDataSetChanged();

                                recipeListRV.setAdapter(adapter);


                                recipeListRV.addOnItemTouchListener(new RecipeRecyclerViewItemClickListener(getContext(), new RecipeRecyclerViewItemClickListener.OnItemClickListener(){


                                            @Override
                                            public void onItemClick(View view, int position) {

                                                mCallback.onRecipeSelected(recipeClassArrayList, position);

                                                /**Intent intent = new Intent(getContext(), RecipeDetailsMainActivity.class);
                                                 intent.putExtra("RecipeName", recipeClassArrayList.get(position).getNameOfTheRecipe());
                                                 intent.putExtra("RecipeIngredients", recipeClassArrayList.get(position).getRecipeIngredients());
                                                 intent.putExtra("RecipeSteps", recipeClassArrayList.get(position).getRecipeSteps());
                                                 intent.putExtra("RecipeServings", recipeClassArrayList.get(position).getRecipeServings());
                                                 intent.putExtra("RecipeNoOfSteps", recipeClassArrayList.get(position).getNumberOfSteps());
                                                 startActivity(intent);*/

                                            }
                                        })
                                );
                                //adapter.setOnRecipeClickListener(this);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(getContext(), ERROR, Toast.LENGTH_SHORT).show();
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

        // Adding request to request queue
        VolleyFetchHandler.getmInstance(getContext()).addToRequesQueue(jsonArrayRequest);

        return rootView;
    }

    public RecipeClass getRecipeList(int position){

        return recipeClassArrayList.get(position);
    }

    public static RecipeListFragment newInstance() {

        RecipeListFragment f = new RecipeListFragment();
        // Supply index input as an argument.
        Bundle args = new Bundle();
        args.putParcelableArrayList("recipeList", recipeClassArrayList);
        f.setArguments(args);
        return f;
    }
}
