package group88.cookhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

/**
 * Created by YANG on 2016-11-13.
 */

public class showRecipe extends Activity {
    private ListView ingList;
    private ListView stepList;



    //{"Beef steak x 1 Piece", "Peper x 10 g","Olive oil x 200 ml","Salt x 10 g"};
    //private String[] stepItems ={"1. Use peper and salt to season the beef steak","2. Pour olive oil in to the pan","3. Wait until the oil is hot, put steak in the pan","4. blablablabla","5. blablablablabla","6. blablablablablablabla"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_recipe);

        /**Example Recipe
         */
        Recipe Steak = new Recipe();
        Steak.setRecipeName("Steak");
        Steak.setRecipeClass("BEEF");
        Steak.setRecipeCategory("MAIN_DISH");
        Steak.setRecipeOrigin("AMERICAN");
        Steak.addIngredients("Beef",1, Ingredient.Measure.piece);
        Steak.addIngredients("Peper",10, Ingredient.Measure.g);
        Steak.addIngredients("Salt",10, Ingredient.Measure.g);
        Steak.addSteps("1. Use peper and salt to season the beef steak");
        Steak.addSteps("2. Pour olive oil in to the pan");
        Steak.addSteps("3. Wait until the oil is hot, put steak in the pan");

        /**
         * Recipe created, normally imported using json
         *
         */

        TextView mRecipeName = (TextView) findViewById(R.id.SRecipeName);
        TextView mRecipeClass = (TextView) findViewById(R.id.SRecipeclass);
        TextView mRecipeCategory = (TextView) findViewById(R.id.SRecipecategory);
        TextView mRecipeOrigin = (TextView) findViewById(R.id.SRecipeorigin);

        /**
         * change the textView
         */
        mRecipeName.setText(Steak.getRecipeName());
        mRecipeClass.setText(Steak.getRecipeClass());
        mRecipeCategory.setText(Steak.getRecipeCategory());
        mRecipeOrigin.setText(Steak.getRecipeOrigin());

        // to access list view:
        ingList = (ListView) findViewById(R.id.recipe_ing_list);
        ArrayAdapter adapterIng = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Steak.getIngredientList());
        ingList.setAdapter(adapterIng);

        stepList = (ListView) findViewById(R.id.recipe_step_list);
        ArrayAdapter adapterStep = new ArrayAdapter(this, android.R.layout.simple_list_item_1, Steak.getSteps());
        stepList.setAdapter(adapterStep);
    }
    public void goEdit(View view) {
        Intent intent = new Intent(this,editRecipe.class );
        startActivity(intent);
    }
}
