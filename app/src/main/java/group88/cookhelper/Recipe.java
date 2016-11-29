package group88.cookhelper;

/**
 * Created by YANG on 2016-11-24.
 */
import org.json.JSONObject;

import java.io.Serializable;
import java.util.*;


public class Recipe implements Serializable {

    private static final long serialVersionUID =1L;
    public static List<Recipe> recipes;
    private String recipeName;
    private String recipeClasss;
    private String recipeOrigin;
    private String  recipeCategory;

    public List<Ingredient>  ingredients;
    public List<String> steps;
    private List<String> ingredientsStringList;


    Recipe(){
        this.recipeName=" ";
        this.recipeClasss="Any";
        this.recipeOrigin="Any";
        this.recipeCategory="Any";
        this.ingredients= new LinkedList<>();
        this.steps = new LinkedList<String>();
        this.ingredientsStringList =new LinkedList<String>();
}




    public String getRecipeName() { return recipeName; }
    public void setRecipeName(String name) { recipeName = name; }

    public String getRecipeClass() {
        String a="";
        a += recipeClasss; return a;}
    public void setRecipeClass(String c) { recipeClasss = c; }

    public String getRecipeOrigin() {
        String a="";
        a += recipeOrigin;
        return a;}
    public void setRecipeOrigin(String origin) { recipeOrigin = origin; }

    public String getRecipeCategory() {
        String a="";
        a += recipeCategory;
        return a; }
    public void setRecipeCategory(String category) { recipeCategory = category; }



    public void setIngredients(List<Ingredient> ing) {ingredients=ing;}
    public void addIngredients(String newIngName, float newIngQuantity, Ingredient.Measure newIngUnits) {
        Ingredient newIng= new Ingredient(newIngName,newIngQuantity,newIngUnits);
        ingredients.add(newIng);
    }
    public List<Ingredient> getIngredients(){return ingredients;}
    public void deleteIngredients(){ingredients.clear();}

    public void setIngredientsStringList(List<String> br){ ingredientsStringList = br;}
    public List<String> getIngredientsStringListList() {
        Iterator<Ingredient> ingItr = ingredients.iterator();
        while (ingItr.hasNext()) {
            Ingredient ingr = ingItr.next();
            ingredientsStringList.add(ingr.getIngName() + " X " + ingr.getIngQuantity() + " " + ingr.getIngUnits());
        }
        return ingredientsStringList;
    }

    public List<String> getSteps() { return steps; }
    public void setSteps(List<String> dr) { steps = dr; }
    public void addSteps(String stp){
        steps.add(stp);
    }
    public void deleteSteps(){steps.clear();}


    public String toString(){
        String str;
        str = "Recipe Name: " + recipeName + "\n";
        str += recipeOrigin + " " + recipeCategory + " " + recipeClasss + " recipe \n";
        str += "Ingredients: \n";
        Iterator<Ingredient> ingItr = ingredients.iterator();
        while (ingItr.hasNext()){
            Ingredient ingr = ingItr.next();
            str += ingr.getIngName() + " X " + ingr.getIngQuantity() + " " + ingr.getIngUnits() + "\n";
        }
        str += "Steps: \n";
        Iterator<String> stepsItr = steps.iterator();
        while (stepsItr.hasNext()){
             String thisStep = stepsItr.next();
            str += thisStep + "\n";
        }
        return str;
    }
}
