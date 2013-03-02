package cat.pilos.xupxup.views.recipe;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import cat.pilos.xupxup.R;
import cat.pilos.xupxup.adapters.StepListAdapter;
import cat.pilos.xupxup.adapters.IngredientListAdapter;
import cat.pilos.xupxup.finders.RecipeFinder;
import cat.pilos.xupxup.model.Recipe;
import cat.pilos.xupxup.views.recipe.adapters.RecipeFragmentAdapter;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.app.ActionBar.OnNavigationListener;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.viewpagerindicator.TabPageIndicator;
import com.viewpagerindicator.TitlePageIndicator;


public class RecipeViewActivity extends SherlockFragmentActivity implements OnNavigationListener{

	public static final String RECIPE_ID_EXTRA = "recipeId";
	private Recipe recipe;
	
	//@Extra(RECIPE_ID_EXTRA)
	long recipeId;
	
    private TextView rec_servings;
    private TextView rec_time;
    
    private ListView ingr_list;
    
    
    //@StringRes(R.string.reciept_num_servings)
    private String rec_num_serv_string;
    //@StringRes(R.string.reciept_time_prepare)
    private String rec_prepare_time;

    
    private IngredientListAdapter ingrAdapter;
    
  
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.reciept_tab);
		

		recipeId=getIntent().getExtras().getLong(RECIPE_ID_EXTRA);
		
		ViewPager pager = (ViewPager)findViewById(R.id.pager);
		pager.setAdapter(new RecipeFragmentAdapter(getSupportFragmentManager(),recipeId));
		
		TabPageIndicator titleIndicator = (TabPageIndicator)findViewById(R.id.indicator);
		titleIndicator.setViewPager(pager);
		
		loadUiViews();
		//bindAdapter();
		afterViews();
		
		
	}
		
	private void loadUiViews() {

		  rec_servings= (TextView) findViewById(R.id.tv_reciept_num_servings);
		  rec_time=(TextView) findViewById(R.id.tv_reciept_time);

		 // ingr_list=(ListView) findViewById(R.id.lv_ingr_list);
		  
		  rec_num_serv_string=getString(R.string.reciept_num_servings);
		  rec_prepare_time= getString(R.string.reciept_time_prepare);
	
	}

	void afterViews() {
	
		configureActionBar();
		setInitialUiData();
		
	}
	
	//@AfterViews
    void bindAdapter() {
		
    	ingrAdapter= new IngredientListAdapter(this, recipeId);
    	ingr_list.setAdapter(ingrAdapter);
    	
    	//stepsAdapter= new StepListAdapter(this, recipeId);
		//step_list.setAdapter(stepsAdapter);

    }

	private void setInitialUiData() {
 
		RecipeFinder recFinder= new RecipeFinder(this);
		
		recipe=recFinder.getRecipeInfo(recipeId);
		
		updateUi();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getSupportMenuInflater().inflate(R.menu.activity_reciept, menu);
		return true;
	}

	private void configureActionBar() {
		Context context = getSupportActionBar().getThemedContext();
		ArrayAdapter<CharSequence> list = ArrayAdapter.createFromResource(context, R.array.locations, (android.R.layout.simple_list_item_1));
		getSupportActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);

	}
	
   // @UiThread
    void updateUi() {
		
		getSupportActionBar().setTitle(recipe.title);

        String text=String.format(rec_num_serv_string, recipe.servings);
		rec_servings.setText(text);		
		
	   	text=String.format(rec_prepare_time, recipe.prepTime);
		rec_time.setText(text);		
		
		
    }

	@Override
	public boolean onNavigationItemSelected(int itemPosition, long itemId) {
		// TODO Auto-generated method stub
		return false;
	}




}
