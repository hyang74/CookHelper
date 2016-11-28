package group88.cookhelper;

/**
 * Created by YANG on 2016-11-13.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;

public class editRecipe extends Activity {

    public String[] spinnerMeasure = {"None","cup", "tea_spoon", "table_spoon", "ounce", "kg", "g", "piece"};
    private String[] spinnerAddClass = {"Any","Beef", "Chicken", "Seafood", "Vegie"};
    private String[] spinnerAddOrigin = {"Any","Italian", "Chinese", "Midle Eastern", "Indian", "American"};
    private String[] spinnerAddCategory = {"Any","Starter", "Main Dish", "Desert", "Drink", "Sauce", "Salad"};
    private List<Ingredient> newIngredientList = new LinkedList<>();
    private List<String> newStepList = new LinkedList<>();
    private List<String> ingList = new LinkedList<>();
    private List<String> stepList = new LinkedList<>();



    EditText mEditText;
    Button mClearText;
    Button mSave;
    Button mAddIng;
    Button mAddStep;
    ListView editIngList;
    ListView editStepList;
    EditText mEditName;
    int stepCounter = 1;
    AlertDialog.Builder dialogBuilder;
    String newStep;
    Ingredient newIng = new Ingredient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_recipe);


        mEditText=(EditText) findViewById(R.id.EditName);
        mAddIng = (Button) findViewById(R.id.addIng);
        mAddIng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingredientDialog();
            }
        });
        mAddStep =(Button) findViewById(R.id.addStep);
        mAddStep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stepDialog();
            }
        });
        Spinner aClass = (Spinner) findViewById(R.id.Addclass);
        Spinner aOrigin = (Spinner) findViewById(R.id.Addorigin);
        Spinner aCategory = (Spinner) findViewById(R.id.Addcategory);
        ArrayAdapter<String> adapterAClass = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerAddClass);
        aClass.setAdapter(adapterAClass);
        aClass.setSelection(0);


        ArrayAdapter<String> adapteraOrigin = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerAddOrigin);
        aOrigin.setAdapter(adapteraOrigin);
        aOrigin.setSelection(0);

        ArrayAdapter<String> adapteraCategory = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerAddCategory);
        aCategory.setAdapter(adapteraCategory);
        aCategory.setSelection(0);

        editIngList =(ListView) findViewById(R.id.edit_ing_list);
        ArrayAdapter adapterIng = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ingList);
        editIngList.setAdapter(adapterIng);

        editStepList = (ListView) findViewById(R.id.edit_step_list);
        ArrayAdapter adapterStep = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stepList);
        editStepList.setAdapter(adapterStep);



        // Do not change, this block is used to clear text on clicking the X botton

        mClearText = (Button) findViewById(R.id.clearText);
        mSave = (Button) findViewById(R.id.saveEdit);

        //initially clear button is invisible
        mClearText.setVisibility(View.INVISIBLE);

        //clear button visibility on text change
        mEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {
                //do nothing
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //do nothing
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    mClearText.setVisibility(View.VISIBLE);
                } else {
                    mClearText.setVisibility(View.GONE);
                }
            }
        });
    }

    public void clearName(View view) {
        mEditText.setText("");
        mClearText.setVisibility(View.GONE);
    }


    public void saveEditRecipe(View view) {
        Recipe newRecipe = new Recipe();
        newRecipe.setRecipeName(mEditName.getText().toString());
        newRecipe.setRecipeClass(aClass.getSelectedItem().toString());
        newRecipe.setRecipeOrigin(aOrigin.getSelectedItem().toString());
        newRecipe.setRecipeCategory(aCategory.getSelectedItem().toString());
        Boolean if_added =Recipe.recipes.add(newRecipe);

    public void ingredientDialog(){
        final EditText nameInput ;
        final EditText quantityInput;
        final Spinner unitSelection;
        Button btnok;
        Button btncancel;
        final Dialog dialogCustom = new Dialog(this);

        dialogCustom.setContentView(R.layout.dialog);
        dialogCustom.show();
        dialogCustom.setTitle("Add your ingredient:");
        nameInput = (EditText) dialogCustom.findViewById(R.id.editIngName);
        quantityInput = (EditText) dialogCustom.findViewById(R.id.editIngQ);
        unitSelection =(Spinner) dialogCustom.findViewById(R.id.editIngU);
        ArrayAdapter<String> adapterMeasure = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerMeasure);
        unitSelection.setAdapter(adapterMeasure);
        unitSelection.setSelection(0);
        btnok = (Button) dialogCustom.findViewById(R.id.OKing);
        btncancel = (Button) dialogCustom.findViewById(R.id.Canceling);
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!nameInput.getText().toString().trim().isEmpty()){
                    if(!quantityInput.getText().toString().trim().isEmpty()){
                        newIng.setIngName(nameInput.getText().toString());
                        newIng.setIngQuantity(Float.valueOf(quantityInput.getText().toString()));
                        switch (unitSelection.getSelectedItemPosition()){
                            case 0:
                                newIng.setIngUnits(Ingredient.Measure.none);
                                break;
                            case 1:
                                newIng.setIngUnits(Ingredient.Measure.cup);
                                break;
                            case 2:
                                newIng.setIngUnits(Ingredient.Measure.table_spoon);
                                break;
                            case 3:
                                newIng.setIngUnits(Ingredient.Measure.ounce);
                                break;
                            case 4:
                                newIng.setIngUnits(Ingredient.Measure.kg);
                                break;
                            case 5:
                                newIng.setIngUnits(Ingredient.Measure.g);
                                break;
                            case 6:
                                newIng.setIngUnits(Ingredient.Measure.piece);
                                break;
                        }
                        ingList.add(newIng.getIngName()+" x " + newIng.getIngQuantity()+" "+newIng.getIngUnits());
                        display();
                        Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_SHORT);
                        dialogCustom.dismiss();
                    }
                }
                dialogCustom.dismiss();
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Canceled",Toast.LENGTH_SHORT);
                dialogCustom.dismiss();
            }
        });
    }

    public void stepDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final EditText stepInput = new EditText(this);


        try {
            JSONObject jasonRecipe = new JSONObject();
            jasonRecipe.put("RecipeName", newRecipe.getRecipeName());
            jasonRecipe.put("Class", aClass.getSelectedItem().toString());
            jasonRecipe.put("Category", aCategory.getSelectedItem().toString());
            jasonRecipe.put("Origin", aOrigin.getSelectedItem().toString());
            JSONArray newIngredients = new JSONArray();
            int i=0;
            while(i<newIngredientList.size()){
                JSONObject ingred= new JSONObject();
                ingred.put("name", newIngredientList.get(i).getIngName());
                ingred.put("quantity",newIngredientList.get(i).getIngQuantity());
                ingred.put("unit", newIngredientList.get(i).getIngUnits());
                newIngredients.put(ingred);
                i++;
            }
            jasonRecipe.put("Ingredients",newIngredients);

            JSONArray stps = new JSONArray();
            int a=0;
            while( a<newStepList.size()){
                JSONObject sp= new JSONObject();
                sp.put("step", newStepList.get(i));
                stps.put(sp);
                a++;
            }
            jasonRecipe.put("steps", stps);
            FileOutputStream("test.text");
            

        }

        dialogBuilder.setTitle("Enter your step "+stepCounter);
        dialogBuilder.setMessage("Type your step here:");
        dialogBuilder.setView(stepInput);
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!stepInput.getText().toString().trim().isEmpty()){

                    newStep = stepCounter +": "+ stepInput.getText().toString();
                    stepList.add(newStep);
                    display();
                    stepCounter++;
                    Toast.makeText(getApplicationContext(),"Added",Toast.LENGTH_SHORT);
                }
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Canceled",Toast.LENGTH_SHORT);
            }
        });
        dialogBuilder.show();
    }
    public void display(){
        editIngList =(ListView) findViewById(R.id.edit_ing_list);
        ArrayAdapter adapterIng = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ingList);
        editIngList.setAdapter(adapterIng);

        editStepList = (ListView) findViewById(R.id.edit_step_list);
        ArrayAdapter adapterStep = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stepList);
        editStepList.setAdapter(adapterStep);
    }

}