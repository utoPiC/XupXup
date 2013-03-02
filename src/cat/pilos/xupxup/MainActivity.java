
package cat.pilos.xupxup;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import cat.pilos.xupxup.adapters.RecipeListAdapter;
import cat.pilos.xupxup.views.recipe.RecipeViewActivity;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;


public class MainActivity extends SherlockActivity implements OnNavigationListener{

	
	/* Views */
	private ListView lv_recipes;
	private RecipeListAdapter recipeAdapter;
	
	
	
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	      
	       super.onCreate(savedInstanceState);
	      
	       setContentView(R.layout.activity_main);
	 
	       loadUiViews();
	       configViews();
	       bindAdapter();
	       
	  }
	
  
	  private void loadUiViews() {

		  lv_recipes=(ListView)findViewById(R.id.lv_recipes);
		  lv_recipes.setOnItemClickListener(lv_recipesItemClicked);
		       
	}
    
    private void configViews() {
        
    	configureActionBar();
    	
    }
    
    void bindAdapter() {
		
    	recipeAdapter= new RecipeListAdapter(this);
    	lv_recipes.setAdapter(recipeAdapter);

    }
	
    public OnItemClickListener lv_recipesItemClicked  = new OnItemClickListener() {
		
    	public void onItemClick(android.widget.AdapterView<?> parent, View v, int position, long id) {
    		
				Intent intent = new Intent(v.getContext(), RecipeViewActivity.class);

		        intent.putExtra(RecipeViewActivity.RECIPE_ID_EXTRA, recipeAdapter.getItem(position).recipeId);

		        startActivity(intent);
		 
		 }
			
    };

    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    private void configureActionBar() {
        Context context = getSupportActionBar().getThemedContext();
        ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(context, R.array.locations, (android.R.layout.simple_list_item_1));
        getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getSupportActionBar().setListNavigationCallbacks(list, this);
    }

    @Override
    public boolean onNavigationItemSelected(int itemPosition, long itemId) {
    	
   
    	
        return true;
    }

}
