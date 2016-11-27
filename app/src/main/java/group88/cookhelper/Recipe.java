package group88.cookhelper;

/**
 * Created by YANG on 2016-11-24.
 */
import java.util.*;


public class Recipe {
    public enum Class {BEAF, CHICKEN, SEAFOOD, VEGIE};
    public enum Origin {ITALIAN, CHINESE, MIDLE_EASTERN, INDIAN, AMERICAN};
    public enum Category {STARTER, MAIN_DISH, DESERT, DRINK, SAUCE, SALAD};
    public enum Measure {CUP, TEA_SPOON, TABLE_SPOON, OUNCE, KILO_GRAM, GRAM, PIECE};
    public static class Ingredient{
        private String ingName;
        private float ingQuantity;
        private Measure ingUnits;
        Ingredient(String newIngName, float newIngQuantity, Measure newIngUnits){
            this.ingName=newIngName;
            this.ingQuantity=newIngQuantity;
            this.ingUnits=newIngUnits;
        }
        public String getIngName() { return ingName; }
        public void setIngName(String name) {ingName = name;}
        public float getIngQuantity() { return ingQuantity; }
        public void setIngQuantity(float q) { ingQuantity = q; }
        public Measure getIngUnits() { return ingUnits; }
        public void setIngUnits(Measure u) { ingUnits = u; }
    }
    private String recipeName;
    private Class recipeClass;
    private Origin recipeOrigin;
    private Category recipeCategory;
    private List<Ingredient>  ingredients;
    private List<String> steps;
    public String getRecipeName() { return recipeName; }
    public void setRecipeName(String name) { recipeName = name; }
    public Class getRecipeClass() { return recipeClass; }
    public void setRecipeClass(Class c) { recipeClass = c; }
    public Origin getRecipeOrigin() { return recipeOrigin; }
    public void setRecipeOrigin(Origin origin) { recipeOrigin = origin; }
    public Category getRecipeCategory() { return recipeCategory; }
    public void setRecipeCategory(Category category) { recipeCategory = category; }
    public List<Ingredient> getIngredients() { return ingredients; }
    public void addIngredients(String newIngName, float newIngQuantity, Measure newIngUnits) {
        Ingredient newIng= new Ingredient(newIngName,newIngQuantity,newIngUnits);
        ingredients.add(newIng);
    }
    public void deleteIngredients(){ingredients.clear();}
    public List<String> getSteps() { return steps; }
    public void setSteps(List<String> dr) { steps = dr; }
    public void deleteSteps(){steps.clear();}
    public String toString(){
        String str;
        str = "Recipe Name: " + recipeName + "\n";
        str += recipeOrigin + " " + recipeCategory + " " + recipeClass + " recipe \n";
        str += "Ingredients: \n";
        Iterator<Ingredient> ingItr = ingredients.iterator();
        while (ingItr.hasNext()){
            Ingredient ingr = ingItr.next();
            str += ingr.ingName + " " + ingr.ingQuantity + " " + ingr.ingUnits + "\n";
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
