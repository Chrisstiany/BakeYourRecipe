package iordache.cristian.bakeyourrecipe.RecipeDetails;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import iordache.cristian.bakeyourrecipe.R;
import iordache.cristian.bakeyourrecipe.RecipeList.RecipeIngredientsClass;
import iordache.cristian.bakeyourrecipe.RecipeList.RecipeStepsClass;

/**
 * Created by cii51253 on 05/06/2017.
 */

public class RecipeDetailsActivity extends AppCompatActivity {

    //Initialize the views
    //@BindView(R.id.text_view_title_for_ingredients_card) TextView ingredientCardTitle;
    //@BindView(R.id.view_ingredients_title) TextView ingredientsTitle;
    //@BindView(R.id.text_view_title_for_steps_card) TextView stepsCardTitle;
    private TextView ingredientMaster;
    private TextView stepsMaster;
    private TextView servingsMaster;

    private RecyclerView viewIngredientsList;
    private RecipeIngredientsRecyclerViewAdapter recipeIngredientsRecyclerViewAdapter;

    private RecyclerView viewStepsList;
    private RecipeStepsRecyclerViewAdapter recipeStepsRecyclerViewAdapter;

    private CardView cardViewIngredients;
    private CardView cardViewSteps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_details);

        //Retrieve data from the RecipeListFragment
        Intent intent = getIntent();

        //Retrieve the name of the Recipe
        String nameOfTheRecipe = intent.getStringExtra("RecipeName");

        //Set the name of the Window Activity to the Recipe Name
        setTitle(nameOfTheRecipe);

        //Retrieve the Ingredients list
        ArrayList<RecipeIngredientsClass> recipeIngredients = intent.getParcelableArrayListExtra("RecipeIngredients");

        //Retrieve the steps for the Recipe
        ArrayList<RecipeStepsClass> recipeSteps = intent.getParcelableArrayListExtra("RecipeSteps");

        //Retrieve the numbers of Steps of the Recipe
        Bundle bundle = getIntent().getExtras();
        int noOfSteps = bundle.getInt("RecipeNoOfSteps");

        //Retrieve the number of Servings
        int noOfServings = bundle.getInt("RecipeServings");

        //Initialize and set the title for the Recipe Ingredients Card
        TextView ingredientCardTitle = (TextView) findViewById(R.id.text_view_title_for_ingredients_card);
        ingredientCardTitle.setText("Ingredients");

        //Initialize and set the title for the Recipe Steps Card
        TextView stepsCardTitle = (TextView) findViewById(R.id.text_view_title_for_steps_card);
        stepsCardTitle.setText("Steps for Cooking");

        //Initialize and set the title for the Recipe Servings Card
        TextView servingsCardtitle = (TextView) findViewById(R.id.text_view_title_for_servings_card);
        servingsCardtitle.setText("Servings");

        //Initialize the Master TextView for the Ingredients CardView
        ingredientMaster = (TextView) findViewById(R.id.ingredient_master_text_view);

        //Set the text for the Master TextView for the Ingredients Cardview
        ingredientMaster.setText("Number of ingredients: "
                + recipeIngredients.size()
                + "\n"
                + "Measure types: "
                + "CUP TBLSP TSP TBLSP K G CUP"
                + "\n"
                + "(click to expand the ingredients list)");


        //Set up the RecycleView for the List of Ingredients
        viewIngredientsList = (RecyclerView) findViewById(R.id.ingredients_recycle_view);
        viewIngredientsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recipeIngredientsRecyclerViewAdapter = new RecipeIngredientsRecyclerViewAdapter(recipeIngredients, this);
        viewIngredientsList.setAdapter(recipeIngredientsRecyclerViewAdapter);

        //OnclickListener for the Ingredients CardView for collapse/expand function
        cardViewIngredients = (CardView) findViewById(R.id.card_view_ingredients);
        cardViewIngredients.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(cardViewIngredients);
                if(viewIngredientsList.getVisibility() == View.GONE) {

                    ingredientMaster.setVisibility(View.GONE);
                    viewIngredientsList.setVisibility(View.VISIBLE);
                }else {
                    viewIngredientsList.setVisibility(View.GONE);
                    ingredientMaster.setVisibility(View.VISIBLE);
                }
            }
        });

        //Initialize the Master TextView for the Steps Cardview
        stepsMaster = (TextView) findViewById(R.id.steps_master_text_view);

        //Set the text for the Master TextView for the Steps Cardview
        stepsMaster.setText("Number of steps: " + recipeSteps.size() + "\n" + "(click to expand the steps list)");

        //Set up the RecycleView for the List of the Steps
        viewStepsList = (RecyclerView) findViewById(R.id.steps_recycler_view);
        viewStepsList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recipeStepsRecyclerViewAdapter = new RecipeStepsRecyclerViewAdapter(recipeSteps, this);
        viewStepsList.setAdapter(recipeStepsRecyclerViewAdapter);

        //OnClickListener for the Steps CardView for collapse/expand function
        cardViewSteps = (CardView) findViewById(R.id.card_view_steps);
        cardViewSteps.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(cardViewSteps);
                if(viewStepsList.getVisibility() == View.GONE) {

                    stepsMaster.setVisibility(View.GONE);
                    viewStepsList.setVisibility(View.VISIBLE);
                }else {
                    viewStepsList.setVisibility(View.GONE);
                    stepsMaster.setVisibility(View.VISIBLE);
                }
            }
        });

        //Initialize the Master TextView for the Steps Cardview
        servingsMaster = (TextView) findViewById(R.id.servings_master_text_view);

        //Set the text for the Master TextView for the Steps Cardview
        servingsMaster.setText("Number of servings: " + noOfServings);
    }


}