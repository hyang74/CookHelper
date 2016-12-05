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
            Steak.setRecipeName("0 Steak");
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
            VegiePho.setRecipeName("1 Veggie Pho");
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
            GrilledChicken.setRecipeName("2 Grilled Chicken");
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
            beefPho.setRecipeName("3 Beef Pho");
            beefPho.addIngredients(new Ingredient("salt",1,Ingredient.Measure.tea_spoon));
            allRecipe.add(beefPho);


            Recipe beefStew = new Recipe();
            beefStew.setRecipeName("4 Beef Stew");
            allRecipe.add(beefStew);

            Recipe beefAndVegiePizza = new Recipe();
            beefAndVegiePizza.setRecipeName("5 Beef and Vegie Pizza");
            allRecipe.add(beefAndVegiePizza);

            Recipe iceCream = new Recipe();
            iceCream.setRecipeName("6 Ice Cream");
            allRecipe.add(iceCream);


            //System.out.println("Printing dummy list:\n");
            //System.out.println(allRecipe.toString());

            System.out.println("Defining search string:");

            String searchString = "honey tofu onion salt+-lime";
            int classOption = 0;
            int originOption = 0;
            int categoryOption = 0;

            System.out.println(searchString + "\n");

            System.out.println("Running filter function...\n");

            allRecipe = this.filterFunction(allRecipe, searchString,
                    classOption, originOption, categoryOption);

            System.out.println("Printing filtered list:\n");

            System.out.println(allRecipe.toString());
        }
    }

    public List<Recipe> filterFunction (List<Recipe> allRecipeName,
                                        String searchText,int classOption,
                                        int originOption, int categoryOption){

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
                = new LinkedHashMap< Integer, List<Recipe>>();

        // List to store final filtered list
        List<Recipe> filterResult= new LinkedList<>();


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
        for (int i = 0; i < wordsList.size(); i++){
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
        for (int i = 0; i < allRecipesSize; i++){

            // Add one point for every hit of a Class, Origin or Category
            if(recipeClass[classOption].equals(allRecipeName.get(i).getRecipeClass())){
                recipeScore++;
            }
            if(recipeOrigin[originOption].equals(allRecipeName.get(i).getRecipeOrigin())){
                recipeScore++;
            }
            if(recipeCategory[categoryOption].equals(allRecipeName.get(i).getRecipeCategory())){
                recipeScore++;
            }

            // Find the size of this recipe's ingredient list
            ingsSize = allRecipeName.get(i).getIngredients().size();

            // DEBUG TO CONSOLE
            //System.out.println("Recipe " + i + allRecipeName.get(i).getRecipeName() + " size " + ingsSize);

            // Go through all words separated by a space (OR condition)
            for (int k = 0; k < nestedWordsList.size(); k++){

                // Determine the size of the current AND word sublist
                andListSize = nestedWordsList.get(k).size();

                // Go through the AND word sublist (words separated by a '+')
                for (int m = 0; m < andListSize; m++){

                    // Isolate the word to be looked for in the ingredients
                    searchWord = nestedWordsList.get(k).get(m);

                    // Case where we hit a NOT word (First char is '-')
                    // Here, we'll add a point if the word is *not* found
                    if (searchWord.substring(0,1).equals("-")) {

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
                    if(isNot){
                        if(passScore == 0){
                            andScore++;
                        }
                    } else {
                        if(passScore > 0){
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
                if(andScore == andListSize){
                    recipeScore = recipeScore + andScore;
                }

                // Reset the AND word grouping score.
                andScore = 0;
            }

            // If the score for the recipe is at least above zero, add it
            // to the recipe results hashmap stored by score as the index key.
            if(recipeScore > 0){
                if(sortedRecipes.get(recipeScore) == null){
                    // If it's the first recipe at that score, put the recipe
                    // in a list object and put that object in the hashmap.
                    sortedRecipes.put(recipeScore,
                            new LinkedList<Recipe>(allRecipeName.subList(i,i+1)));
                } else {
                    // If there's already been a recipe with the found score,
                    // There should already be a list object with at least
                    // one recipe. Add this recipe to that list.
                    sortedRecipes.get(recipeScore).add(allRecipeName.get(i));
                }
            }

            // Keep track of the highest score value to unwind the hashmap
            if(recipeScore > bestScore){
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
        for (int n = bestScore; n > 0; n--){

            // Skip over the score keys that had no Recipes at that value..
            if(sortedRecipes.get(n) != null) {

                // When a populated score key is found, dump the recipes into
                // the result recipe list. At equal scores, the insertion
                // order doesn't really matter for the recipes.
                for (int p = 0; p < sortedRecipes.get(n).size(); p++) {
                    filterResult.add(sortedRecipes.get(n).get(p));
                }
            }
        }

        // Deactivate showList related method not needed in test:
        /*
        for(int q = 0; q < filterResult.size(); q++){
            showList.add(filterResult.get(q).getRecipeName());
        }

        // Deactivate showList related method not needed in test:
        // displayList(showList);
        // pass a string list to displaylist
    */

        // Return the results list ordered by 'relevance' of search results.
        return filterResult;
    }
}