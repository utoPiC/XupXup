package cat.pilos.xupxup.views.recipe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import cat.pilos.xupxup.R;
import cat.pilos.xupxup.adapters.IngredientListAdapter;
import cat.pilos.xupxup.adapters.StepListAdapter;

import com.actionbarsherlock.app.SherlockFragment;

public class RecipeIngFragment extends SherlockFragment{
	
    private ListView ing_list;
    private IngredientListAdapter ingAdapter;

	
	public static RecipeIngFragment newInstance(long recipeId) {
		  
		RecipeIngFragment pageFragment = new RecipeIngFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("recipeId", recipeId);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }
      
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.ing_fragment, container, false); 
		
        ing_list=(ListView)view.findViewById(R.id.lv_ing_list);
        
        ingAdapter= new IngredientListAdapter(getActivity().getBaseContext(), getArguments().getLong("recipeId"));
		ing_list.setAdapter(ingAdapter);
    	
		return view;
		
	}

	  public int getShownIndex() {
	        return getArguments().getInt("index", 0);
	    
	  }

}
