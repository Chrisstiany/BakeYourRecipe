package iordache.cristian.bakeyourrecipe.RecipeList;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import iordache.cristian.bakeyourrecipe.R;
import iordache.cristian.bakeyourrecipe.RecipeListFragment;

public class DisplayRecipeList extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_recipe_list);

        //Define a new fragment
        RecipeListFragment recipeListFragment = new RecipeListFragment();

        //Use a FragmentManager and transaction to add the fragment to the screen
        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.recycleview_fragment, recipeListFragment)
                .commit();
    }
}
