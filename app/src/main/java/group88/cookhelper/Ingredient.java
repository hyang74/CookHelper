package group88.cookhelper;


/**
 * Created by YANG on 2016-11-27.
 */

public class Ingredient{

    public enum Measure  {none,cup, tea_spoon, table_spoon, ounce, kg, g, piece};
    private String ingName;
    private float ingQuantity;
    private Measure ingUnits;
    Ingredient(){
        this.ingName=" ";
        this.ingQuantity=0;
        this.ingUnits = Measure.none;
    }
    Ingredient(String newIngName, float newIngQuantity,Measure newIngUnits){
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