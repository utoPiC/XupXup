package cat.pilos.xupxup.adapters;

import cat.pilos.xupxup.model.Step;
import cat.pilos.xupxup.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;


public class StepItemView extends LinearLayout {
	
    private TextView tv_step_num;
    private TextView tv_step_text;
    
    //falta fer bindings!!
    

    public StepItemView(Context context) {
        super(context);
        
        LayoutInflater layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.step_item, this);
        
        tv_step_num=(TextView) findViewById(R.id.tv_step_num);
        tv_step_text=(TextView) findViewById(R.id.tv_step_text);
        
    }

    public void bind(Step step) {
    	
        tv_step_num.setText(step.num+".");
        tv_step_text.setText(step.text);
        
    }
}

