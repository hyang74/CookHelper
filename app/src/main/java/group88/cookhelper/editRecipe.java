package group88.cookhelper;

/**
 * Created by YANG on 2016-11-13.
 */

import android.app.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;

import android.content.Context;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.*;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

import static group88.cookhelper.MainActivity.PREFS_NAME;
import static group88.cookhelper.MainActivity.allRecipe;
import static group88.cookhelper.MainActivity.filterResult;


public class editRecipe extends Activity {

    public String[] spinnerMeasure = {"none","cup", "tea spoon", "table spoon", "ounce", "kg", "g", "piece","pound"};
    private String[] spinnerAddClass = {"Any","Beef", "Chicken", "Seafood", "Veggie"};
    private String[] spinnerAddOrigin = {"Any","Italian", "Chinese", "Midle Eastern", "Indian", "American"};
    private String[] spinnerAddCategory = {"Any","Starter", "Main Dish", "Desert", "Drink", "Sauce", "Salad"};
    private List<Ingredient>newIngList = new LinkedList<>();
    private List<String> newStepList = new LinkedList<>();
    private List<String> showIng;
    private List<String> showSteps;
    private Recipe newRecipe = new Recipe();
    EditText mEditText;
    Button mClearText;
    Button mSave;
    Button mAddIng;
    Button mAddStep;
    ListView editIngList;
    ListView editStepList;
    private int stepCounter = 1;
    Spinner aClass;
    Spinner aOrigin;
    Spinner aCategory;
    AlertDialog.Builder dialogBuilder;
    String newStep;
    Ingredient newIng = new Ingredient();
    private int theNumOfThisRecipe;
    private boolean trueIfAdd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_recipe);


        Intent intent = getIntent();
        theNumOfThisRecipe=intent.getIntExtra("RecipeNumber",0);
        trueIfAdd = intent.getBooleanExtra("trueIfAdd",false);




        if(trueIfAdd){
            newRecipe=allRecipe.get(theNumOfThisRecipe);
        }
        else {
            newRecipe = filterResult.get(theNumOfThisRecipe);
        }

        stepCounter=newRecipe.getSteps().size()+1;

        newStepList=newRecipe.getSteps();
        newIngList=newRecipe.getIngredients();
        showIng=new LinkedList<>();
        showSteps = new LinkedList<>();


        mEditText=(EditText) findViewById(R.id.EditName);
        mEditText.setText(newRecipe.getRecipeName());
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
        aClass = (Spinner) findViewById(R.id.Addclass);
        aOrigin = (Spinner) findViewById(R.id.Addorigin);
        aCategory = (Spinner) findViewById(R.id.Addcategory);
        ArrayAdapter<String> adapterAClass = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerAddClass);
        aClass.setAdapter(adapterAClass);
        //        private String[] spinnerClass ={{"Any","Beef", "Chicken","Pork","Seafood", "Vegie","Mixed"};
//        private String[] spinnerOrigin= {"Any","Italian", "Chinese", "Midle Eastern", "Indian", "American"};
//        private String[] spinnerCategory= {"Any","Starter", "Main Dish", "Desert", "Drink", "Sauce", "Salad"};

        switch (newRecipe.getRecipeClass()) {
            case "Any":
                aClass.setSelection(0);
                break;
            case "Beef":
                aClass.setSelection(1);
                break;
            case "Chicken":
                aClass.setSelection(2);
                break;
            case "Prok":
                aClass.setSelection(3);;
                break;
            case "Seafood":
                aClass.setSelection(4);
                break;
            case "Vegie":
                aClass.setSelection(5);
                break;
            case "Mixed":
                aClass.setSelection(6);
                break;
        }



        ArrayAdapter<String> adapteraOrigin = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerAddOrigin);
        aOrigin.setAdapter(adapteraOrigin);
        //{"Any","Italian", "Chinese", "Midle Eastern", "Indian", "American"};
        switch (newRecipe.getRecipeOrigin()) {
            case "Any":
                aOrigin.setSelection(0);
                break;
            case "Italian":
                aOrigin.setSelection(1);
                break;
            case "Chinese":
                aOrigin.setSelection(2);
                break;
            case "Midle Eastern":
                aOrigin.setSelection(3);;
                break;
            case "Indian":
                aOrigin.setSelection(4);
                break;
            case "American":
                aOrigin.setSelection(5);
                break;
        }

        ArrayAdapter<String> adapteraCategory = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerAddCategory);
        aCategory.setAdapter(adapteraCategory);
        //{"Any","Starter", "Main Dish", "Desert", "Drink", "Sauce", "Salad"
        switch (newRecipe.getRecipeClass()) {
            case "Any":
                aCategory.setSelection(0);
                break;
            case "Starter":
                aCategory.setSelection(1);
                break;
            case "Main Dish":
                aCategory.setSelection(2);
                break;
            case "Desert":
                aCategory.setSelection(3);;
                break;
            case "Drink":
                aCategory.setSelection(4);
                break;
            case "Sauce":
                aCategory.setSelection(5);
                break;
            case "Salad":
                aCategory.setSelection(6);
                break;
        }

        for(int i =0;i<newIngList.size();i++){
            showIng.add(Integer.toString(i+1) +". "+newIngList.get(i).getIngName());
        }
        for(int i =0;i<newStepList.size();i++){
            showSteps.add(Integer.toString(i+1) +". "+newStepList.get(i));
        }

        editIngList =(ListView) findViewById(R.id.edit_ing_list);
        ArrayAdapter adapterIng = new ArrayAdapter(this, android.R.layout.simple_list_item_1, showIng);
        editIngList.setAdapter(adapterIng);

        editStepList = (ListView) findViewById(R.id.edit_step_list);
        ArrayAdapter adapterStep = new ArrayAdapter(this, android.R.layout.simple_list_item_1, showSteps);
        editStepList.setAdapter(adapterStep);

        editIngList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView,View view, int i, long l){
                deleteIngDialog(i);
            }
        });

        editStepList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> adapterView,View view, int i, long l){
                deleteStepDialog(i);
            }
        });
        // Do not change, this block is used to clear text on clicking the X botton

        mClearText = (Button) findViewById(R.id.clearText);
        mSave = (Button) findViewById(R.id.saveEdit);

        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goRecipe();
            }
        });
        //initially clear button is invisible
        mClearText.setVisibility(View.INVISIBLE);
        mEditText = (EditText) findViewById(R.id.EditName);

        //clear button visibility on text change
        if(!mEditText.getText().toString().isEmpty()){
            mClearText.setVisibility(View.VISIBLE);
        }
        else {
            mClearText.setVisibility(View.GONE);
        }
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


    public void ingredientDialog(){
        final EditText nameInput ;
        final EditText quantityInput;
        final Spinner unitSelection;
        Button btnSave;
        Button btnDetele;
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
        btnSave = (Button) dialogCustom.findViewById(R.id.saveIng);
        btnDetele = (Button) dialogCustom.findViewById(R.id.deleteIng);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newIng= new Ingredient();
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
                                newIng.setIngUnits(Ingredient.Measure.tea_spoon);
                            case 3:
                                newIng.setIngUnits(Ingredient.Measure.table_spoon);
                                break;
                            case 4:
                                newIng.setIngUnits(Ingredient.Measure.ounce);
                                break;
                            case 5:
                                newIng.setIngUnits(Ingredient.Measure.kg);
                                break;
                            case 6:
                                newIng.setIngUnits(Ingredient.Measure.g);
                                break;
                            case 7:
                                newIng.setIngUnits(Ingredient.Measure.piece);
                                break;
                        }
                        newIngList.add(newIng);
                        display();

                        dialogCustom.dismiss();
                    }
                }
                dialogCustom.dismiss();
            }
        });

        btnDetele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogCustom.dismiss();
            }
        });
    }

    public void stepDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        final EditText stepInput = new EditText(this);

        dialogBuilder.setTitle("Enter your step "+stepCounter);
        dialogBuilder.setMessage("Type your step here:");
        dialogBuilder.setView(stepInput);
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (!stepInput.getText().toString().trim().isEmpty()){
                    newStep = stepInput.getText().toString();
                    newStepList.add(newStep);
                    display();
                    //stepCounter++;
                }

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogBuilder.show();
    }
    public void display(){
        showIng=new LinkedList<>();
        showSteps=new LinkedList<>();
        for(int i =0;i<newIngList.size();i++){
            showIng.add(Integer.toString(i+1) +". "+newIngList.get(i).getIngName()+
                    " x "+newIngList.get(i).getIngQuantity()+
                    " "+ newIngList.get(i).getIngUnits());
        }
        for(int i =0;i<newStepList.size();i++){
            showSteps.add(Integer.toString(i+1) +". "+newStepList.get(i));
        }
        editIngList =(ListView) findViewById(R.id.edit_ing_list);
        ArrayAdapter adapterIng = new ArrayAdapter(this, android.R.layout.simple_list_item_1, showIng);
        editIngList.setAdapter(adapterIng);

        editStepList = (ListView) findViewById(R.id.edit_step_list);
        ArrayAdapter adapterStep = new ArrayAdapter(this, android.R.layout.simple_list_item_1, showSteps);
        editStepList.setAdapter(adapterStep);
    }


    public void goRecipe() {
        if(mEditText.getText().toString().isEmpty()){
            missingNameDialog();}
        else {
            if (newIngList.isEmpty()) {
                missingIngredientDialog();
            } else {
                if (newStepList.isEmpty()) {
                    missingStepDialog();
                } else {
                    if(trueIfAdd){
                        allRecipe.remove(theNumOfThisRecipe);
                        filterResult.clear();
                        newRecipe.setRecipeName(mEditText.getText().toString());
                        newRecipe.setRecipeClass(spinnerAddClass[aClass.getSelectedItemPosition()]);
                        newRecipe.setRecipeOrigin(spinnerAddOrigin[aOrigin.getSelectedItemPosition()]);
                        newRecipe.setRecipeCategory(spinnerAddCategory[aCategory.getSelectedItemPosition()]);
                        newRecipe.setIngredients(newIngList);
                        newRecipe.setSteps(newStepList);
                        allRecipe.add(newRecipe);
                        for (int w=0;w<allRecipe.size();w++){
                            filterResult.add(allRecipe.get(w));
                        }
                        writeBtn();
                        Intent intentShow = new Intent(this, showRecipe.class);
                        intentShow.putExtra("RecipeNumber", theNumOfThisRecipe);
                        startActivity(intentShow);
                    }
                    else {
                        filterResult.remove(theNumOfThisRecipe);
                        newRecipe.setRecipeName(mEditText.getText().toString());
                        newRecipe.setRecipeClass(spinnerAddClass[aClass.getSelectedItemPosition()]);
                        newRecipe.setRecipeOrigin(spinnerAddOrigin[aOrigin.getSelectedItemPosition()]);
                        newRecipe.setRecipeCategory(spinnerAddCategory[aCategory.getSelectedItemPosition()]);
                        newRecipe.setIngredients(newIngList);
                        newRecipe.setSteps(newStepList);
                        int theNumOfNewRecipe = filterResult.size();
                        filterResult.add(newRecipe);
                        for(int o=0;o<allRecipe.size();o++){
                            if(allRecipe.get(o).getRecipeName()==newRecipe.getRecipeName())
                                allRecipe.remove(o);
                        }

                        allRecipe.add(newRecipe);
                        writeBtn();
                        Intent intentShow = new Intent(this, showRecipe.class);
                        intentShow.putExtra("RecipeNumber", theNumOfNewRecipe);
                        startActivity(intentShow);
                    }
                }

            }

        }
    }

    public void missingNameDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Recipe can not be saved");
        dialogBuilder.setMessage("Reason:Name missing");
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialogBuilder.show();
    }
    public void missingIngredientDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Recipe can not be saved");
        dialogBuilder.setMessage("Reason:Ingredient missing");
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface,int i) {

            }
        });

        dialogBuilder.show();
    }
    public void missingStepDialog(){
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Recipe can not be saved");
        dialogBuilder.setMessage("Reason:Step missing");
        dialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface,int i) {

            }
        });

        dialogBuilder.show();
    }
    public void deleteIngDialog(final int j){
            final EditText nameInput;
            final EditText quantityInput;
            final Spinner unitSelection;
            Button btnSave;
            Button btnDelete;
            final Dialog dialogCustom = new Dialog(this);
            final Ingredient oldIng = newIngList.get(j);
            dialogCustom.setContentView(R.layout.dialog);
            dialogCustom.show();
            dialogCustom.setTitle("Modify your ingredient:");
            nameInput = (EditText) dialogCustom.findViewById(R.id.editIngName);
        nameInput.setText(oldIng.getIngName());
            quantityInput = (EditText) dialogCustom.findViewById(R.id.editIngQ);
        quantityInput.setText(oldIng.getIngQuantity());
            unitSelection = (Spinner) dialogCustom.findViewById(R.id.editIngU);
            ArrayAdapter<String> adapterMeasure = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, spinnerMeasure);
            unitSelection.setAdapter(adapterMeasure);
        switch (newIngList.get(j).getIngUnits()) {
            case "":
                unitSelection.setSelection(0);
                break;
            case "cup":
                unitSelection.setSelection(1);
                break;
            case "tea_spoon":
                unitSelection.setSelection(2);
                break;
            case "table_spoon":
                unitSelection.setSelection(3);
                break;
            case "ounce":
                unitSelection.setSelection(4);
                break;
            case "kg":
                unitSelection.setSelection(5);
                break;
            case "g":
                unitSelection.setSelection(6);
                break;
            case "piece":
                unitSelection.setSelection(7);
                break;
            case "pound":
                unitSelection.setSelection(8);
                break;
        }
            btnSave = (Button) dialogCustom.findViewById(R.id.saveIng);
            btnDelete = (Button) dialogCustom.findViewById(R.id.deleteIng);
            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!nameInput.getText().toString().trim().isEmpty()) {
                        if (!quantityInput.getText().toString().trim().isEmpty()) {
                            oldIng.setIngName(nameInput.getText().toString());
                            oldIng.setIngQuantity(Float.valueOf(quantityInput.getText().toString()));
                            switch (unitSelection.getSelectedItemPosition()) {
                                case 0:
                                    oldIng.setIngUnits(Ingredient.Measure.none);
                                    break;
                                case 1:
                                    oldIng.setIngUnits(Ingredient.Measure.cup);
                                    break;
                                case 2:
                                    oldIng.setIngUnits(Ingredient.Measure.tea_spoon);
                                case 3:
                                    oldIng.setIngUnits(Ingredient.Measure.table_spoon);
                                    break;
                                case 4:
                                    oldIng.setIngUnits(Ingredient.Measure.ounce);
                                    break;
                                case 5:
                                    oldIng.setIngUnits(Ingredient.Measure.kg);
                                    break;
                                case 6:
                                    oldIng.setIngUnits(Ingredient.Measure.g);
                                    break;
                                case 7:
                                    oldIng.setIngUnits(Ingredient.Measure.piece);
                                    break;
                            }
                            display();
                            dialogCustom.dismiss();
                        }
                    }
                    dialogCustom.dismiss();
                    display();
                }
            });

            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    newIngList.remove(j);
                    dialogCustom.dismiss();
                    display();
                }
            });

    }
    public void deleteStepDialog(final int j){
        dialogBuilder = new AlertDialog.Builder(this);
        final EditText stepInput = new EditText(this);

        dialogBuilder.setTitle("Modify your step "+(j+1));
        dialogBuilder.setMessage("Type your step here:");
        stepInput.setText(newStepList.get(j));
        dialogBuilder.setView(stepInput);
        dialogBuilder.setPositiveButton("SAVE", new DialogInterface.OnClickListener() {
            @Override

            public void onClick(DialogInterface dialogInterface, int i) {
                if (!stepInput.getText().toString().trim().isEmpty()){
                    String modifiedStep = stepInput.getText().toString();
                    newStepList.set(j,modifiedStep);
                    display();
                }
            }
        });
        dialogBuilder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            newStepList.remove(j);
                stepCounter--;
                display();
            }
        });
        dialogBuilder.show();
    }
    public void onBackPressed() {
        moveTaskToBack(false);
        if(trueIfAdd){
            allRecipe.remove(theNumOfThisRecipe);
            editRecipe.this.finish();

            writeBtn();
            Intent intentMain = new Intent (this,MainActivity.class);
            startActivity(intentMain);
        }
        else{
            editRecipe.this.finish();
            writeBtn();
            Intent intentShow = new Intent (this,showRecipe.class);
            intentShow.putExtra("RecipeNumber",theNumOfThisRecipe);
            startActivity(intentShow);
        }
    }
    public void writeBtn() {
        String str = write_jason();
        SharedPreferences savedV = getSharedPreferences( PREFS_NAME, 0);
        SharedPreferences.Editor editor = savedV.edit();
        editor.putString("AllRecipe", str);
        editor.apply();

    }
    public String write_jason() {
        try {
            JSONObject data = new JSONObject();
            JSONArray recipes = new JSONArray();
            data.put("recipes", recipes);
            for(int c = 0; c< allRecipe.size(); c++) {
                JSONObject jasonRecipe = new JSONObject();
                jasonRecipe.put("RecipeName", allRecipe.get(c).getRecipeName());
                jasonRecipe.put("Class", allRecipe.get(c).getRecipeClass());
                jasonRecipe.put("Category", allRecipe.get(c).getRecipeCategory());
                jasonRecipe.put("Origin", allRecipe.get(c).getRecipeOrigin());
                JSONArray newIngredients = new JSONArray();
                int i = 0;
                while (i < allRecipe.get(c).getIngredients().size()) {
                    JSONObject ingred = new JSONObject();
                    ingred.put("name", allRecipe.get(c).getIngredients().get(i).getIngName());
                    ingred.put("quantity", allRecipe.get(c).getIngredients().get(i).getIngQuantity());
                    ingred.put("unit", allRecipe.get(c).getIngredients().get(i).getIngUnits());
                    newIngredients.put(ingred);
                    i++;
                }
                jasonRecipe.put("Ingredients", newIngredients);
                JSONArray stps = new JSONArray();
                int a = 0;
                while (a < allRecipe.get(c).getSteps().size()) {
                    JSONObject sp = new JSONObject();
                    sp.put("step", allRecipe.get(c).getSteps().get(a));
                    stps.put(sp);

                    a++;
                }
                jasonRecipe.put("steps", stps);
                recipes.put(jasonRecipe);

            }
            return data.toString();
        } catch(JSONException e) {
            e.printStackTrace();
        }


        return null;
    }


}



