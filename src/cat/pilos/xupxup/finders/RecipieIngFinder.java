package cat.pilos.xupxup.finders;

import java.util.ArrayList;
import java.util.List;

import cat.pilos.xupxup.databases.RecieptDBAdapter;
import cat.pilos.xupxup.model.RecIngredient;
import cat.pilos.xupxup.model.Step;
import android.content.Context;
import android.database.Cursor;

public class RecipieIngFinder {
	
	private Context baseContext;
	private RecieptDBAdapter recipeDB;
	
	
	public RecipieIngFinder(Context context){
		
		this.baseContext=context;
		
	}
	
	
	public List<RecIngredient> getAllRecipeIng(long recipeId){
	
		List<RecIngredient> recIngList= new ArrayList<RecIngredient>();
		
		recipeDB=new RecieptDBAdapter(baseContext);
		recipeDB.open();
		
		Cursor recipeIngCursorList=recipeDB.getRecipeIngredients(recipeId);
		recipeIngCursorList.moveToFirst();
		
		if(recipeIngCursorList!=null){
			
			while(!recipeIngCursorList.isAfterLast()){
				
				//KEY_ROWID,KEY_ING_NAME, KEY_ING_QUANT, KEY_ING_UNIT, KEY_ING_FK_RECIPE,KEY_ING_FK_ING
				
				RecIngredient recIng=new RecIngredient(
						recipeIngCursorList.getInt(2), 
						recipeIngCursorList.getString(1), 
						recipeIngCursorList.getString(3)
						);
	
				recIngList.add(recIng);
				
				recipeIngCursorList.moveToNext();
				
			}
			
			recipeIngCursorList.close();
						
		}
		
		recipeDB.close();
		
		return recIngList;
		
	}
	
	
	

}
