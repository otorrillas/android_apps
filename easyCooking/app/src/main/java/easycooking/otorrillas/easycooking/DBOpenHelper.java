package easycooking.otorrillas.easycooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.format.DateFormat;

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
        final String[] names = {
                "Chocolate cake",
                "Spiced carrot & lentil soup",
                "Chilli con carne",
                "BBQ pulled pork",
                "Chinese-style wintery rolls"
        };
        int[] cooking_times = {
                90,
                15,
                70,
                300,
                50
        };
        final String[] difficulty = {
                "Easy",
                "Easy",
                "Medium",
                "Easy",
                "Hard"
        };

        final String[] category = {
                "British",
                "British",
                "Mexican",
                "American",
                "Chinese"
        };

        final String[] instructions = {
                "Butter a 20cm round cake tin (7.5cm deep) and line the base. Preheat the oven to fan 140C/conventional 160C/ gas 3. Break 200g good quality dark chocolate in pieces into a medium, heavy-based pan. Cut 200g butter into pieces and tip in with the chocolate, then mix 1 tbsp instant coffee granules into 125ml cold water and pour into the pan. Warm through over a low heat just until everything is melted – don’t overheat. Or melt in the microwave on Medium for about 5 minutes, stirring half way through.\n" +
                        "While the chocolate is melting, mix 85g self-raising flour, 85g plain flour, ¼ bicarbonate of soda, 200g light muscovado sugar, 200g golden caster sugar and 25g cocoa powder in a big bowl, mixing with your hands to get rid of any lumps. Beat 3 medium eggs in a bowl and stir in 75ml (5 tbsp) buttermilk.\n" +
                        "Now pour the melted chocolate mixture and the egg mixture into the flour mixture, stirring just until everything is well blended and you have a smooth, quite runny consistency. Pour this into the tin and bake for 1 hour 25- 1 hour 30 minutes – if you push a skewer in the centre it should come out clean and the top should feel firm (don’t worry if it cracks a bit). Leave to cool in the tin (don’t worry if it dips slightly), then turn out onto a wire rack to cool completely.\n" +
                        "When the cake is cold, cut it horizontally into three. Make the ganache: chop 200g good quality dark chocolate into small pieces and tip into a bowl. Pour a 284ml carton of double cream into a pan, add 2 tbsp golden caster sugar, and heat until it is about to boil. Take off the heat and pour it over the chocolate. Stir until the chocolate has melted and the mixture is smooth.\n" +
                        "Sandwich the layers together with just a little of the ganache. Pour the rest over the cake letting it fall down the sides and smoothing to cover with a palette knife. Decorate with grated chocolate or a pile of chocolate curls. The cake keeps moist and gooey for 3-4 days.",
                "Heat a large saucepan and dry-fry the cumin seeds and chilli flakes for 1 min, or until they start to jump around the pan and release their aromas. Scoop out about half of the seeds with a spoon and set aside. Add the oil, carrot, lentils, stock and milk to the pan and bring to the boil. Simmer for 15 mins until the lentils have swollen and softened.\n" +
                        "Whizz the soup with a stick blender or in a food processor until smooth (or leave it chunky if you prefer). Season to taste and finish with a dollop of yogurt and a sprinkling of the reserved toasted spices. Serve with warmed naan breads.",
                "Prepare your vegetables. Chop 1 large onion into small dice, about 5mm square. The easiest way to do this is to cut the onion in half from root to tip, peel it and slice each half into thick matchsticks lengthways, not quite cutting all the way to the root end so they are still held together. Slice across the matchsticks into neat dice. Cut 1 red pepper in half lengthways, remove stalk and wash the seeds away, then chop. Peel and finely chop 2 garlic cloves.\n" +
                        "Start cooking. Put your pan on the hob over a medium heat. Add the oil and leave it for 1-2 minutes until hot (a little longer for an electric hob). Add the onions and cook, stirring fairly frequently, for about 5 minutes, or until the onions are soft, squidgy and slightly translucent. Tip in the garlic, red pepper, 1 heaped tsp hot chilli powder or 1 level tbsp mild chilli powder, 1 tsp paprika and 1 tsp ground cumin. Give it a good stir, then leave it to cook for another 5 minutes, stirring occasionally.\n" +
                        "Brown the 500g lean minced beef. Turn the heat up a bit, add the meat to the pan and break it up with your spoon or spatula. The mix should sizzle a bit when you add the mince. Keep stirring and prodding for at least 5 minutes, until all the mince is in uniform, mince-sized lumps and there are no more pink bits. Make sure you keep the heat hot enough for the meat to fry and become brown, rather than just stew.\n" +
                        "Making the sauce. Crumble 1 beef stock cube into 300ml hot water. Pour this into the pan with the mince mixture. Open 1 can of chopped tomatoes (400g can) and add these as well. Tip in ½ tsp dried marjoram and 1 tsp sugar, if using (see tip at the bottom), and add a good shake of salt and pepper. Squirt in about 2 tbsp tomato purée and stir the sauce well.\n" +
                        "Simmer it gently. Bring the whole thing to the boil, give it a good stir and put a lid on the pan. Turn down the heat until it is gently bubbling and leave it for 20 minutes. You should check on the pan occasionally to stir it and make sure the sauce doesn’t catch on the bottom of the pan or isn’t drying out. If it is, add a couple of tablespoons of water and make sure that the heat really is low enough. After simmering gently, the saucy mince mixture should look thick, moist and juicy.\n" +
                        "Bring on the beans. Drain and rinse 1 can of red kidney beans (410g can) in a sieve and stir them into the chilli pot. Bring to the boil again, and gently bubble without the lid for another 10 minutes, adding a little more water if it looks too dry. Taste a bit of the chilli and season. It will probably take a lot more seasoning than you think. Now replace the lid, turn off the heat and leave your chilli to stand for 10 minutes before serving, and relax. Leaving your chilli to stand is really important as it allows the flavours to mingle and the meat.\n" +
                        "Serve with soured cream and plain boiled long grain rice.",
                "Heat oven to 150C/130C fan/gas 2. Rub the pork with 2 tbsp of the olive oil. Heat a large non-stick pan until very hot and sear the pork on all sides until golden brown.\n" +
                        "Place the meat on a wire rack in a roasting tin. Mix the paprika, mustard powder, garlic and onion salt, and some black pepper with the liquid smoke, if using. Brush all over the meat.\n" +
                        "Add 1 cup of water to the roasting tin, cover very tightly with foil and cook for 5 hrs or until almost falling apart.\n" +
                        "Drain the juices from the meat into a measuring jug. Shred the pork using 2 forks, discarding the fat.\n" +
                        "Skim off the fat from the juices. Mix 125ml of the juices with 4 tbsp BBQ sauce (see recipe in 'goes well with') and pour over the meat. Keep warm until serving, or reheat.\n" +
                        "To assemble, pile the meat into the halved brioche buns, spoon over the BBQ sauce, top with coleslaw and pickles, and sandwich together.",
                "Make the filling. Slice the carrot into long, thin lengths. Then cut into long, thin ‘julienne’ sticks. Cut the leek into three, halve, then cut into sticks. Slice the celeriac and celery, then cut into julienne sticks as before.\n" +
                        "Halve the onion lengthways, then slice thinly. Slice the peppers into sticks. Cut the chilli into thin shreds. Cut the ginger into small julienne sticks and chop the garlic. Mix everything together in a bowl with half the picked herb leaves.\n" +
                        "Heat olive oil in a wok until very hot and add half the veg. Toss for 30 secs to wilt, then remove from the heat, season and toss in soy and sesame oil. Drain in a colander set over a bowl. Repeat with remaining veg. Mix in bean sprouts, sesame seeds, remaining herbs and extra soy, if liked.\n" +
                        "When the filling has cooled, peel off a spring roll wrapper – it will feel slightly sticky. Cover the remainder with a tea towel. Lay the wrapper on a board and brush the edges with egg white. Spoon a sixth of the vegetables in a line on the top third of the wrapper.\n" +
                        "Fold over the top of the wrapper and press well down all round. Fold in the sides, then roll up firmly, pressing the end in well. The roll should be about 18cm long. Repeat process with the remaining filling and wrappers. The rolls can be chilled at this point for up to 3 hrs, uncovered, in the fridge, until ready to cook. (You should only cook the rolls just before you’re ready to serve, so that they remain crisp.)\n" +
                        "Heat a deep fat fryer or large pan a third full of oil to 180C (or until a cube of bread crisps and turns brown). Deep-fry two rolls at a time for 5 mins until golden and crisp. Drain on paper towel. Mix ingredients for the sauce and pour into 1 larger or six very small bowls. Trim the ends off the rolls, then cut in half diagonally. Serve on one large or six small plates with sauce. Garnish with herb leaves or salad."
        };
        final ArrayList<ArrayList<RecipeIngredient>> ingredients = new ArrayList<>();
        ingredients.add(firstRecipeIngredients());
        ingredients.add(secondRecipeIngredients());
        ingredients.add(thirdRecipeIngredients());
        ingredients.add(fourthRecipeIngredients());
        ingredients.add(fifthRecipeIngredients());


        for (int i = 0; i < 5; i++) {
            final String timestamp = (DateFormat.format("dd-MM-yyyy kk:mm:ss", new java.util.Date()).toString());
            long id = addRecipe(names[i], cooking_times[i], difficulty[i], category[i], timestamp, instructions[i]);
            Integer _id = (int) (long) id;

            addRecipeIngredients(_id, ingredients.get(i));
        }
    }


    private ArrayList<RecipeIngredient> firstRecipeIngredients() {
        ArrayList<RecipeIngredient> res = new ArrayList<>();
        String[] alternatives;
        RecipeIngredient tmp_rp;

        alternatives = new String[]{"cocoa"};
        tmp_rp = new RecipeIngredient(200, "dark chocolate", "grammes", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(200, "butter", "grammes", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(1, "coffee", "tea spoon", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(85, "self-raising flour", "grammes", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(85, "plain flour", "grammes", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(1, "bicarbonate of soda", "tea spoon", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(400, "sugar", "grammes", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(25, "cocoa", "grammes", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(3, "egg", "units", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(75, "buttermilk", "ml", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        return res;
    }


    private ArrayList<RecipeIngredient> secondRecipeIngredients() {
        ArrayList<RecipeIngredient> res = new ArrayList<>();
        String[] alternatives;
        RecipeIngredient tmp_rp;

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(2, "cumin seeds", "tsp", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(1, "chilli", "pinch", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(2, "olive oil", "tsp", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(600, "carrots", "grammes", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(140, "red lentils", "grammes", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(1000, "hot vegetable stock", "ml", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(125, "milk", "ml", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        return res;
    }

    private ArrayList<RecipeIngredient> thirdRecipeIngredients() {
        ArrayList<RecipeIngredient> res = new ArrayList<>();
        String[] alternatives;
        RecipeIngredient tmp_rp;

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(1, "olive oil", "tsp", alternatives);
        res.add(tmp_rp);
        //addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(1, "onion", "unit", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(1, "red pepper", "unit", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(2, "garlic gloves", "unit", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(1, "paprika", "tsp", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(1, "ground cumin", "tsp", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(500, "lean minced beef", "grammes", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(1, "beef stock", "grammes", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(400, "can chopped tomatoes", "grammes", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(1, "dried marjoram", "tsp", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(1, "tomato purée", "tsp", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(1, "sugar", "tsp", alternatives);
        res.add(tmp_rp);
        //addIngredient(tmp_rp.name);

        alternatives = new String[]{};
        tmp_rp = new RecipeIngredient(410, "can red kidney beans", "grammes", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);

        return res;
    }

    private ArrayList<RecipeIngredient> fourthRecipeIngredients() {
        ArrayList<RecipeIngredient> res = new ArrayList<>();
        String[] alternatives;
        RecipeIngredient tmp_rp;

        alternatives = new String[]{"Cocoa"};
        tmp_rp = new RecipeIngredient(200, "dark chocolate", "grammes", alternatives);
        res.add(tmp_rp);
        addIngredient(tmp_rp.name);


        return res;
    }

    private ArrayList<RecipeIngredient> fifthRecipeIngredients() {
        ArrayList<RecipeIngredient> res = new ArrayList<>();
        String[] alternatives;
        RecipeIngredient tmp_rp;

        alternatives = new String[]{"Cocoa"};
        tmp_rp = new RecipeIngredient(200, "dark chocolate", "grammes", alternatives);
        res.add(tmp_rp);


        return res;
    }


    /*
        RECIPE items
    */

    public long addRecipe(String name, Integer cooking_time, String category, String difficulty, String timestamp, String instructions) {
        ContentValues values = new ContentValues();
        values.put(RecipeContract.RECIPE_NAME, name);
        values.put(RecipeContract.COOKING_TIME, cooking_time);
        values.put(RecipeContract.CATEGORY, category);
        values.put(RecipeContract.DIFFICULTY, difficulty);
        values.put(RecipeContract.RECIPE_DATE, timestamp);
        values.put(RecipeContract.RECIPE_INSTRUCTIONS, instructions);

        long id = getWritableDatabase().insert(RecipeContract.TABLE_NAME, null, values);


        return id;
    }

    public void updateRecipe(Integer id, String name, Integer cooking_time, String category, String difficulty, String timestamp, String instructions) {
        ContentValues values = new ContentValues();
        values.put(RecipeContract.RECIPE_NAME, name);
        values.put(RecipeContract.COOKING_TIME, cooking_time);
        values.put(RecipeContract.CATEGORY, category);
        values.put(RecipeContract.DIFFICULTY, difficulty);
        values.put(RecipeContract.RECIPE_DATE, timestamp);
        values.put(RecipeContract.RECIPE_INSTRUCTIONS, instructions);

        long res = getWritableDatabase().update(RecipeContract.TABLE_NAME, values, "_id=" + id.toString(), null);

    }

    public void deleteRecipe(Integer id) {
        long res = getWritableDatabase().delete(RecipeContract.TABLE_NAME, "_id=" + id.toString(), null);
    }

    public ArrayList<ArrayList<String>> getLastRecipes(Integer re_limit) {
        ArrayList<ArrayList<String>> res = new ArrayList<>();

        String query = "SELECT " + RecipeContract._ID
                + " " + RecipeContract.RECIPE_NAME
                + " " + RecipeContract.DIFFICULTY
                + " " + RecipeContract.COOKING_TIME
                + " FROM " + RecipeContract.TABLE_NAME
                + " ORDER BY datetime(" + RecipeContract.RECIPE_DATE + ") DESC"
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

    /* Recipe Ingredients */

    public void addRecipeIngredients(Integer id, ArrayList<RecipeIngredient> recipeIngredient) {

    }


    /*
        INGREDIENTS items
     */

    public void addIngredient(String name) {

    }

}
