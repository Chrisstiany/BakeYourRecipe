package iordache.cristian.bakeyourrecipe.RecipeDetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import iordache.cristian.bakeyourrecipe.R;
import iordache.cristian.bakeyourrecipe.RecipeList.RecipeIngredientsClass;

/**
 * Created by cii51253 on 08/06/2017.
 */

public class RecipeIngredientsRecyclerViewAdapter extends RecyclerView.Adapter<RecipeIngredientsRecyclerViewAdapter.MyViewHolder>{

    ArrayList<RecipeIngredientsClass> recipeIngredientsList = new ArrayList<>();

    private Context mContext;

    public RecipeIngredientsRecyclerViewAdapter(ArrayList<RecipeIngredientsClass> recipeIngredientsList, Context mContext) {
        this.recipeIngredientsList = recipeIngredientsList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_ingredients_recycle_view_item_customization, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.ingredientName.setText("Ingredient: " + recipeIngredientsList.get(position).getIngredient());
        holder.ingredientMeasure.setText("Measure: " + recipeIngredientsList.get(position).getMeasure());
        holder.ingredientQuantity.setText("Quantity: " + recipeIngredientsList.get(position).getQuantity());

    }

    @Override
    public int getItemCount() {
        return recipeIngredientsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView ingredientName;
        TextView ingredientMeasure;
        TextView ingredientQuantity;

        public MyViewHolder(View itemView) {
            super(itemView);
            ingredientName = (TextView)itemView.findViewById(R.id.view_ingredients_title);
            ingredientMeasure = (TextView)itemView.findViewById(R.id.view_ingredients_measure);
            ingredientQuantity = (TextView)itemView.findViewById(R.id.view_ingredients_quantity);

        }
    }
}
