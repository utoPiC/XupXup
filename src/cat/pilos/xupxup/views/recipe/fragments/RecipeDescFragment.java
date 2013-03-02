package cat.pilos.xupxup.views.recipe.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cat.pilos.xupxup.R;

import com.actionbarsherlock.app.SherlockFragment;

public class RecipeDescFragment extends SherlockFragment{
	
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.desc_fragment, container, false);
		
	}


}
