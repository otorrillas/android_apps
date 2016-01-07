package easycooking.otorrillas.easycooking;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;

public class RecipeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_recipe);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final String recipe_name = getIntent().getStringExtra("recipe_name");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), EditRecipeActivity.class);
                i.putExtra("recipe_name", recipe_name);
                startActivity(i);

                //Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                //        .setAction("Action", null).show();


            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setTitle(recipe_name);


        /* Ingredient ListView */
        String[] ingredientList = {"Ingredient1", "Ingredient2", "Ingredient3"};
        ArrayList<ArrayList<String>> altIngredientsList = null;


        // Fill the custom ListView
        ListView lv = (ListView) findViewById(R.id.ingredientListView);
        lv.setAdapter(new IngredientListAdapter(this, ingredientList, altIngredientsList));

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) lv.getLayoutParams();
        lp.height = 60 * ingredientList.length;
        lv.setLayoutParams(lp);

    }
}
