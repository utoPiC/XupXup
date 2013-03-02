package cat.pilos.xupxup.finders;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;

import cat.pilos.xupxup.databases.RecieptDBAdapter;
import cat.pilos.xupxup.model.RecIngredient;
import cat.pilos.xupxup.model.Recipe;
import cat.pilos.xupxup.utils.IngUnits;

public class RecipeFinder {
	
	private RecieptDBAdapter recipeDB;
	private Context baseContext;
	
	
	public RecipeFinder(Context baseContext){
		
		this.baseContext=baseContext;
		//insertSampleRecipe();
		//insertSampleRecipeSteps();
		//insertSampleRecipeIng();
				
	}
	
	
	public List<Recipe> getAllRecipes(){
	
		List<Recipe> recipeList= new ArrayList<Recipe>();
		
		recipeDB=new RecieptDBAdapter(baseContext);
		recipeDB.open();
		
		Cursor recipeCursorList=recipeDB.getAllReciepes();
		recipeCursorList.moveToFirst();
		
		if(recipeCursorList!=null){
			
			while(!recipeCursorList.isAfterLast()){
				
				//ho haurem de fer més net amb la columna
				Recipe tmpReciept= new Recipe(
						recipeCursorList.getLong(0),
						recipeCursorList.getString(1),
						recipeCursorList.getString(2),
						recipeCursorList.getInt(4),
						recipeCursorList.getInt(5)
						);
				
				recipeList.add(tmpReciept);
				
				recipeCursorList.moveToNext();
				
			}
			
			recipeCursorList.close();
						
		}
		
		recipeDB.close();
		
		return recipeList;
		
	}
	
	public Recipe getRecipeInfo(long recipeId){
		
		Recipe tmpReciept=null;
		
		recipeDB=new RecieptDBAdapter(baseContext);
		recipeDB.open();

		Cursor recipeCursorList=recipeDB.getRecipe(recipeId);
		
		if(recipeCursorList!=null){
					
				//ho haurem de fer més net amb la columna
				tmpReciept= new Recipe(
						recipeCursorList.getLong(0),
						recipeCursorList.getString(1),
						recipeCursorList.getString(2),
						recipeCursorList.getInt(4),
						recipeCursorList.getInt(5)
						);
			
				recipeCursorList.close();	
				
		}
		
		recipeDB.close();
		
		return tmpReciept;

	}
	
	public List<Recipe> getAllRecipe(long recipeId){
		
		return null;
		
	}
	
	public void insertSampleRecipe(){
		
		recipeDB=new RecieptDBAdapter(baseContext);
		recipeDB.open();
		
			recipeDB.insertRecipe("Bunyols de Carabassa", "Bunyols del PV", "ca_AD", 4, 90);
			recipeDB.insertRecipe("Brownie de xocolata", "Brownie per a intol·lerants", "ca_AD", 6, 60);
			recipeDB.insertRecipe("Truita de patates", "Truita EGSPAÑOLA!", "ca_AD", 4, 20);

		recipeDB.close();
				
	}
	
	public void insertSampleRecipeSteps(){
		
		recipeDB=new RecieptDBAdapter(baseContext);
		recipeDB.open();
			
			recipeDB.insertRecipeStep(1, "Posar carabasses al forn", "oven",1);
			recipeDB.insertRecipeStep(1, "Barrejar tots els ingredients", "simple", 2);
			recipeDB.insertRecipeStep(1, "Deixar reposar la massa <b>mitja hora</b>","rest", 3);
			recipeDB.insertRecipeStep(1, "Fregir el bunyols fent la forma amb les mans", "simple", 4);
			recipeDB.insertRecipeStep(1, "Ensucrar els bunyols", "simple", 5);
		
			recipeDB.insertRecipeStep(2, "Fer el brownie", "simple", 1);

			
			recipeDB.insertRecipeStep(3, "Pela i tallar patates, fregint-les amb un dit d'oli a foc lent", "simple", 1);
			recipeDB.insertRecipeStep(3, "Batre els ous afegint una mica de sal", "simple", 2);
			recipeDB.insertRecipeStep(3, "Barrejar els ous amb les patates i la ceba", "simple", 3);
			
		recipeDB.close();
				
	}
	
	public void insertSampleRecipeIng(){
		
		recipeDB=new RecieptDBAdapter(baseContext);
		recipeDB.open();
			
			recipeDB.insertRecipeIng(1, "carbassa cuita", 250,IngUnits.GRAM,-1);
			recipeDB.insertRecipeIng(1, "farina de força", 200, IngUnits.GRAM,-1);
			recipeDB.insertRecipeIng(1, "sobre de llevat de forner",1,"unit",-1);
			recipeDB.insertRecipeIng(1, "sucre",1, "soupSpoon",-1);
			recipeDB.insertRecipeIng(1, "aigua",50,IngUnits.GRAM,-1);
			recipeDB.insertRecipeIng(1, "rovell d'ou",1,"unit",-1);

			recipeDB.insertRecipeIng(2, "mantega",150,IngUnits.GRAM,-1);
			recipeDB.insertRecipeIng(2, "xocolata negra",90,IngUnits.GRAM,-1);
			recipeDB.insertRecipeIng(2, "sucre",300,IngUnits.GRAM,-1);
			recipeDB.insertRecipeIng(2, "farina",150,IngUnits.GRAM,-1);
			recipeDB.insertRecipeIng(2, "sobre llevat (mig)",1,IngUnits.GRAM,-1);
			recipeDB.insertRecipeIng(2, "extracte de vainilla",1,"coffeSpoon",-1);
			recipeDB.insertRecipeIng(2, "nous",150,IngUnits.GRAM,-1);
			recipeDB.insertRecipeIng(2, "ous",3,"unit",-1);

			
			recipeDB.insertRecipeIng(3, "patates", 3,"unit", -1);
			recipeDB.insertRecipeIng(3, "ceba", 1,"unit", -1);
			recipeDB.insertRecipeIng(3, "oli girasol", 1,"none", -1);
			
		recipeDB.close();
				
	}
	
	
	

}
