package group88.cookhelper;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SearchTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
        System.out.println("Testing Search Function...\n");

        // Create a dummy list of recipes for testing

        List<Recipe> allRecipe = new LinkedList<>();

        // Recipe Class, Origin and Category orders:
        // {"Any","Beef", "Chicken","Pork","Seafood", "Vegie","Mixed"};
        // {"Any","Italian", "Chinese", "Midle Eastern", "Indian", "American"};
        // {"Any","Starter", "Main Dish", "Desert", "Drink", "Sauce", "Salad"};

        if (allRecipe.isEmpty()) {



            Recipe steak = new Recipe();
            steak.setRecipeName("Steak");
            steak.setRecipeClass("Beef");
            steak.setRecipeOrigin("American");
            steak.setRecipeCategory("Main Dish");
            steak.addIngredients(new Ingredient("balsamic vinegar", (float) 0.5, Ingredient.Measure.cup));
            steak.addIngredients(new Ingredient("soy sauce", (float) 0.25, Ingredient.Measure.cup));
            steak.addIngredients(new Ingredient("minced garlic", 3, Ingredient.Measure.table_spoon));
            steak.addIngredients(new Ingredient("honey", 2, Ingredient.Measure.table_spoon));
            steak.addIngredients(new Ingredient("olive oil", 2, Ingredient.Measure.table_spoon));
            steak.addIngredients(new Ingredient("ground black pepper", 2, Ingredient.Measure.tea_spoon));
            steak.addIngredients(new Ingredient("Worcestershire sauce", 1, Ingredient.Measure.tea_spoon));
            steak.addIngredients(new Ingredient("onion powder", 2, Ingredient.Measure.tea_spoon));
            steak.addIngredients(new Ingredient("salt", (float) 0.5, Ingredient.Measure.tea_spoon));
            steak.addIngredients(new Ingredient("liquid smoke flavoring", (float) 0.5, Ingredient.Measure.tea_spoon));
            steak.addIngredients(new Ingredient("rib-eye steaks", (float) 2.5, Ingredient.Measure.pound));
            steak.addSteps("Mix vinegar, soy sauce, garlic, honey, olive oil, ground black pepper, etc (all" +
                    "the ingredients) in a bowl");
            steak.addSteps("Place steak in glass dish with the marinade and rub liquid onto the meat.");
            steak.addSteps("Refrigerate for 1 -2 days");
            steak.addSteps("Preheat grill to medium- high");
            steak.addSteps("Lightly oil grill.");
            steak.addSteps("Grill steaks 7 mins per side or desired doneness.");
            steak.addSteps("Serve and enjoy!");
            allRecipe.add(steak);

            Recipe veggiePho = new Recipe();
            veggiePho.setRecipeName("Vegie Pho");
            allRecipe.add(veggiePho);

            Recipe beefPho = new Recipe();
            beefPho.setRecipeName("Beef Pho");
            allRecipe.add(beefPho);

            Recipe grilledChicken = new Recipe();
            grilledChicken.setRecipeName("Grilled Chicken");
            allRecipe.add(grilledChicken);

            Recipe beefStew = new Recipe();
            beefStew.setRecipeName("Beef Stew");
            allRecipe.add(beefStew);

            Recipe beefAndVegiePizza = new Recipe();
            beefAndVegiePizza.setRecipeName("Beef and Vegie Pizza");
            allRecipe.add(beefAndVegiePizza);

            Recipe iceCream = new Recipe();
            iceCream.setRecipeName("Ice Cream");
            allRecipe.add(iceCream);

            System.out.println(allRecipe.toString());

            String searchString = "Steak";
            int classOption = 0;
            int originOption = 0;
            int categoryOption = 0;


            allRecipe = this.filterFunction(allRecipe, searchString,
                    classOption, originOption, categoryOption);

            System.out.println("\nFiltering...\n");

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


        // List<Recipe> showList = new LinkedList<>();
        List<Recipe> filterResult= new LinkedList<>();
        //used to store filtered list



        //just for test,should show last 3 item with reversed order
        int numOfFilteredRecipe = 6;
        for (int i=0;i<numOfFilteredRecipe;i++){
            filterResult.add(allRecipeName.get(allRecipeName.size()-i-1));
        }

    /*
        for(int j=0;j<numOfFilteredRecipe;j++){
            showList.add(filterResult.get(j).getRecipeName());
        }
    */

        String[] recipeClass = {"Any", "Beef", "Chicken",
                "Seafood", "Vegie"};
        String[] recipeOrigin = {"Any", "Italian", "Chinese",
                "Midle Eastern", "Indian", "American"};
        String[] recipeCategory = {"Any", "Starter", "Main Dish",
                "Desert", "Drink", "Sauce", "Salad"};


        // displayList(showList);//pass a string list to displaylist
        return filterResult;
    }
}