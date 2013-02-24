package cat.pilos.xupxup.finders;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import cat.pilos.xupxup.databases.RecieptDBAdapter;
import cat.pilos.xupxup.model.Recipe;
import cat.pilos.xupxup.model.Step;

public class RecipeStepFinder {
	
	private RecieptDBAdapter recipeDB;
	private Context baseContext;
	
	
	public RecipeStepFinder(Context baseContext){
		
		this.baseContext=baseContext;
		
	}

	
	
	public List<Step> getAllRecipeSteps(long recipeId){
	
		List<Step> stepList= new ArrayList<Step>();
		
		recipeDB=new RecieptDBAdapter(baseContext);
		recipeDB.open();
		
		Cursor recipeCursorList=recipeDB.getRecipeSteps(recipeId);
		recipeCursorList.moveToFirst();
		
		if(recipeCursorList!=null){
			
			while(!recipeCursorList.isAfterLast()){
				
				//ho haurem de fer més net amb la columna
				Step tmpStep= new Step(
						recipeCursorList.getString(2),
						recipeCursorList.getString(3),
						recipeCursorList.getInt(4)
						);
				
				stepList.add(tmpStep);
				
				recipeCursorList.moveToNext();
				
			}
			
			recipeCursorList.close();
						
		}
		
		recipeDB.close();
		
		return stepList;
		
	}
	

	
	

}
