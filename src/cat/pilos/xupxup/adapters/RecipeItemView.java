package cat.pilos.xupxup.adapters;

import cat.pilos.xupxup.model.Recipe;

import cat.pilos.xupxup.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


public class RecipeItemView extends LinearLayout {

    private TextView tv_rec_title;
    private TextView tv_rec_desc;
    private TextView tv_rec_servings;
    private TextView tv_rec_prepTime;
    
    
    public RecipeItemView(Context context) {
        super(context);
        
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.recipe_ing_item, this);
        
        tv_rec_title=(TextView) findViewById(R.id.tv_rec_title);
        tv_rec_desc=(TextView) findViewById(R.id.tv_rec_desc);
        tv_rec_servings=(TextView) findViewById(R.id.tv_rec_servings);
        tv_rec_prepTime=(TextView) findViewById(R.id.tv_rec_prepTime);
        
    }

    public void bind(Recipe reciept) {
    	
        tv_rec_title.setText(reciept.title);
        tv_rec_desc.setText(reciept.desc);
        tv_rec_servings.setText(""+reciept.servings);
        tv_rec_prepTime.setText(""+reciept.recipeId);

    }
    
    
}

