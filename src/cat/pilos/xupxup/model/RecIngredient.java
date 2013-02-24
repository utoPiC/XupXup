package cat.pilos.xupxup.model;

public class RecIngredient {
	
	public Ingredient ingredient;
	public int quantity;
	
	/*improve with classes of units*/
	public String unit;
	
	
	public RecIngredient(int quantity, String name, String unit){
		
		this.ingredient=new Ingredient();
		ingredient.name=name;
		this.unit=unit;
		
		this.quantity=quantity;
		
		
	}
	

}
