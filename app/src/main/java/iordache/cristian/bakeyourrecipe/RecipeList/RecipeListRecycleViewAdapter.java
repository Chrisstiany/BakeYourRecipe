package iordache.cristian.bakeyourrecipe.RecipeList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import iordache.cristian.bakeyourrecipe.R;

/**
 * Created by cii51253 on 01/06/2017.
 */

public class RecipeListRecycleViewAdapter extends RecyclerView.Adapter<RecipeListRecycleViewAdapter.MyViewHolder> {

    ArrayList<RecipeClass> recipeList = new ArrayList<>();

    private Context mContext;



    public RecipeListRecycleViewAdapter(Context context, ArrayList<RecipeClass> recipeList) {
        this.recipeList = recipeList;
        mContext = context;
       // this.mOnClickListener = mOnClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list_item_customization, parent, false);
       // view.setOnClickListener(mOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder,final int position) {
        holder.recipeName.setText(recipeList.get(position).getNameOfTheRecipe());
        holder.recipeNoOfSteps.setText("Number of steps: " + recipeList.get(position).getNumberOfSteps());
        holder.recipeNoOfIngredients.setText("Number of ingredients: " + recipeList.get(position).getRecipeIngredients().size());
        holder.recipeImage.setImageResource(R.drawable.avengers);


    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView recipeName;
        TextView recipeNoOfSteps;
        ImageView recipeImage;
        TextView recipeNoOfIngredients;

        public MyViewHolder(View itemView) {
            super(itemView);
            recipeName = (TextView) itemView.findViewById(R.id.view_recipe_name);
            recipeNoOfSteps = (TextView) itemView.findViewById(R.id.view_recipe_no_of_steps);
            recipeNoOfIngredients = (TextView) itemView.findViewById(R.id.view_recipe_no_of_ingredients);
            recipeImage = (ImageView) itemView.findViewById(R.id.view_recipe_image);
        }


    }



}
