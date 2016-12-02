package group88.cookhelper;


import java.io.Serializable;

/**
 * Created by YANG on 2016-11-27.
 */

public class Ingredient implements Serializable{

    private static final long serialVersionUID =1L;
    public enum Measure  {none,cup, tea_spoon, table_spoon, ounce, kg, g, piece,pound};
    private String ingName;
    private float ingQuantity;
    private Measure ingUnits;
    Ingredient(){
        this.ingName="";
        this.ingQuantity=0;
        this.ingUnits = Measure.none;
    }
    Ingredient(String name, float quan, Measure unit){
        this.ingName=name;
        this.ingQuantity=quan;
        this.ingUnits = unit;
    }
    public String getIngName() { return ingName; }
    public void setIngName(String name) {ingName = name;}
    public String getIngQuantity() { return Float.toString(ingQuantity); }
    public void setIngQuantity(float q) { ingQuantity = q; }
    public String getIngUnits() {
        String a="";
        if(ingUnits==Measure.none){
            return a;
        }
        else{
            a += ingUnits;}
        return a;
    }
    public void setIngUnits(Measure u) { ingUnits = u; }
    public String toString(){
        String str="";
        str += this.getIngName() + " X " + this.getIngQuantity() + " " + this.getIngUnits() + "\n";
        return str;
    }
}