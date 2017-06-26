package iordache.cristian.bakeyourrecipe.RecipeDetails;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import iordache.cristian.bakeyourrecipe.R;
import iordache.cristian.bakeyourrecipe.RecipeList.RecipeStepsClass;

/**
 * Created by cii51253 on 09/06/2017.
 */

public class RecipeStepsRecyclerViewAdapter extends RecyclerView.Adapter<RecipeStepsRecyclerViewAdapter.MyViewHolder> {

    ArrayList<RecipeStepsClass> recipeStepsList = new ArrayList<>();

    private Context mContext;

    public RecipeStepsRecyclerViewAdapter(ArrayList<RecipeStepsClass> recipeStepsList, Context mContext) {
        this.recipeStepsList = recipeStepsList;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_steps_recycle_view_item_customization, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.stepsShortDescription.setText(recipeStepsList.get(position).getsDescription());
        holder.stepsDescription.setText(recipeStepsList.get(position).getDescription());
        holder.videoUrl.setText("Play Video");

    }

    @Override
    public int getItemCount() {
        return recipeStepsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView stepsShortDescription;
        TextView stepsDescription;
        TextView videoUrl;

        public MyViewHolder(View itemView) {
            super(itemView);

            stepsShortDescription = (TextView) itemView.findViewById(R.id.view_steps_short_description);
            stepsDescription = (TextView) itemView.findViewById(R.id.view_steps_description);
            videoUrl = (TextView) itemView.findViewById(R.id.view_steps_videoUrl);

        }
    }
}
