package iordache.cristian.bakeyourrecipe.RecipeDetails;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import iordache.cristian.bakeyourrecipe.R;
import iordache.cristian.bakeyourrecipe.RecipeList.RecipeClass;
import iordache.cristian.bakeyourrecipe.RecipeList.RecipeIngredientsClass;
import iordache.cristian.bakeyourrecipe.RecipeList.RecipeStepsClass;

/**
 * Created by cii51253 on 09/06/2017.
 */

public class RecipeDetailsFragment extends Fragment {

    private TextView ingredientMaster;
    private TextView stepsMaster;
    private TextView servingsMaster;

    private RecyclerView viewIngredientsList;
    private RecipeIngredientsRecyclerViewAdapter recipeIngredientsRecyclerViewAdapter;

    private RecyclerView viewStepsList;
    private RecipeStepsRecyclerViewAdapter recipeStepsRecyclerViewAdapter;

    private CardView cardViewIngredients;
    private CardView cardViewSteps;

    private String nameOfTheRecipe;

    private ArrayList<RecipeIngredientsClass> recipeIngredients;

    private ArrayList<RecipeStepsClass> recipeSteps;

    private int noOfSteps;

    private int noOfServings;

    public RecipeDetailsFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.activity_recipe_details, container, false);

        Bundle bundle = this.getArguments();
        Bundle bundle1 = getActivity().getIntent().getBundleExtra("extra");



        //Retrieve data from the RecipeListFragment
        if (bundle != null) {
            Intent intent = getActivity().getIntent();

            int position = bundle.getInt("position");

            ArrayList<RecipeClass> recipeList = bundle.getParcelableArrayList("recipeList");
            //Retrieve the name of the Recipe
            nameOfTheRecipe = recipeList.get(position).getNameOfTheRecipe();


            //Retrieve the Ingredients list
            recipeIngredients = recipeList.get(position).getRecipeIngredients();

            //Retrieve the steps for the Recipe
            recipeSteps = recipeList.get(position).getRecipeSteps();

            //Retrieve the numbers of Steps of the Recipe
            noOfSteps = recipeList.get(position).getNumberOfSteps();

            //Retrieve the number of Servings
            noOfServings = recipeList.get(position).getRecipeServings();


            //Initialize and set the title for the Recipe Ingredients Card
            TextView ingredientCardTitle = (TextView) rootView.findViewById(R.id.text_view_title_for_ingredients_card);
            ingredientCardTitle.setText("Ingredients");

            //Initialize and set the title for the Recipe Steps Card
            TextView stepsCardTitle = (TextView) rootView.findViewById(R.id.text_view_title_for_steps_card);
            stepsCardTitle.setText("Steps for Cooking");

            //Initialize and set the title for the Recipe Servings Card
            TextView servingsCardtitle = (TextView) rootView.findViewById(R.id.text_view_title_for_servings_card);
            servingsCardtitle.setText("Servings");

            //Initialize the Master TextView for the Ingredients CardView
            ingredientMaster = (TextView) rootView.findViewById(R.id.ingredient_master_text_view);

            //Set the text for the Master TextView for the Ingredients Cardview
            ingredientMaster.setText("Number of ingredients: "
                    + recipeIngredients.size()
                    + "\n"
                    + "Measure types: "
                    + "CUP TBLSP TSP TBLSP K G CUP"
                    + "\n"
                    + "(click to expand the ingredients list)");

            //Set up the RecycleView for the List of Ingredients
            viewIngredientsList = (RecyclerView) rootView.findViewById(R.id.ingredients_recycle_view);
            viewIngredientsList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            recipeIngredientsRecyclerViewAdapter = new RecipeIngredientsRecyclerViewAdapter(recipeIngredients, getActivity());
            viewIngredientsList.setAdapter(recipeIngredientsRecyclerViewAdapter);

            //OnclickListener for the Ingredients CardView for collapse/expand function
            cardViewIngredients = (CardView) rootView.findViewById(R.id.card_view_ingredients);
            cardViewIngredients.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View view) {
                    TransitionManager.beginDelayedTransition(cardViewIngredients);
                    if (viewIngredientsList.getVisibility() == View.GONE) {

                        ingredientMaster.setVisibility(View.GONE);
                        viewIngredientsList.setVisibility(View.VISIBLE);
                    } else {
                        viewIngredientsList.setVisibility(View.GONE);
                        ingredientMaster.setVisibility(View.VISIBLE);
                    }
                }
            });

            //Initialize the Master TextView for the Steps Cardview
            stepsMaster = (TextView) rootView.findViewById(R.id.steps_master_text_view);

            //Set the text for the Master TextView for the Steps Cardview
            stepsMaster.setText("Number of steps: " + recipeSteps.size() + "\n" + "(click to expand the steps list)");

            //Set up the RecycleView for the List of the Steps
            viewStepsList = (RecyclerView) rootView.findViewById(R.id.steps_recycler_view);
            viewStepsList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            recipeStepsRecyclerViewAdapter = new RecipeStepsRecyclerViewAdapter(recipeSteps, getActivity());
            viewStepsList.setAdapter(recipeStepsRecyclerViewAdapter);

            //OnClickListener for the Steps CardView for collapse/expand function
            cardViewSteps = (CardView) rootView.findViewById(R.id.card_view_steps);
            cardViewSteps.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View view) {
                    TransitionManager.beginDelayedTransition(cardViewSteps);
                    if (viewStepsList.getVisibility() == View.GONE) {

                        stepsMaster.setVisibility(View.GONE);
                        viewStepsList.setVisibility(View.VISIBLE);
                    } else {
                        viewStepsList.setVisibility(View.GONE);
                        stepsMaster.setVisibility(View.VISIBLE);
                    }
                }
            });

            //Initialize the Master TextView for the Steps Cardview
            servingsMaster = (TextView) rootView.findViewById(R.id.servings_master_text_view);

            //Set the text for the Master TextView for the Steps Cardview
            servingsMaster.setText("Number of servings: " + noOfServings);
        }
        return rootView;
    }

    public void updateDetails(ArrayList<RecipeClass> recipeList, int position) {
        Bundle bundle = getActivity().getIntent().getExtras();
        if (bundle == null) {
            nameOfTheRecipe = recipeList.get(position).getNameOfTheRecipe();
            //Retrieve the Ingredients list
            recipeIngredients = recipeList.get(position).getRecipeIngredients();

            //Retrieve the steps for the Recipe
            recipeSteps = recipeList.get(position).getRecipeSteps();

            //Retrieve the numbers of Steps of the Recipe
            noOfSteps = recipeList.get(position).getNumberOfSteps();

            //Retrieve the number of Servings
            noOfServings = recipeSteps.size();

            View view = getView();
            TextView ingredientCardTitle = (TextView) view.findViewById(R.id.text_view_title_for_ingredients_card);

            //Initialize and set the title for the Recipe Steps Card
            TextView stepsCardTitle = (TextView) view.findViewById(R.id.text_view_title_for_steps_card);
            stepsCardTitle.setText("Steps for Cooking");

            //Initialize and set the title for the Recipe Servings Card
            TextView servingsCardtitle = (TextView) view.findViewById(R.id.text_view_title_for_servings_card);
            servingsCardtitle.setText("Servings");

            //Initialize the Master TextView for the Ingredients CardView
            ingredientMaster = (TextView) view.findViewById(R.id.ingredient_master_text_view);

            //Set the text for the Master TextView for the Ingredients Cardview
            ingredientMaster.setText("Number of ingredients: "
                    + recipeIngredients.size()
                    + "\n"
                    + "Measure types: "
                    + "CUP TBLSP TSP TBLSP K G CUP"
                    + "\n"
                    + "(click to expand the ingredients list)");

            //Set up the RecycleView for the List of Ingredients
            viewIngredientsList = (RecyclerView) view.findViewById(R.id.ingredients_recycle_view);
            viewIngredientsList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            recipeIngredientsRecyclerViewAdapter = new RecipeIngredientsRecyclerViewAdapter(recipeIngredients, getActivity());
            viewIngredientsList.setAdapter(recipeIngredientsRecyclerViewAdapter);

            //OnclickListener for the Ingredients CardView for collapse/expand function
            cardViewIngredients = (CardView) view.findViewById(R.id.card_view_ingredients);
            cardViewIngredients.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View view) {
                    TransitionManager.beginDelayedTransition(cardViewIngredients);
                    if (viewIngredientsList.getVisibility() == View.GONE) {

                        ingredientMaster.setVisibility(View.GONE);
                        viewIngredientsList.setVisibility(View.VISIBLE);
                    } else {
                        viewIngredientsList.setVisibility(View.GONE);
                        ingredientMaster.setVisibility(View.VISIBLE);
                    }
                }
            });

            //Initialize the Master TextView for the Steps Cardview
            stepsMaster = (TextView) view.findViewById(R.id.steps_master_text_view);

            //Set the text for the Master TextView for the Steps Cardview
            stepsMaster.setText("Number of steps: " + recipeSteps.size() + "\n" + "(click to expand the steps list)");

            //Set up the RecycleView for the List of the Steps
            viewStepsList = (RecyclerView) view.findViewById(R.id.steps_recycler_view);
            viewStepsList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
            recipeStepsRecyclerViewAdapter = new RecipeStepsRecyclerViewAdapter(recipeSteps, getActivity());
            viewStepsList.setAdapter(recipeStepsRecyclerViewAdapter);

            //OnClickListener for the Steps CardView for collapse/expand function
            cardViewSteps = (CardView) view.findViewById(R.id.card_view_steps);
            cardViewSteps.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View view) {
                    TransitionManager.beginDelayedTransition(cardViewSteps);
                    if (viewStepsList.getVisibility() == View.GONE) {

                        stepsMaster.setVisibility(View.GONE);
                        viewStepsList.setVisibility(View.VISIBLE);
                    } else {
                        viewStepsList.setVisibility(View.GONE);
                        stepsMaster.setVisibility(View.VISIBLE);
                    }
                }
            });

            //Initialize the Master TextView for the Steps Cardview
            servingsMaster = (TextView) view.findViewById(R.id.servings_master_text_view);

            //Set the text for the Master TextView for the Steps Cardview
            servingsMaster.setText("Number of servings: " + noOfServings);
        }
    }
}