package group88.cookhelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.*;
/**
 * Created by YANG on 2016-11-13.
 */

public class showRecipe extends Activity {
    private ListView ingList;
    private ListView stepList;

    private String[] ingItems ={"Beef steak x 1 Piece", "Peper x 10 g","Olive oil x 200 ml","Salt x 10 g"};
    private String[] stepItems ={"1. Use peper and salt to season the beef steak","2. Pour olive oil in to the pan","3. Wait until the oil is hot, put steak in the pan","4. blablablabla","5. blablablablabla","6. blablablablablablabla"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_recipe);

    // to access list view:
        ingList = (ListView) findViewById(R.id.recipe_ing_list);
        ArrayAdapter adapterIng = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ingItems);
        ingList.setAdapter(adapterIng);

        stepList = (ListView) findViewById(R.id.recipe_step_list);
        ArrayAdapter adapterStep = new ArrayAdapter(this, android.R.layout.simple_list_item_1, stepItems);
        stepList.setAdapter(adapterStep);
}
public void goEdit(View view) {
    Intent intent = new Intent(this,editRecipe.class );
    startActivity(intent);
}
}
