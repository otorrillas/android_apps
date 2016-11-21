package easycooking.otorrillas.easycooking;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by otorr on 13/12/2015.
 */
public class RecipeListAdapter extends BaseAdapter{
    private String [] recipeNameList;
    private int [] recipeImgs;
    private Context context;
    private static LayoutInflater inflater = null;

    public RecipeListAdapter(Activity act, String [] recipeNameList, int [] recipeImgs) {
        this.recipeNameList = recipeNameList;
        this.recipeImgs = recipeImgs;
        context = act;
        inflater = LayoutInflater.from(act);
    }

    @Override
    public int getCount() {
        return recipeNameList.length;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        View rowView;
        rowView = inflater.inflate(R.layout.recipe_item_list, null);

        // Update of textView
        TextView tmp_tv = (TextView) rowView.findViewById(R.id.textView_ListRecipeName);
        tmp_tv.setText(recipeNameList[position]);

        ImageView tmp_img = (ImageView) rowView.findViewById(R.id.imageView_ListRecipeImg);
        tmp_img.setImageResource(recipeImgs[position]);

        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(context, "You clicked " + recipeNameList[position], Toast.LENGTH_LONG).show();
                Intent i = new Intent(context.getApplicationContext(), RecipeActivity.class);
                i.putExtra("recipe_name", recipeNameList[position]);
                context.startActivity(i);
            }
        });
        return rowView;
    }
}
