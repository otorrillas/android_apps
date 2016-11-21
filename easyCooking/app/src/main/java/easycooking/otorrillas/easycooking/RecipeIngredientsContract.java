package easycooking.otorrillas.easycooking;

/**
 * Created by otorr on 04/01/2016.
 */
public class RecipeIngredientsContract {
    public static final String TABLE_NAME = "recipe_ingredients";
    public static final String RECIPE_ID = "recipe_id";
    public static final String INGREDIENT_ID = "ingredient_id";
    public static final String INGREDIENT_QTY = "quantity";
    public static final String INGREDIENT_UNT = "unit";
    public static final String ALTERNATIVE_INGREDIENTS = "alternatives";

    public static final String[] COLUMNS = {RECIPE_ID, INGREDIENT_ID, INGREDIENT_QTY, INGREDIENT_UNT, ALTERNATIVE_INGREDIENTS};
}
