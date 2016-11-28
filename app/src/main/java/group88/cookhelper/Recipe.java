package group88.cookhelper;

/**
 * Created by YANG on 2016-11-24.
 */
import org.json.JSONObject;

import java.util.*;


public class Recipe {

    public String Classs;
    public String Origin;
    public String Category;
    public enum Measure  {ANY,CUP, TEA_SPOON, TABLE_SPOON, OUNCE, KILO_GRAM, GRAM, PIECE};
    public static List<Recipe> recipes;
    private String recipeName;
    private String recipeClasss;
    private String recipeOrigin;
    private String  recipeCategory;

    public List<Ingredient>  ingredients;
    public List<String> steps;
    private List<String> b;


    Recipe(){
        this.recipeName=" ";
        this.recipeClasss="ANY";
        this.recipeOrigin="ANY";
        this.recipeCategory="ANY";
        this.ingredients= new LinkedList<>();
        this.steps = new LinkedList<String>();
        this.b =new LinkedList<String>();
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
    public void addIngredients(String newIngName, float newIngQuantity, Measure newIngUnits) {
        Ingredient newIng= new Ingredient(newIngName,newIngQuantity,newIngUnits);
        ingredients.add(newIng);
    }
    public void deleteIngredients(){ingredients.clear();}

    public void setB(List<String> br){ b = br;}
    public List<Ingredient> getIngredients(){return ingredients;}
    public List<String> getIngredientList() {
        Iterator<Ingredient> ingItr = ingredients.iterator();
        while (ingItr.hasNext()) {
            Ingredient ingr = ingItr.next();
            b.add(ingr.getIngName() + " X " + ingr.getIngQuantity() + " " + ingr.getIngUnits());
        }
        return b;
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
