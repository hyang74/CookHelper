package group88.cookhelper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.List;

import static group88.cookhelper.MainActivity.allRecipe;
import static group88.cookhelper.MainActivity.filterResult;

/**
 * Created by YANG on 2016-11-13.
 */

public class showRecipe extends Activity {
    private ListView ingList;
    private ListView stepList;
    private List<String> showIng;
    private List<String> showSteps;
    private Recipe showRecipeDetail;
    private int theNumOfRecipe;
    AlertDialog.Builder dialogBuilder;
    Button mDelete;


    //{"Beef showRecipeDetail x 1 Piece", "Peper x 10 g","Olive oil x 200 ml","Salt x 10 g"};
    //private String[] stepItems ={"1. Use peper and salt to season the beef showRecipeDetail","2. Pour olive oil in to the pan","3. Wait until the oil is hot, put showRecipeDetail in the pan","4. blablablabla","5. blablablablabla","6. blablablablablablabla"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_recipe);
        TextView mRecipeName = (TextView) findViewById(R.id.SRecipeName);
        TextView mRecipeClass = (TextView) findViewById(R.id.SRecipeclass);
        TextView mRecipeCategory = (TextView) findViewById(R.id.SRecipecategory);
        TextView mRecipeOrigin = (TextView) findViewById(R.id.SRecipeorigin);


        Intent intentShow = getIntent();
        theNumOfRecipe=intentShow.getIntExtra("RecipeNumber",0);
        showRecipeDetail =allRecipe.get(theNumOfRecipe);
        showIng=new LinkedList<>();
        showSteps=new LinkedList<>();

        /**
         * change the textView
         */
        mRecipeName.setText(showRecipeDetail.getRecipeName());
        mRecipeClass.setText(showRecipeDetail.getRecipeClass());
        mRecipeCategory.setText(showRecipeDetail.getRecipeCategory());
        mRecipeOrigin.setText(showRecipeDetail.getRecipeOrigin());
        mDelete = (Button) findViewById(R.id.deleteRecipe);
        mDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteRecipeDialog(theNumOfRecipe);
            }
        });

        for(int i =0;i<showRecipeDetail.getIngredients().size();i++){
            showIng.add(Integer.toString(i+1) +". "+showRecipeDetail.getIngredients().get(i).getIngName()+
                    " x "+showRecipeDetail.getIngredients().get(i).getIngQuantity()+
                    " "+ showRecipeDetail.getIngredients().get(i).getIngUnits());
        }
        for(int i =0;i<showRecipeDetail.getSteps().size();i++){
            showSteps.add(Integer.toString(i+1) +". "+showRecipeDetail.getSteps().get(i));
        }
        // to access list view:
        ingList = (ListView) findViewById(R.id.recipe_ing_list);
        ArrayAdapter adapterIng = new ArrayAdapter(this, android.R.layout.simple_list_item_1, showIng);
        ingList.setAdapter(adapterIng);

        stepList = (ListView) findViewById(R.id.recipe_step_list);
        ArrayAdapter adapterStep = new ArrayAdapter(this, android.R.layout.simple_list_item_1, showSteps);
        stepList.setAdapter(adapterStep);


    }
    public void goEdit(View view) {
        Intent intentEdit = new Intent(this,editRecipe.class );
        intentEdit.putExtra("RecipeNumber", theNumOfRecipe);
        intentEdit.putExtra("trueIfAdd",false);
        startActivity(intentEdit);

    }


    public void onBackPressed() {
        moveTaskToBack(false);
        showRecipe.this.finish();
        Intent intentMain = new Intent (this,MainActivity.class);
        startActivity(intentMain);
    }

    public void deleteRecipeDialog(final int j){
        dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle("Are you sure to delete this recipe?");
        dialogBuilder.setMessage(showRecipeDetail.getRecipeName());
        dialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface,int i) {
                for(int o=0;o<filterResult.size();o++){
                    if(filterResult.get(o).getRecipeName()==showRecipeDetail.getRecipeName()){
                        filterResult.remove(o);
                    }
                }
                allRecipe.remove(theNumOfRecipe);
                delete();
            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogBuilder.show();
    }
    public void delete(){

        writeBtn();
        Intent intentMain = new Intent (this,MainActivity.class);
        startActivity(intentMain);
    }
    public void writeBtn() {
        String str=write_jason();
        try {
            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
            outputWriter.write(str);
            outputWriter.close();
            System.out.println("write+"+str);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
