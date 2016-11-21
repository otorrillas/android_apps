package easycooking.otorrillas.easycooking;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by otorr on 29/12/2015.
 */
public class IngredientListAdapter extends BaseAdapter {
    private String[] ingredientList;
    private ArrayList<ArrayList<String>> altIngredientsList;
    private Context context;
    private static LayoutInflater inflater = null;

    public IngredientListAdapter(Activity act, String[] ingredientList, ArrayList<ArrayList<String>> altIngredientsList) {
        this.ingredientList = ingredientList;
        this.altIngredientsList = altIngredientsList;
        context = act;
        inflater = LayoutInflater.from(act);
    }

    @Override
    public int getCount() {
        return ingredientList.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View rowView;
        rowView = inflater.inflate(R.layout.ingredient_item_list, null);

        TextView ingr_quant = (TextView) rowView.findViewById(R.id.textView_QuantityNum);
        ingr_quant.setText("69");

        TextView ingr_unit = (TextView) rowView.findViewById(R.id.textView_QuantityUnit);
        String tmp_unit = "grammes";
        ingr_unit.setText(" " + tmp_unit + " ");

        TextView ingr_name = (TextView) rowView.findViewById(R.id.textView_IngredientName);
        ingr_name.setText(ingredientList[position]);


        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                LayoutInflater infl = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                final PopupWindow popupWindow = new PopupWindow(context);
                View layout = infl.inflate(R.layout.ingredients_popup, null);
                popupWindow.setContentView(layout);
                popupWindow.setFocusable(true);
                popupWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
                */
            }
        });
        return rowView;
    }
}
