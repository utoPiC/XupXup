package cat.pilos.xupxup.adapters;

import java.util.List;

import cat.pilos.xupxup.finders.RecipeStepFinder;
import cat.pilos.xupxup.model.Step;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class StepListAdapter extends BaseAdapter {

    private List<Step> steps;
    
    private RecipeStepFinder stepFinder;
    private Context context;
    
    public StepListAdapter(Context context, long recipeId){
    	
    	this.context=context;
    	
    	stepFinder=new RecipeStepFinder(context);

    	steps=stepFinder.getAllRecipeSteps(recipeId);
    	
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        StepItemView personItemView;
        
        if (convertView == null) {
        	
            personItemView = new StepItemView(context);
            
            
        } else {
            personItemView = (StepItemView) convertView;
        }
        
        personItemView.bind(getItem(position));

        return personItemView;
    }
    
    @Override
    public int getCount() {
        return steps.size();
    }

    @Override
    public Step getItem(int position) {
        return steps.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}