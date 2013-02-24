package cat.pilos.xupxup.utils.converters;


import cat.pilos.xupxup.R;
import cat.pilos.xupxup.utils.IngUnits;
import android.content.Context;

public class IngUnitsConverter {

	private Context context;
	
	private String gramString;
	private String kilogramString;
	
	private String unitString;

	private String coffeSpoonString;
	private String soupSpoonString;
	private String coffeCupString;
	
	private String teaCupString;
	private String glassString;
	
	
	
	public String getUnitTranslation(String unit){
		
		if(unit.equals(IngUnits.GRAM)) return gramString;
		else if(unit.equals(IngUnits.KILO_GRAM)) return kilogramString;
		else if(unit.equals(IngUnits.UNIT)) return unitString;
		else if(unit.equals(IngUnits.NONE)) return "";
		else if(unit.equals(IngUnits.SPOON_COFFE)) return coffeSpoonString;
		else if(unit.equals(IngUnits.SPOON_SOUP)) return soupSpoonString;
		else if(unit.equals(IngUnits.CUP_COFFE)) return coffeCupString;
		else if(unit.equals(IngUnits.CUP_TEA)) return teaCupString;
		else if(unit.equals(IngUnits.GLASS)) return glassString;
		else return "error";
				
	}
	
	
	public IngUnitsConverter(Context context){
		
		this.context=context;
		
		gramString=context.getString(R.string.unit_GRAM);
		kilogramString=context.getString(R.string.unit_KILO_GRAM);
		unitString=context.getString(R.string.unit_UNIT);

		coffeSpoonString=context.getString(R.string.unit_SPOON_COFFE);
		soupSpoonString=context.getString(R.string.unit_SPOON_SOUP);
		
		coffeCupString=context.getString(R.string.unit_CUP_COFFE);
		teaCupString=context.getString(R.string.unit_CUP_TEA);
		glassString=context.getString(R.string.unit_GLASS);
		
		
	}
	
	
	
}
