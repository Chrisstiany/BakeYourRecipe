package iordache.cristian.bakeyourrecipe.RecipeDetails;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import iordache.cristian.bakeyourrecipe.R;

/**
 * Created by cii51253 on 09/06/2017.
 */

public class RecipeDetailsMainActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_recipe_details);

        RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        fragmentManager.beginTransaction()
                .add(R.id.recipe_details_fragment, recipeDetailsFragment)
                .commit();

    }
}
