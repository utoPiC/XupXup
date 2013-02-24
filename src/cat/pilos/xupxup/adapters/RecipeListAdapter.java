package cat.pilos.xupxup.adapters;

import java.util.List;

import cat.pilos.xupxup.finders.RecipeFinder;
import cat.pilos.xupxup.model.Recipe;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class RecipeListAdapter extends BaseAdapter {

    private List<Recipe> recipes;
    
    private RecipeFinder recipeFinder;
    private Context context;

    
    
	public RecipeListAdapter(Context context) {
    	
		this.context=context;
		
    	recipeFinder=new RecipeFinder(context);
    	recipes=recipeFinder.getAllRecipes();

    	
    }

    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        RecipeItemView recipeItemView;
        
        if (convertView == null) {
            recipeItemView = new RecipeItemView(context);
            
        } 
        else {
        	
            recipeItemView = (RecipeItemView) convertView;
        }
        
        recipeItemView.bind(getItem(position));

        return recipeItemView;
    }
    
    @Override
    public int getCount() {
        return recipes.size();
    }

    @Override
    public Recipe getItem(int position) {
        return recipes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}