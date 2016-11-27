package group88.cookhelper;

/**
 * Created by YANG on 2016-11-13.
 */

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.*;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class editRecipe extends Activity {


    private String[] spinnerAddClass = {"ANY", "BEEF", "CHICKEN", "SEAFOOD", "VEGIE"};
    private String[] spinnerAddOrigin = {"ANY", "ITALIAN", "CHINESE", "MIDLE_EASTERN", "INDIAN", "AMERICAN"};
    private String[] spinnerAddCategory = {"ANY", "STARTER", "MAIN_DISH", "DESERT", "DRINK", "SAUCE", "SALAD"};
    private List<Ingredient> newIngredientList = new LinkedList<>();
    private List<String> newStepList = new LinkedList<>();

    EditText mEditText;
    Button mClearText;
    Button mSave;
    EditText mEditName = (EditText) findViewById(R.id.EditName);
    Spinner aClass = (Spinner) findViewById(R.id.Addclass);
    Spinner aOrigin = (Spinner) findViewById(R.id.Addorigin);

    Spinner aCategory = (Spinner) findViewById(R.id.Addcategory);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_recipe);


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

        // Do not change, this block is used to clear text on clicking the X botton
        mEditText = (EditText) findViewById(R.id.search);
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



        try {
            JSONObject jasonRecipe = new JSONObject();
            jasonRecipe.put("RecipeName", newRecipe.getRecipeName());
            jasonRecipe.put("Class", aClass.getSelectedItem().toString());
            jasonRecipe.put("Category", aCategory.getSelectedItem().toString());
            jasonRecipe.put("Origin", aOrigin.getSelectedItem().toString());
            JSONArray newIngredients = new JSONArray();
            while()
        }

        catch(JSONException ex){}
    }
}