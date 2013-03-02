package cat.pilos.xupxup.views.recipe.adapters;

import cat.pilos.xupxup.views.recipe.fragments.RecipeDescFragment;
import cat.pilos.xupxup.views.recipe.fragments.RecipeIngFragment;
import cat.pilos.xupxup.views.recipe.fragments.RecipeStepsFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class RecipeFragmentAdapter extends FragmentPagerAdapter {
	
	private String[] TITLES={"DESCRIPCIÓ","INGREDIENTS","PASSOS"};
	private Fragment[] fragments = new Fragment[3];
	
	private long recipeId;
	
    public RecipeFragmentAdapter(FragmentManager fm, long recipeId) {
    	
        super(fm);
        this.recipeId=recipeId;
        
        fragments[0]=new RecipeDescFragment();
        fragments[1]=RecipeIngFragment.newInstance(recipeId);
        fragments[2]=RecipeStepsFragment.newInstance(recipeId);
    }

    @Override
    public Fragment getItem(int position) {
    	
    	if(fragments[position]==null){
    	

    		switch (position) {
    		case 0:

    			fragments[position]=new RecipeDescFragment();

    		case 1:

    			fragments[position]=RecipeIngFragment.newInstance(recipeId);

    		case 2:

    			fragments[position]=RecipeStepsFragment.newInstance(recipeId);

    		default:

    		}
    	}
    	
    	return fragments[position];
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
    	
      return TITLES.length;
      
    }
}