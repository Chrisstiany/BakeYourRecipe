package iordache.cristian.bakeyourrecipe.RecipeList;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by cii51253 on 01/06/2017.
 */

public class RecipeClass implements Parcelable {
    private String nameOfTheRecipe;
    private ArrayList<RecipeIngredientsClass> recipeIngredients;
    private ArrayList<RecipeStepsClass> recipeSteps;
    private int numberOfSteps;
    private int recipeServings;
    private String recipeImage;



    public RecipeClass(String nameOfTheRecipe, ArrayList<RecipeIngredientsClass> recipeIngredients, ArrayList<RecipeStepsClass> recipeSteps, int recipeServings, String recipeImage, int numberOfSteps) {
        this.nameOfTheRecipe = nameOfTheRecipe;
        this.recipeIngredients = recipeIngredients;
        this.recipeSteps = recipeSteps;
        this.recipeServings = recipeServings;
        this.recipeImage = recipeImage;
        this.numberOfSteps = recipeSteps.size();
    }

    public RecipeClass(Parcel in){

        nameOfTheRecipe = in.readString();
        numberOfSteps = in.readInt();
        recipeServings = in.readInt();
        recipeImage = in.readString();
    }

    public static final Creator<RecipeClass> CREATOR = new Creator<RecipeClass>() {
        @Override
        public RecipeClass createFromParcel(Parcel in) {
            return new RecipeClass(in);
        }

        @Override
        public RecipeClass[] newArray(int size) {
            return new RecipeClass[size];
        }
    };

    public String getNameOfTheRecipe() {
        return nameOfTheRecipe;
    }

    public void setNameOfTheRecipe(String nameOfTheRecipe) {
        this.nameOfTheRecipe = nameOfTheRecipe;
    }

    public ArrayList<RecipeIngredientsClass> getRecipeIngredients() {
        return recipeIngredients;
    }

    public void setRecipeIngredients(ArrayList<RecipeIngredientsClass> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

    public ArrayList<RecipeStepsClass> getRecipeSteps() {
        return recipeSteps;
    }

    public void setRecipeSteps(ArrayList<RecipeStepsClass> recipeSteps) {
        this.recipeSteps = recipeSteps;
    }

    public int getRecipeServings() {
        return recipeServings;
    }

    public void setRecipeServings(int recipeServings) {
        this.recipeServings = recipeServings;
    }

    public String getRecipeImage() {
        return recipeImage;
    }

    public void setRecipeImage(String recipeImage) {
        this.recipeImage = recipeImage;
    }
    public int getNumberOfSteps() {
        return numberOfSteps;
    }

    public void setNumberOfSteps(int numberOfSteps) {
        this.numberOfSteps = numberOfSteps;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nameOfTheRecipe);
        parcel.writeInt(numberOfSteps);
        parcel.writeInt(recipeServings);
        parcel.writeString(recipeImage);
    }
}
