package cat.pilos.xupxup.adapters;

import java.util.List;

import cat.pilos.xupxup.finders.RecipieIngFinder;
import cat.pilos.xupxup.model.RecIngredient;
import cat.pilos.xupxup.utils.converters.IngUnitsConverter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;



public class IngredientListAdapter extends BaseAdapter {

    private List<RecIngredient> ingredients;
    
    private RecipieIngFinder recIngFinder;
    private Context context;
    private IngUnitsConverter converter;

    public IngredientListAdapter(Context context, long recipeId){
		
    	this.context=context;
    	
    	recIngFinder=new RecipieIngFinder(context);
    	
    	ingredients=recIngFinder.getAllRecipeIng(recipeId);
    	converter=new IngUnitsConverter(context);
    	
    	
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        
        IngredientItemView personItemView;
        
        if (convertView == null) {
        	
            personItemView = new IngredientItemView(context,converter);
            
        } 
        else {
            
        	personItemView = (IngredientItemView) convertView;
        	
        }
        
        personItemView.bind(getItem(position));

        return personItemView;
    }
    
    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public RecIngredient getItem(int position) {
        return ingredients.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}