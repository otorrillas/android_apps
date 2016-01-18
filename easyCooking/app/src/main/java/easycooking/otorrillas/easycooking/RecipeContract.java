package easycooking.otorrillas.easycooking;

/**
 * Created by otorr on 04/01/2016.
 */
public class RecipeContract {
    public static final String TABLE_NAME = "recipe";
    public static final String RECIPE_NAME = "name";
    public static final String COOKING_TIME = "cooking_time";
    public static final String CATEGORY = "category";
    public static final String DIFFICULTY = "difficulty";
    public static final String RECIPE_DATE = "date";
    public static final String RECIPE_INSTRUCTIONS = "instructions";
    public static final String _ID = "_id";
    public static final String[] COLUMNS = {_ID, RECIPE_NAME, COOKING_TIME, CATEGORY, DIFFICULTY, RECIPE_DATE, RECIPE_INSTRUCTIONS};
}
