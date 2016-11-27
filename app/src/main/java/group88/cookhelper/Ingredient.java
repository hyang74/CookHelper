package group88.cookhelper;

/**
 * Created by YANG on 2016-11-27.
 */

public class Ingredient{
    private String ingName;
    private float ingQuantity;
    private Recipe.Measure ingUnits;
    Ingredient(String newIngName, float newIngQuantity,Recipe.Measure newIngUnits){
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
    public void setIngUnits(Recipe.Measure u) { ingUnits = u; }
}