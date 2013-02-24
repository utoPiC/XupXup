package cat.pilos.xupxup.adapters;

import cat.pilos.xupxup.model.RecIngredient;
import cat.pilos.xupxup.utils.converters.IngUnitsConverter;

import cat.pilos.xupxup.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class IngredientItemView extends LinearLayout {

    private TextView tv_ing_quantity;
    private TextView tv_ing_name;
    private TextView tv_ing_unit;
    private IngUnitsConverter converter;

    public IngredientItemView(Context context, IngUnitsConverter converter) {
        super(context);
        
        this.converter=converter;
        
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.recipe_ing_item, this);
        
        tv_ing_quantity = (TextView) findViewById(R.id.tv_ing_quantity);
        tv_ing_name = (TextView) findViewById(R.id.tv_ing_name);
        tv_ing_unit= (TextView) findViewById(R.id.tv_ing_unit);
        
        
    }

    public void bind(RecIngredient ingredient) {
    	
        tv_ing_quantity.setText(""+ingredient.quantity);
        tv_ing_name.setText(ingredient.ingredient.name);
        tv_ing_unit.setText(converter.getUnitTranslation(ingredient.unit));
        
    }
}

