package group88.cookhelper;

/**
 * Created by YANG on 2016-11-13.
 */

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

public class editRecipe extends Activity {


    private String[] spinnerAddClass ={"Any","Beef", "Chicken", "Seafood", "Vegie"};
    private String[] spinnerAddOrigin= {"Any","Italian", "Chinese", "Midle Eastern", "Indian", "American"};
    private String[] spinnerAddCategory= {"Any","Starter", "Main Dish", "Desert", "Drink", "Sauce", "Salad"};


    EditText mEditText;
    Button mClearText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_recipe);


        Spinner AClass = (Spinner) findViewById(R.id.Addclass);
        ArrayAdapter<String> adapterAClass = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerAddClass);
        AClass.setAdapter(adapterAClass);
        AClass.setSelection(0);

        Spinner AOrigin = (Spinner) findViewById(R.id.Addorigin);
        ArrayAdapter<String> adapterAOrigin = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerAddOrigin);
        AOrigin.setAdapter(adapterAOrigin);
        AOrigin.setSelection(0);

        Spinner ACategory = (Spinner) findViewById(R.id.Addcategory);
        ArrayAdapter<String> adapterACategory = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerAddCategory);
        ACategory.setAdapter(adapterACategory);
        ACategory.setSelection(0);

        // Do not change, this block is used to clear text on clicking the X botton
        mEditText = (EditText) findViewById(R.id.search);
        mClearText = (Button) findViewById(R.id.clearText);


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
                if(s.length() != 0) {
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

}