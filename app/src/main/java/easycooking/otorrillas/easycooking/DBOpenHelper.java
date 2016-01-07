package easycooking.otorrillas.easycooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * Created by otorr on 04/01/2016.
 */
public class DBOpenHelper extends SQLiteOpenHelper {

    final private static String CREATE_RECIPE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + RecipeContract.TABLE_NAME + " (" + RecipeContract._ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + RecipeContract.RECIPE_NAME + " TEXT NOT NULL, "
                    + RecipeContract.COOKING_TIME + " INTEGER, "
                    + RecipeContract.RECIPE_DATE + " TEXT NOT NULL, "
                    + RecipeContract.RECIPE_INSTRUCTIONS + " TEXT)";

    final private static String CREATE_INGREDIENT_TABLE =
            "CREATE TABLE IF NOT EXISTS " + IngredientContract.TABLE_NAME + " (" + IngredientContract._ID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + IngredientContract.INGREDIENT_NAME + " TEXT NOT NULL UNIQUE)";

    final private static String CREATE_RECIPE_INGREDIENTS_TABLE =
            "CREATE TABLE IF NOT EXISTS " + RecipeIngredientsContract.TABLE_NAME + " ("
                    + RecipeIngredientsContract.RECIPE_ID + " INTEGER NOT NULL, "
                    + RecipeIngredientsContract.INGREDIENT_ID + " INTEGER NOT NULL, "
                    + RecipeIngredientsContract.INGREDIENT_QTY + " INTEGER, "
                    + RecipeIngredientsContract.INGREDIENT_UNT + " TEXT, "
                    + RecipeIngredientsContract.ALTERNATIVE_INGREDIENTS + " TEXT, "
                    + "PRIMARY KEY ("
                    + RecipeIngredientsContract.RECIPE_ID + ", "
                    + RecipeIngredientsContract.INGREDIENT_ID
                    + "))";


    final private static String NAME = "easycooking_db";
    final private static Integer VERSION = 1;
    final private Context mContext;

    public DBOpenHelper(Context context) {
        super(context, NAME, null, VERSION);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_RECIPE_TABLE);
        db.execSQL(CREATE_RECIPE_INGREDIENTS_TABLE);
        db.execSQL(CREATE_INGREDIENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /*
        INITIAL DATA
     */

    public void insert_initial_data() {

    }


    /*
        RECIPE items
    */

    public long addRecipe(String name, Integer cooking_time, String timestamp, String instructions) {

        ContentValues values = new ContentValues();
        values.put(RecipeContract.RECIPE_NAME, name);
        values.put(RecipeContract.COOKING_TIME, cooking_time);
        values.put(RecipeContract.RECIPE_DATE, timestamp);
        values.put(RecipeContract.RECIPE_INSTRUCTIONS, instructions);

        long id = getWritableDatabase().insert(RecipeContract.TABLE_NAME, null, values);


        return id;
    }

    public void updateRecipe(Integer id, String name, Integer cooking_time, String timestamp, String instructions) {

        ContentValues values = new ContentValues();
        values.put(RecipeContract.RECIPE_NAME, name);
        values.put(RecipeContract.COOKING_TIME, cooking_time);
        values.put(RecipeContract.RECIPE_DATE, timestamp);
        values.put(RecipeContract.RECIPE_INSTRUCTIONS, instructions);

        long res = getWritableDatabase().update(RecipeContract.TABLE_NAME, values, "_id=" + id, null);

    }

    public void deleteRecipe(Integer id) {
        long res = getWritableDatabase().delete(RecipeContract.TABLE_NAME, "_id=" + id, null);
    }

    public ArrayList<ArrayList<String>> getLastRecipes(Integer re_limit) {
        ArrayList<ArrayList<String>> res = new ArrayList<>();

        String query = "SELECT " + RecipeContract._ID
                + " " + RecipeContract.RECIPE_NAME
                + " " + RecipeContract.DIFFICULTY
                + " " + RecipeContract.COOKING_TIME
                + " FROM " + RecipeContract.TABLE_NAME
                + " ORDER BY " + RecipeContract.RECIPE_DATE + " DESC"
                + " LIMIT " + re_limit.toString();

        Cursor cursor = getReadableDatabase().rawQuery(query, null);
        while (cursor.moveToNext()) {
            ArrayList<String> row = new ArrayList<>();
            row.add(cursor.getString(0));   // id
            row.add(cursor.getString(1));   // name
            row.add(cursor.getString(2));   // difficulty
            row.add(cursor.getString(3));   // cooking_time
            res.add(row);
        }

        return res;
    }

    /*
        INGREDIENTS items
     */


}
