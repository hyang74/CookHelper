package group88.cookhelper;

/**
 * Created by YANG on 2016-11-24.
 */
import java.util.*;


public class Recipe {

    public enum Classs {ANY,BEEF, CHICKEN, SEAFOOD, VEGIE};
    public enum Origin {ANY,ITALIAN, CHINESE, MIDLE_EASTERN, INDIAN, AMERICAN};
    public enum Category {ANY,STARTER, MAIN_DISH, DESERT, DRINK, SAUCE, SALAD};
    public enum Measure {ANY,CUP, TEA_SPOON, TABLE_SPOON, OUNCE, KILO_GRAM, GRAM, PIECE};

    private String recipeName;
    private Classs recipeClasss;
    private Origin recipeOrigin;
    private Category recipeCategory;

    private List<Ingredient>  ingredients;
    private List<String> steps;
    private List<String> b;


    Recipe(){
        this.recipeName=" ";
        this.recipeClasss=Classs.ANY;
        this.recipeOrigin=Origin.ANY;
        this.recipeCategory=Category.ANY;
        this.ingredients= new LinkedList<>();
        this.steps = new LinkedList<String>();
        this.b =new LinkedList<String>();
}


    public class Ingredient{
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
        public String getIngUnits() {
            String a="";
            a += ingUnits;
            return a;}
        public void setIngUnits(Measure u) { ingUnits = u; }
    }

    public String getRecipeName() { return recipeName; }
    public void setRecipeName(String name) { recipeName = name; }

    public String getRecipeClass() {
        String a="";
        a += recipeClasss; return a;}
    public void setRecipeClass(Classs c) { recipeClasss = c; }

    public String getRecipeOrigin() {
        String a="";
        a += recipeOrigin;
        return a;}
    public void setRecipeOrigin(Origin origin) { recipeOrigin = origin; }

    public String getRecipeCategory() {
        String a="";
        a += recipeCategory;
        return a; }
    public void setRecipeCategory(Category category) { recipeCategory = category; }



    public void setIngredients(List<Ingredient> ing) {ingredients=ing;}
    public void addIngredients(String newIngName, float newIngQuantity, Measure newIngUnits) {
        Ingredient newIng= new Ingredient(newIngName,newIngQuantity,newIngUnits);
        ingredients.add(newIng);
    }
    public void deleteIngredients(){ingredients.clear();}

    public void setB(List<String> br){ b = br;}
    public List<String> getIngredientList() {
        Iterator<Ingredient> ingItr = ingredients.iterator();
        while (ingItr.hasNext()) {
            Ingredient ingr = ingItr.next();
            b.add(ingr.ingName + " X " + ingr.ingQuantity + " " + ingr.ingUnits);
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
            str += ingr.ingName + " X " + ingr.ingQuantity + " " + ingr.ingUnits + "\n";
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
