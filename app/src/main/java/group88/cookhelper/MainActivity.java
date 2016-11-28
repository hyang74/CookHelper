package group88.cookhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.text.*;
import android.content.*;

import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ListView recipeList;

    private String[] spinnerClass ={"Any","Beef", "Chicken", "Seafood", "Vegie"};
    private String[] spinnerOrigin= {"Any","Italian", "Chinese", "Midle Eastern", "Indian", "American"};
    private String[] spinnerCategory= {"Any","Starter", "Main Dish", "Desert", "Drink", "Sauce", "Salad"};
    List<Recipe> allRecipe=new LinkedList<>();
    List<Recipe> filterResult=new LinkedList<>();
    List<String> showList=new LinkedList<String>();
    int numOfAllRecipe;
    int numOfFilteredRecipe;

    EditText mEditText;
    Button mClearText;
    Button filter;
    Button reset;
    Spinner spClass;
    Spinner spOrigin;
    Spinner spCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //We use temperary array to show the list,
        //will be substitute by JSON reader
        Recipe Steak = new Recipe();
        Steak.setRecipeName("Steak");
        allRecipe.add(Steak);

        Recipe VegiePho = new Recipe();
        VegiePho.setRecipeName("Vegie Pho");
        allRecipe.add(VegiePho);

        Recipe BeefPho = new Recipe();
        BeefPho.setRecipeName("Beef Pho");
        allRecipe.add(BeefPho);

        Recipe GrilledChicken = new Recipe();
        GrilledChicken.setRecipeName("Grilled Chicken");
        allRecipe.add(GrilledChicken);

        Recipe BeefStew= new Recipe();
        BeefStew.setRecipeName("Beef Stew");
        allRecipe.add(BeefStew);

        Recipe BeefAndVegiePizza= new Recipe();
        BeefAndVegiePizza.setRecipeName("Beef and Vegie Pizza");
        allRecipe.add(BeefAndVegiePizza);

        Recipe IceCream= new Recipe();
        IceCream.setRecipeName("Ice Cream");
        allRecipe.add(IceCream);

        numOfAllRecipe=6;
        for (int i=0;i<numOfAllRecipe;i++){
            showList.add(allRecipe.get(i).getRecipeName());
        }
        // just for test

        //This is how to add  items to list: use ArrayAdapter
        recipeList = (ListView) findViewById(R.id.recipe_list_view);
        ArrayAdapter adapterRecipe = new ArrayAdapter(this, android.R.layout.simple_list_item_1, showList);
        recipeList.setAdapter(adapterRecipe);


        //This is how to add items to spinner:
        spClass = (Spinner) findViewById(R.id.SPclass);
        ArrayAdapter<String> adapterClass = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerClass);
        spClass.setAdapter(adapterClass);
        spClass.setSelection(0);

        spOrigin = (Spinner) findViewById(R.id.SPorigin);
        ArrayAdapter<String> adapterOrigin = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerOrigin);
        spOrigin.setAdapter(adapterOrigin);
        spOrigin.setSelection(0);

        spCategory = (Spinner) findViewById(R.id.SPcategory);
        ArrayAdapter<String> adapterCategory = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerCategory);
        spCategory.setAdapter(adapterCategory);
        spCategory.setSelection(0);

        filter = (Button) findViewById(R.id.filter);
        reset = (Button) findViewById(R.id.reset);

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filterFunction(allRecipe,mEditText.getText().toString(),spClass.getSelectedItemPosition(),spOrigin.getSelectedItemPosition(),spCategory.getSelectedItemPosition());
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

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

    // This function is called to reset the text
    public void clear(View view) {
        mEditText.setText("");
        mClearText.setVisibility(View.GONE);
    }

    public void goRecipe(View view) {
        Intent intent1 = new Intent(this,showRecipe.class );
        startActivity(intent1);
    }

    public void goAdd(View view) {
        Intent intent2 = new Intent(this,editRecipe.class );
        startActivity(intent2);

    }


    public List<Recipe> filterFunction (List<Recipe> allRecipeName,String searchText,int classOption, int originOption, int categoryOption){
        //we need this function to take a recipe list, and a search string
        //and output a filtered recipe list
        //we can input some recipe (see line 40-70)
        //and add attributes of recipes to test
        //the integer options are in the following order
//        Class {"Any","Beef", "Chicken", "Seafood", "Vegie"};
//        origin {"Any","Italian", "Chinese", "Midle Eastern", "Indian", "American"};
//        category {"Any","Starter", "Main Dish", "Desert", "Drink", "Sauce", "Salad"};


        showList = new LinkedList<>();
        filterResult= new LinkedList<>();
        //used to store filtered list



        //just for test,should show last 3 item with reversed order
        numOfFilteredRecipe=4;
        for (int i=0;i<numOfFilteredRecipe;i++){
            filterResult.add(allRecipe.get(numOfAllRecipe-i-1));
        }


        for(int j=0;j<numOfFilteredRecipe;j++){
            showList.add(filterResult.get(j).getRecipeName());
        }




        //please implement


        displayList(showList);//pass a string list to displaylist
        return filterResult;
    }
    public void reset (){
        spClass.setSelection(0);
        spOrigin.setSelection(0);
        spCategory.setSelection(0);
        mEditText.setText("");
        mClearText.setVisibility(View.GONE);
        showList = new LinkedList<>();
        for(int i=0;i<numOfAllRecipe;i++){
            showList.add(allRecipe.get(i).getRecipeName());
        }
        displayList(showList);

    }
    public void displayList(List<String> newList){
        ArrayAdapter newAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, newList);
        recipeList.setAdapter(newAdapter);
    }
}
