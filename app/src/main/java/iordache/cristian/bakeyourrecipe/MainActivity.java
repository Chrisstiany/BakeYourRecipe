package iordache.cristian.bakeyourrecipe;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import java.util.ArrayList;

import iordache.cristian.bakeyourrecipe.RecipeDetails.RecipeDetailsFragment;
import iordache.cristian.bakeyourrecipe.RecipeList.RecipeClass;

public class MainActivity extends AppCompatActivity implements RecipeListFragment.OnRecipeClickListener {

    //To help us to determine whether the display is single or two pane
    public static boolean mTwoPane = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ProgressBar mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);

        //Determine if we create a single or two pane display
        if(findViewById(R.id.two_pane_layout) != null) {

            mTwoPane = true;

            if (savedInstanceState != null) {
                return;
            }

            //Define a new fragment for the recipeList
            RecipeListFragment recipeListFragment = new RecipeListFragment();

            //Define a new ArrayList for the Fetched recipe
            ArrayList<RecipeClass> recipeClasses = new ArrayList<>();

            //recipeListFragment.newInstance();

            // cleanup any existing fragments in case we are in detailed mode
            getSupportFragmentManager().executePendingTransactions();

            RecipeDetailsFragment recipeDetailsFragment = new RecipeDetailsFragment();


            //Use a FragmentManager and transaction to add the fragment to the screen
            FragmentManager fragmentManager = getSupportFragmentManager();

            fragmentManager.beginTransaction()
                    .add(R.id.recipe_details_two_pane_fragment, recipeDetailsFragment)
                    .commit();

        }else {
            mTwoPane = false;

        }
    }


    @Override
    public void onRecipeSelected(ArrayList<RecipeClass> recipeList, int position) {

        if(mTwoPane == true) {
            RecipeDetailsFragment recipeDetailsFragment = (RecipeDetailsFragment) getSupportFragmentManager().findFragmentById(R.id.recipe_details_fragment);

            if (recipeDetailsFragment != null) {
                recipeDetailsFragment.updateDetails(recipeList, position);
            } else {
                RecipeDetailsFragment recipeDetailsFragment1 = new RecipeDetailsFragment();
                Bundle args = new Bundle();
                args.putInt("position", position);
                args.putParcelableArrayList("recipeList", recipeList);
                recipeDetailsFragment1.setArguments(args);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

                transaction.replace(R.id.recipe_details_two_pane_fragment, recipeDetailsFragment1);
                transaction.addToBackStack(null);

                transaction.commit();
            }
        }else {
            // Create fragment and give it an argument specifying the article it should show
            RecipeDetailsFragment recipeDetailsFragment1 = new RecipeDetailsFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            args.putParcelableArrayList("recipeList", recipeList);
            recipeDetailsFragment1.setArguments(args);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.master_recipe_list_fragment, recipeDetailsFragment1);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
        }
    }
}
