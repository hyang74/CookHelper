package group88.cookhelper;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SearchTest {
    @Test
    public void searchTester() throws Exception {

        System.out.println("Testing Search Function...\n");

        // Create a dummy list of recipes for testing
        System.out.println("Creating recipe dummy list...\n");

        List<Recipe> allRecipe = new LinkedList<>();

        // Recipe Class, Origin and Category orders:
        // {"Any","Beef", "Chicken","Pork","Seafood", "Vegie","Mixed"};
        // {"Any","Italian", "Chinese", "Midle Eastern", "Indian", "American"};
        // {"Any","Starter", "Main Dish", "Desert", "Drink", "Sauce", "Salad"};

        if (allRecipe.isEmpty()) {



            Recipe Steak = new Recipe();
            Steak.setRecipeName("1 Steak");
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
            VegiePho.setRecipeName("2 Veggie Pho");
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
            GrilledChicken.setRecipeName("3 Grilled Chicken");
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


            Recipe beefPho = new Recipe();
            beefPho.setRecipeName("4 Beef Pho");
            allRecipe.add(beefPho);


            Recipe beefStew = new Recipe();
            beefStew.setRecipeName("5 Beef Stew");
            allRecipe.add(beefStew);

            Recipe beefAndVegiePizza = new Recipe();
            beefAndVegiePizza.setRecipeName("6 Beef and Vegie Pizza");
            allRecipe.add(beefAndVegiePizza);

            Recipe iceCream = new Recipe();
            iceCream.setRecipeName("7 Ice Cream");
            allRecipe.add(iceCream);


            System.out.println("Printing dummy list:\n");
            System.out.println(allRecipe.toString());

            System.out.println("Defining search string:\n");

            String searchString = "onion salt+-lime";
            int classOption = 0;
            int originOption = 0;
            int categoryOption = 0;

            System.out.println(searchString + "\n");

            System.out.println("Running filter function...\n");

            allRecipe = this.filterFunction(allRecipe, searchString,
                    classOption, originOption, categoryOption);

            System.out.println("\nFiltering...\n");

            System.out.println("Printing filtered list:\n");

            System.out.println(allRecipe.toString());
        }
    }

    public List<Recipe> filterFunction (List<Recipe> allRecipeName,
                                        String searchText,int classOption,
                                        int originOption, int categoryOption){

        // we need this function to take a recipe list, and a search string
        // and output a filtered recipe list
        // we can input some recipe (see line 40-70)
        // and add attributes of recipes to test
        // the integer options are in the following order
        // Class {"Any","Beef", "Chicken", "Seafood", "Vegie"};
        // origin{"Any","Italian","Chinese","Midle Eastern","Indian","American"};
        // category{"Any","Starter","Main Dish","Desert","Drink","Sauce","Salad"};


        // Define string arrays to decode integer listings:
        String[] recipeClass = {"Any", "Beef", "Chicken",
                "Seafood", "Vegie"};
        String[] recipeOrigin = {"Any", "Italian", "Chinese",
                "Midle Eastern", "Indian", "American"};
        String[] recipeCategory = {"Any", "Starter", "Main Dish",
                "Desert", "Drink", "Sauce", "Salad"};

        // Deactivate showlist object since not needed in dev test method:
        // List<Recipe> showList = new LinkedList<>();

        // Create a hash map based list to store the Recipes in
        // order of search result relevance
        Map<Integer, List<Recipe>> sortedRecipes
                = new LinkedHashMap< Integer, List<Recipe>>();

        // Create list to store temp filtered list:
        List<Recipe> filterTemp = new LinkedList<>();

        // Create list to store final filtered list:
        List<Recipe> filterResult= new LinkedList<>();

        // Search string parser - turns search string into nested list.

        // 1 - Separate string into list separated by spaces
        List<String> wordsList = Arrays.asList(searchText.split(" "));

        // 2 - Turn this list into list of lists
        //     Each sublist is a string to be separated by plus signs
        //     If no plus signs, it's a list of one word
        List<List<String>> nestedWordsList = new LinkedList<List<String>>();
        for (int i = 0; i < wordsList.size(); i++){
            nestedWordsList.add(Arrays.asList(wordsList.get(i).split("\\+")));
        }

        System.out.println("Search words:\n");
        System.out.println(wordsList + "\n");
        System.out.println(nestedWordsList + "\n");

        // The NOT character "-" to be searched for a each element..



        int allRecipesSize = allRecipeName.size();
        for (int i = 0; i < allRecipesSize; i++){
            filterTemp.add(allRecipeName.get(i));
        }

        int filterTempSize = filterTemp.size();
        for (int i = 0; i < filterTempSize; i++){
            filterResult.add(allRecipeName.get(i));
        }





    // Deactivate initial filtered list dummy result generator:
    /*
        //just for test,should show last 3 item with reversed order
        int numOfFilteredRecipe = 6;
        for (int i=0;i<numOfFilteredRecipe;i++){
            filterResult.add(allRecipeName.get(allRecipeName.size()-i-1));
        }

        for(int j=0;j<numOfFilteredRecipe;j++){
            showList.add(filterResult.get(j).getRecipeName());
        }
    */

        // Deactivate showList related method not needed in test:
        // displayList(showList);
        // pass a string list to displaylist


        return filterResult;
    }
}