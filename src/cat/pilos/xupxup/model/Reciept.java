package cat.pilos.xupxup.model;

import java.util.ArrayList;
import java.util.Iterator;

public class Reciept {
	
	public long recipeId;
	public String title;
	public String desc;
	public int servings;
	public int prepTime;
	
	private ArrayList<Step> steps;
	private ArrayList<RecIngredient> ingredients;
	
	public Reciept(long recipeId,String title, String desc,int servings, int prepTime){
		
		this.recipeId=recipeId;
		this.title=title;
		this.desc=desc;
	
		this.servings=servings;
		this.prepTime=prepTime;
		
		steps=new ArrayList<Step>();
		ingredients=new ArrayList<RecIngredient>();
		
		
	}
	
	public int addStep(String text, StepType stepType){
		
		Step newStep=new Step(text,stepType.name(),steps.size()+1);
		steps.add(newStep);
		
		return steps.size();		
	}
	
	public int addIngredient(String name, int quantity, int units){
		
		RecIngredient recIng= new RecIngredient(quantity,name,"");
		ingredients.add(recIng);
		
		return ingredients.size();
	
	}
	
	
	
	private void printRecieptIngredients() {


		
		
	}
	
	
	public void printRecieptSteps(){
				
		int i=1;
		Iterator<Step> stepsIt=steps.iterator();
		
		while(stepsIt.hasNext()){
			
			Step tmpStep=stepsIt.next();
			System.out.println(tmpStep.toString());			
			
		}
	
	}
	
	

}
