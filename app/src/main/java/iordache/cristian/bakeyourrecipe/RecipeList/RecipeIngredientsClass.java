package iordache.cristian.bakeyourrecipe.RecipeList;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by cii51253 on 01/06/2017.
 */

public class RecipeIngredientsClass implements Parcelable {
    private int quantity;
    private String measure;
    private String ingredient;

    public RecipeIngredientsClass() {}

    public RecipeIngredientsClass(int quantity, String measure, String ingredient) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredient = ingredient;
    }

    protected RecipeIngredientsClass(Parcel in) {
        quantity = in.readInt();
        measure = in.readString();
        ingredient = in.readString();
    }

    public static final Creator<RecipeIngredientsClass> CREATOR = new Creator<RecipeIngredientsClass>() {
        @Override
        public RecipeIngredientsClass createFromParcel(Parcel in) {
            return new RecipeIngredientsClass(in);
        }

        @Override
        public RecipeIngredientsClass[] newArray(int size) {
            return new RecipeIngredientsClass[size];
        }
    };

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(quantity);
        parcel.writeString(measure);
        parcel.writeString(ingredient);
    }
}
