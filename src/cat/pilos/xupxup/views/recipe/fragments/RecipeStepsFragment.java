package cat.pilos.xupxup.views.recipe.fragments;

import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import cat.pilos.xupxup.R;
import cat.pilos.xupxup.adapters.IngredientListAdapter;
import cat.pilos.xupxup.adapters.StepListAdapter;

import com.actionbarsherlock.app.SherlockFragment;

public class RecipeStepsFragment extends SherlockFragment{
	
    private ListView step_list;
    private StepListAdapter stepsAdapter;

	
	public static RecipeStepsFragment newInstance(long recipeId) {
		  
		RecipeStepsFragment pageFragment = new RecipeStepsFragment();
        Bundle bundle = new Bundle();
        bundle.putLong("recipeId", recipeId);
        pageFragment.setArguments(bundle);
        return pageFragment;
    }
      
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.step_fragment, container, false); 
		
        step_list=(ListView)view.findViewById(R.id.lv_steps_list);
        
        stepsAdapter= new StepListAdapter(getActivity().getBaseContext(), getArguments().getLong("recipeId"));
		step_list.setAdapter(stepsAdapter);
    	
		return view;
		
	}

	  public int getShownIndex() {
	        return getArguments().getInt("index", 0);
	    
	  }

}
