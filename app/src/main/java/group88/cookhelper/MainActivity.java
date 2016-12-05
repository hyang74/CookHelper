package group88.cookhelper;

import android.app.Activity;
import android.content.res.AssetManager;
import android.support.v4.app.INotificationSideChannel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.text.*;
import android.content.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.io.Reader;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Group Project
 * CookHelper
 */


public class MainActivity extends AppCompatActivity {
    private ListView recipeList;

    private String[] spinnerClass ={"Any","Beef", "Chicken","Pork","Seafood", "Veggie","Mixed"};
    private String[] spinnerOrigin= {"Any","Italian", "Chinese", "Midle Eastern", "Indian", "American"};
    private String[] spinnerCategory= {"Any","Starter", "Main Dish", "Desert=-p=[", "Drink", "Sauce", "Salad"};

    public static List<Recipe> allRecipe=new LinkedList<>();
    public static List<Recipe> filterResult=new LinkedList<>();
    public  List<String> showList=new LinkedList<String>();
    private int numOfFilteredRecipe;

    private static String savedSearch="";
    private static int savedClass=0;
    private static int savedOrigin =0;
    private static int savedCategory =0;


    EditText mEditText;
    Button mClearText;
    Button filter;
    Button reset;
    Spinner spClass;
    Spinner spOrigin;
    Spinner spCategory;
    int backButtonCount=0;
    String savedString;
    public static final String PREFS_NAME = "RECIPE";





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }


        readBtn();

        filter = (Button) findViewById(R.id.filter);
        reset = (Button) findViewById(R.id.reset);

        mEditText = (EditText) findViewById(R.id.search);
        mEditText.setText(savedSearch);
        mClearText = (Button) findViewById(R.id.clearText);
        if(!mEditText.getText().toString().isEmpty()){
            mClearText.setVisibility(View.VISIBLE);
        }
        else {
            mClearText.setVisibility(View.GONE);
        }


        //This is how to add items to spinner:
        spClass = (Spinner) findViewById(R.id.SPclass);
        ArrayAdapter<String> adapterClass = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerClass);
        spClass.setAdapter(adapterClass);
        spClass.setSelection(savedClass);

        spOrigin = (Spinner) findViewById(R.id.SPorigin);
        ArrayAdapter<String> adapterOrigin = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerOrigin);
        spOrigin.setAdapter(adapterOrigin);
        spOrigin.setSelection(savedOrigin);

        spCategory = (Spinner) findViewById(R.id.SPcategory);
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerCategory);
        spCategory.setAdapter(adapterCategory);
        spCategory.setSelection(savedCategory);

        recipeList = (ListView) findViewById(R.id.recipe_list_view);
        ArrayAdapter adapterRecipe = new ArrayAdapter(this, android.R.layout.simple_list_item_1, showList);
        recipeList.setAdapter(adapterRecipe);


        //We use temperary array to show the list,
        //will be substitute by JSON reader


        // showList = new LinkedList<>();
        if(allRecipe.isEmpty()) {
//
//            {"Any","Beef", "Chicken","Pork","Seafood", "Vegie","Mixed"};
//             {"Any","Italian", "Chinese", "Midle Eastern", "Indian", "American"};
//           {"Any","Starter", "Main Dish", "Desert", "Drink", "Sauce", "Salad"};
            showList.clear();
            filterResult.clear();
            Recipe Steak = new Recipe();
            Steak.setRecipeName("Steak");
            Steak.setRecipeOrigin("American");
            Steak.setRecipeClass("Beef");
            Steak.setRecipeCategory("Main Dish");
            Steak.addIngredients(new Ingredient("balsamic vinegar",(float)0.5, Ingredient.Measure.cup));
            Steak.addIngredients(new Ingredient("soy sauce",(float)0.25, Ingredient.Measure.cup));
            Steak.addIngredients(new Ingredient("minced garlic",3, Ingredient.Measure.table_spoon));
            Steak.addIngredients(new Ingredient("honey",2, Ingredient.Measure.table_spoon));
            Steak.addIngredients(new Ingredient("olive oil",2, Ingredient.Measure.table_spoon));
            Steak.addIngredients(new Ingredient("ground black pepper",2, Ingredient.Measure.tea_spoon));
            Steak.addIngredients(new Ingredient("Worcestershire sauce",1, Ingredient.Measure.tea_spoon));
            Steak.addIngredients(new Ingredient("onion powder",2, Ingredient.Measure.tea_spoon));
            Steak.addIngredients(new Ingredient("salt",(float)0.5, Ingredient.Measure.tea_spoon));
            Steak.addIngredients(new Ingredient("liquid smoke flavoring",(float)0.5, Ingredient.Measure.tea_spoon));
            Steak.addIngredients(new Ingredient("rib-eye steaks",(float)2.5, Ingredient.Measure.pound));
            Steak.addSteps("Mix vinegar, soy sauce, garlic, honey, olive oil, ground black pepper, etc (all" +
                    "the ingredients) in a bowl");
            Steak.addSteps("Place steak in glass dish with the marinade and rub liquid onto the meat.");
            Steak.addSteps("Refrigerate for 1 -2 days");
            Steak.addSteps("Preheat grill to medium- high");
            Steak.addSteps("Lightly oil grill.");
            Steak.addSteps("Grill steaks 7 mins per side or desired doneness.");
            Steak.addSteps("Serve and enjoy!");
            allRecipe.add(Steak);

            Recipe VegiePho = new Recipe();
            VegiePho.setRecipeName("Veggie Pho");
            VegiePho.setRecipeOrigin("Any");
            VegiePho.setRecipeClass("Veggie");
            VegiePho.setRecipeCategory("Main Dish");
            VegiePho.addIngredients(new Ingredient("onion",1, Ingredient.Measure.piece));
            VegiePho.addIngredients(new Ingredient("shallot",(float)0.5,Ingredient.Measure.piece));
            VegiePho.addIngredients(new Ingredient("garlic cloves",(float)0.5,Ingredient.Measure.piece));
            VegiePho.addIngredients(new Ingredient("sliced ginger",1,Ingredient.Measure.piece));
            VegiePho.addIngredients(new Ingredient("cinnamon sticks",1, Ingredient.Measure.piece));
            VegiePho.addIngredients(new Ingredient("vegetable stock",2,Ingredient.Measure.cup));
            VegiePho.addIngredients(new Ingredient("soy sauce",2,Ingredient.Measure.table_spoon));
            VegiePho.addIngredients(new Ingredient("salt",1,Ingredient.Measure.tea_spoon));
            VegiePho.addIngredients(new Ingredient("riced noodles",1,Ingredient.Measure.pound));
            VegiePho.addIngredients(new Ingredient("tofu",8,Ingredient.Measure.ounce));
            VegiePho.addIngredients(new Ingredient("scallions",6,Ingredient.Measure.piece));
            VegiePho.addIngredients(new Ingredient("bean sprouts",(float)1.5,Ingredient.Measure.cup));
            VegiePho.addIngredients(new Ingredient("lime",1,Ingredient.Measure.piece));
            VegiePho.addSteps("To make broth: Place all the ingredients in pot with 8 cups of water");
            VegiePho.addSteps("Bring broth to a boil");
            VegiePho.addSteps("Strain broth and return to pot. Discard all solids");
            VegiePho.addSteps("To make pho: Cook rice noodles according to packet instructions. Drain them and rinse with cold water");
            VegiePho.addSteps("Ladle the broth over noodles");
            VegiePho.addSteps("Top with tofu, sprouts, onions or any additional ingredients to your liking! Enjoy!");
            allRecipe.add(VegiePho);

            Recipe GrilledChicken = new Recipe();
            GrilledChicken.setRecipeName("Grilled Chicken");
            GrilledChicken.setRecipeOrigin("American");
            GrilledChicken.setRecipeClass("Meat");
            GrilledChicken.setRecipeCategory("Main Dish");
            GrilledChicken.addIngredients(new Ingredient("skinless chicken",4, Ingredient.Measure.piece));
            GrilledChicken.addIngredients(new Ingredient("lemon juice",(float)0.5,Ingredient.Measure.cup));
            GrilledChicken.addIngredients(new Ingredient("onion powder",(float)0.5,Ingredient.Measure.tea_spoon));
            GrilledChicken.addIngredients(new Ingredient("black pepper",(float)0.4,Ingredient.Measure.tea_spoon));
            GrilledChicken.addIngredients(new Ingredient("salt",1,Ingredient.Measure.tea_spoon));
            GrilledChicken.addIngredients(new Ingredient("dried parsley",1,Ingredient.Measure.tea_spoon));
            GrilledChicken.addSteps("Preheat grill for medium high heat and lightly oil grate");
            GrilledChicken.addSteps("Dip chicken in lemon juice and sprinkle with all the ingredients listed above ");
            GrilledChicken.addSteps("Cook on grill for 10-15 minutes per side");
            GrilledChicken.addSteps("Serve and enjoy!");
            allRecipe.add(GrilledChicken);


















            Recipe BeefPho = new Recipe();
            BeefPho.setRecipeName("Beef Pho");
            allRecipe.add(BeefPho);



            Recipe BeefStew = new Recipe();
            BeefStew.setRecipeName("Beef Stew");
            allRecipe.add(BeefStew);

            Recipe BeefAndVegiePizza = new Recipe();
            BeefAndVegiePizza.setRecipeName("Beef and Vegie Pizza");
            allRecipe.add(BeefAndVegiePizza);

            Recipe IceCream = new Recipe();
            IceCream.setRecipeName("Ice Cream");
            allRecipe.add(IceCream);


            writeBtn();
            for(int i=0;i<allRecipe.size();i++){
                filterResult.add(allRecipe.get(i));
            }

        }
        if (filterResult.size()==allRecipe.size()) {
            reset();
        }
        else{
            showList.clear();
            for(int i=0;i<filterResult.size();i++){

                showList.add(filterResult.get(i).getRecipeName());

            }
            displayList(showList);
        }









        // just for test

        // read_jason();



        //This is how to add  items to list: use ArrayAdapter



        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterFunction(allRecipe,mEditText.getText().toString(),spClass.getSelectedItemPosition(),spOrigin.getSelectedItemPosition(),spCategory.getSelectedItemPosition());


                // Code snipet to activate search results ?
                /*
                filterResult = filterFunction(allRecipe,
                        mEditText.getText().toString(),
                        spClass.getSelectedItemPosition(),
                        spOrigin.getSelectedItemPosition(),
                        spCategory.getSelectedItemPosition());

                showList.clear();
                for(int i = 0; i < filterResult.size(); i++){

                    showList.add(filterResult.get(i).getRecipeName());

                }
                displayList(showList);
                */
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        // Do not change, this block is used to clear text on clicking the X botton

        //initially clear button is invisible
        mClearText.setVisibility(View.INVISIBLE);

        //clear button visibility on text change
        mEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                //do nothing
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length() != 0) {
                    mClearText.setVisibility(View.VISIBLE);
                } else {
                    mClearText.setVisibility(View.GONE);
                }
            }
        });
        recipeList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView,View view, int i, long l){

                goRecipe(i);
            }
        });



    }
    // This function is called to reset the text
    public void clear(View view) {
        mEditText.setText("");
        mClearText.setVisibility(View.GONE);
    }
    public void goRecipe(int i) {
        savedCategory=spCategory.getSelectedItemPosition();
        savedClass=spClass.getSelectedItemPosition();
        savedOrigin=spOrigin.getSelectedItemPosition();
        savedSearch=mEditText.getText().toString();
        int j=0;
        while(allRecipe.get(j).getRecipeName()!=showList.get(i)){
            j++;
        }
        Intent intent1 = new Intent(this,showRecipe.class );
        intent1.putExtra("RecipeNumber",j);
        startActivity(intent1);
    }
    public void goAdd(View view) {
        savedSearch="";
        savedOrigin=0;
        savedClass=0;
        savedCategory=0;
        Intent intentAdd = new Intent(this,editRecipe.class );
        int numOfNewRecipe=allRecipe.size();
        Recipe newRecipe=new Recipe();
        allRecipe.add(newRecipe);
        intentAdd.putExtra("RecipeNumber", numOfNewRecipe);
        intentAdd.putExtra("trueIfAdd",true);
        startActivity(intentAdd);
    }
    public void reset (){
        spClass.setSelection(0);
        spOrigin.setSelection(0);
        spCategory.setSelection(0);
        mEditText.setText("");
        mClearText.setVisibility(View.GONE);
        savedSearch="";
        savedOrigin=0;
        savedClass=0;
        savedCategory=0;
        filterResult=new LinkedList<>();
        for(int i=0;i<allRecipe.size();i++){
            filterResult.add(allRecipe.get(i));
        }
        showList=new LinkedList<>();
        for(int i=0;i<filterResult.size();i++){
            showList.add(filterResult.get(i).getRecipeName());
        }
        displayList(showList);
    }
    public void displayList(List<String> newList){
        ArrayAdapter newAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, newList);
        recipeList.setAdapter(newAdapter);
    }

    public void onBackPressed()
    {
        if(backButtonCount >= 1)
        {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show();
            backButtonCount++;
        }
    }


    public List<Recipe> filterFunction (List<Recipe> allRecipeName,
                                        String searchText,int classOption,
                                        int originOption, int categoryOption) {

        // This method takes a recipe list, a search string and three
        // integer values representing the three types of recipe info
        // and outputs a filtered recipe list.
        // The integer options are in the following order:
        // Class {"Any","Beef", "Chicken", "Seafood", "Vegie"};
        // origin{"Any","Italian","Chinese","Midle Eastern","Indian","American"};
        // category{"Any","Starter","Main Dish","Desert","Drink","Sauce","Salad"};


        // DEFINITIONS

        // Define string arrays to decode integer listings:
        String[] recipeClass = {"Any", "Beef", "Chicken",
                "Seafood", "Vegie"};
        String[] recipeOrigin = {"Any", "Italian", "Chinese",
                "Midle Eastern", "Indian", "American"};
        String[] recipeCategory = {"Any", "Starter", "Main Dish",
                "Desert", "Drink", "Sauce", "Salad"};

        // Deactivate showlist object since not needed in dev test method:
        // List<Recipe> showList = new LinkedList<>();


        // Sum representing a recipe's search score
        //
        // +1 for every hit on a recipe class, origin or category
        //
        // +1 for every word of a hit on an AND'd group of words
        // as long as all the words have successful hits.
        //
        // Note that a word separated by spaces ends up being in its own
        // AND group, so a hit on it will equal the 'group total' of 1.
        //
        // This lets the OR condition be treated naturally in the algorithm.
        //
        // +1 for every hit on a NOT word successfully not in the ingredients.
        //
        // The score then serves as the key to put the recipe in the right list
        // of the LinkedHashMap that will store the search result recipes
        int recipeScore = 0;

        // Best recipeScore found so far
        int bestScore = 0;

        // Count of times a word is found in an ingredient list pass
        int passScore = 0;

        // Temporary sum to calculate an all or nothing score for AND's
        int andScore = 0;

        // Size of sub list of AND words
        int andListSize = 0;

        // Flag to show that word is a NOT word (1st char is '-')
        boolean isNot = false;

        // Temp value to store the all recipe list size
        int allRecipesSize = 0;

        // Temp value to store a recipe's ingredient list size
        int ingsSize = 0;

        // Current search word to be checked
        String searchWord;

        // Current ingredient name to be checked
        String ingWord;

        // Create a hash map based list to store the Recipes in
        // order of search result relevance
        Map<Integer, List<Recipe>> sortedRecipes
                = new LinkedHashMap<Integer, List<Recipe>>();

        // List to store final filtered list
        List<Recipe> filterResult = new LinkedList<>();


        // SEARCH LOGIC IMPLEMENTATION

        // A - PARSER - Turns search string into nested word list

        // 1 - Separate string into word list using space separations
        //     The first level of the list represents OR conditions.
        List<String> wordsList = Arrays.asList(searchText.split(" "));

        // 2 - Turn this list into list of word lists
        //     Each sublist is a string to be separated by plus signs
        //     If no plus signs, it's a list of one word
        //     This second list level represents the AND conditions
        List<List<String>> nestedWordsList = new LinkedList<List<String>>();
        for (int i = 0; i < wordsList.size(); i++) {
            nestedWordsList.add(Arrays.asList(wordsList.get(i).split("\\+")));
        }

        /*
        // DEBUG TO CONSOLE
        System.out.println("Search words:\n");
        System.out.println(wordsList + "\n");
        System.out.println(nestedWordsList + "\n");
        */


        // B - SEARCH  - Begin main loop through the recipes to get the results

        allRecipesSize = allRecipeName.size();

        // For every recipe, give it a search score value.
        for (int i = 0; i < allRecipesSize; i++) {

            // Add one point for every hit of a Class, Origin or Category
            if (recipeClass[classOption].equals(allRecipeName.get(i).getRecipeClass())) {
                recipeScore++;
            }
            if (recipeOrigin[originOption].equals(allRecipeName.get(i).getRecipeOrigin())) {
                recipeScore++;
            }
            if (recipeCategory[categoryOption].equals(allRecipeName.get(i).getRecipeCategory())) {
                recipeScore++;
            }

            // Find the size of this recipe's ingredient list
            ingsSize = allRecipeName.get(i).getIngredients().size();

            // DEBUG TO CONSOLE
            //System.out.println("Recipe " + i + allRecipeName.get(i).getRecipeName() + " size " + ingsSize);

            // Go through all words separated by a space (OR condition)
            for (int k = 0; k < nestedWordsList.size(); k++) {

                // Determine the size of the current AND word sublist
                andListSize = nestedWordsList.get(k).size();

                // Go through the AND word sublist (words separated by a '+')
                for (int m = 0; m < andListSize; m++) {

                    // Isolate the word to be looked for in the ingredients
                    searchWord = nestedWordsList.get(k).get(m);

                    // Case where we hit a NOT word (First char is '-')
                    // Here, we'll add a point if the word is *not* found
                    if (searchWord.substring(0, 1).equals("-")) {

                        // Get rid of the '-' for proper search
                        searchWord = searchWord.substring(1, searchWord.length());

                        // Set NOT flag
                        isNot = true;
                    }

                    // DEBUG TO CONSOLE
                    //System.out.print(searchWord);

                    // Go through the recipe's ingredients
                    for (int j = 0; j < ingsSize; j++) {

                        // Current ingredient word extract
                        ingWord = allRecipeName.get(i).getIngredients().get(j).getIngName();

                        // DEBUG TO CONSOLE
                        //System.out.print(ingWord + " ");

                        // Count the search word hits during an ingredients pass
                        if (searchWord.equals(ingWord)) {

                            // DEBUG TO CONSOLE
                            //System.out.print("XXX");

                            passScore++;
                        }
                    }

                    // DEBUG TO CONSOLE
                    //System.out.print("\n");
                    //System.out.print(" " + passScore + " ");

                    // If we were looking for a NOT word, not finding it is
                    // good. Add a point to the score of this AND grouping.
                    // Otherwise, if we found one or more hits,
                    // Add a point to the score of the AND grouping.
                    if (isNot) {
                        if (passScore == 0) {
                            andScore++;
                        }
                    } else {
                        if (passScore > 0) {
                            andScore++;
                        }
                    }

                    // Reset the NOT word flag and hit count.
                    isNot = false;
                    passScore = 0;

                }

                // DEBUG TO CONSOLE
                //System.out.println(" andScore = " + andScore);

                // If all the words in the AND condition were found, add a
                // point for each word in the AND grouping to the overall
                // search score for this recipe.
                if (andScore == andListSize) {
                    recipeScore = recipeScore + andScore;
                }

                // Reset the AND word grouping score.
                andScore = 0;
            }

            // If the score for the recipe is at least above zero, add it
            // to the recipe results hashmap stored by score as the index key.
            if (recipeScore > 0) {
                if (sortedRecipes.get(recipeScore) == null) {
                    // If it's the first recipe at that score, put the recipe
                    // in a list object and put that object in the hashmap.
                    sortedRecipes.put(recipeScore,
                            new LinkedList<Recipe>(allRecipeName.subList(i, i + 1)));
                } else {
                    // If there's already been a recipe with the found score,
                    // There should already be a list object with at least
                    // one recipe. Add this recipe to that list.
                    sortedRecipes.get(recipeScore).add(allRecipeName.get(i));
                }
            }

            // Keep track of the highest score value to unwind the hashmap
            if (recipeScore > bestScore) {
                bestScore = recipeScore;
            }

            // DEBUG TO CONSOLE
            //System.out.println("recipeScore = " + recipeScore);

            // Reset the recipe score for the next recipe.
            recipeScore = 0;
        }

        // Unwind the hashmap into the results recipe list starting from
        // the best score to the lowest score that is above zero.
        // Recipes with a score of zero just aren't returned in the search.
        for (int n = bestScore; n > 0; n--) {

            // Skip over the score keys that had no Recipes at that value..
            if (sortedRecipes.get(n) != null) {

                // When a populated score key is found, dump the recipes into
                // the result recipe list. At equal scores, the insertion
                // order doesn't really matter for the recipes.
                for (int p = 0; p < sortedRecipes.get(n).size(); p++) {
                    filterResult.add(sortedRecipes.get(n).get(p));
                }
            }
        }

        /*
        // Add result recipe names to the showList string list.
        for (int q = 0; q < filterResult.size(); q++) {
            showList.add(filterResult.get(q).getRecipeName());
        }

        // Pass the string list to displaylist.
        displayList(showList);
        */

        // Return the results list ordered by 'relevance' of search results.
        return filterResult;

    /*
    public List<Recipe> filterFunction (List<Recipe> allRecipeName,String searchText,int classOption, int originOption, int categoryOption){
        //we need this function to take a recipe list, and a search string
        //and output a filtered recipe list
        //we can input some recipe (see line 40-70)
        //and add attributes of recipes to test
        //the integer options are in the following order
        // Class {"Any","Beef", "Chicken", "Seafood", "Vegie"};
        // origin {"Any","Italian", "Chinese", "Midle Eastern", "Indian", "American"};
        // category {"Any","Starter", "Main Dish", "Desert", "Drink", "Sauce", "Salad"};

        showList = new LinkedList<>();
        filterResult= new LinkedList<>();
        //used to store filtered list

        //just for test,should show last 3 item with reversed order
        numOfFilteredRecipe=4;
        for (int i=0;i<numOfFilteredRecipe;i++){
            filterResult.add(allRecipe.get(allRecipe.size()-i-1));
        }


        for(int j=0;j<numOfFilteredRecipe;j++){
            showList.add(filterResult.get(j).getRecipeName());
        }


        //please implement



        displayList(showList);//pass a string list to displaylist
        return filterResult;
    }
    */

        // END OF filterFunction METHOD ======================================
    }

    public void writeBtn() {
        String str=write_jason();
        SharedPreferences savedV = getSharedPreferences( PREFS_NAME, 0);
        SharedPreferences.Editor editor = savedV.edit();
        editor.putString("AllRecipe", str);
        editor.apply();

    }
    public void readBtn() {
        SharedPreferences savedV = getSharedPreferences( PREFS_NAME, 0);
        savedString = savedV.getString("AllRecipe","");
        System.out.println("read+"+savedString);
        read_jason(savedString);

    }

    public String write_jason() {
        try {
            JSONObject data = new JSONObject();
            JSONArray recipes = new JSONArray();
            data.put("recipes", recipes);
            for(int c = 0; c< allRecipe.size(); c++) {
                JSONObject jasonRecipe = new JSONObject();
                jasonRecipe.put("RecipeName", allRecipe.get(c).getRecipeName());
                jasonRecipe.put("Class", allRecipe.get(c).getRecipeClass());
                jasonRecipe.put("Category", allRecipe.get(c).getRecipeCategory());
                jasonRecipe.put("Origin", allRecipe.get(c).getRecipeOrigin());
                JSONArray newIngredients = new JSONArray();
                int i = 0;
                while (i < allRecipe.get(c).getIngredients().size()) {
                    JSONObject ingred = new JSONObject();
                    ingred.put("name", allRecipe.get(c).getIngredients().get(i).getIngName());
                    ingred.put("quantity", allRecipe.get(c).getIngredients().get(i).getIngQuantity());
                    ingred.put("unit", allRecipe.get(c).getIngredients().get(i).getIngUnits());
                    newIngredients.put(ingred);
                    i++;
                }
                jasonRecipe.put("Ingredients", newIngredients);
                JSONArray stps = new JSONArray();
                int a = 0;
                while (a < allRecipe.get(c).getSteps().size()) {
                    JSONObject sp = new JSONObject();
                    sp.put("step", allRecipe.get(c).getSteps().get(a));
                    stps.put(sp);

                    a++;
                }
                jasonRecipe.put("steps", stps);
                recipes.put(jasonRecipe);

            }
            return data.toString();
        } catch(JSONException e) {
            e.printStackTrace();
        }


        return null;
    }
    public void read_jason(String json){
        try{
            if(!json.isEmpty()){
                JSONObject jsob= new JSONObject(json);
                JSONArray recipes;
                recipes=jsob.getJSONArray("recipes");
                for (int i =0; i <recipes.length(); i++) {
                    Recipe recpe = new Recipe();
                    List ingredient_list= new LinkedList<Ingredient>();
                    List steps_list= new LinkedList<String>();
                    JSONObject recp = recipes.getJSONObject(i);
                    String name = recp.getString("RecipeName");
                    showList.add(name);
                    recpe.setRecipeName(name);
                    String classs = recp.getString("Class");
                    recpe.setRecipeClass(classs);
                    String category = recp.getString("Category");
                    recpe.setRecipeCategory(category);
                    String Origin = recp.getString("Origin");
                    recpe.setRecipeOrigin(Origin);
                    JSONArray ject = recp.getJSONArray("Ingredients");
                    for (int a = 0; a < ject.length(); a++) {
                        JSONObject inget = ject.getJSONObject(a);
                        String names = inget.getString("name");
                        float quantity = inget.getLong("quantity");
                        String unit = inget.getString("unit");
                        Ingredient the_ingredient= new Ingredient();
                        the_ingredient.setIngName(names);
                        the_ingredient.setIngQuantity(quantity);
                        switch (unit) {
                            case "None":
                                the_ingredient.setIngUnits(Ingredient.Measure.none);
                                break;
                            case "cup":
                                the_ingredient.setIngUnits(Ingredient.Measure.cup);
                                break;
                            case "tea_spoon":
                                the_ingredient.setIngUnits(Ingredient.Measure.tea_spoon);
                                break;
                            case "table_spoon":
                                the_ingredient.setIngUnits(Ingredient.Measure.table_spoon);
                                break;
                            case "ounce":
                                the_ingredient.setIngUnits(Ingredient.Measure.ounce);
                                break;
                            case "kg":
                                the_ingredient.setIngUnits(Ingredient.Measure.kg);
                                break;
                            case "g":
                                the_ingredient.setIngUnits(Ingredient.Measure.g);
                                break;
                            case "piece":
                                the_ingredient.setIngUnits(Ingredient.Measure.piece);
                        }
                        ingredient_list.add(the_ingredient);
                    }
                    JSONArray sps = recp.getJSONArray("steps");
                    for (int b = 0; b < sps.length(); b++) {
                        JSONObject step = sps.getJSONObject(b);
                        String the_step=step.getString("step");
                        steps_list.add(the_step);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    @Override
    protected void onStop() {
        super.onStop();
        writeBtn();
    }

    }

