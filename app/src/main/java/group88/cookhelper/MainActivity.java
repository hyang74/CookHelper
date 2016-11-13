package group88.cookhelper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.view.*;
import android.text.*;

public class MainActivity extends AppCompatActivity {
    private ListView mListView;
    EditText mEditText;
    Button mClearText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListView = (ListView) findViewById(R.id.recipe_list_view);
        String[] listItems ={"Spaghetti","Steak","Pho","Grilled Chicken","Beef Stew","Meat Pizza","Ice Cream"};
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listItems);
        mListView.setAdapter(adapter);


        mEditText = (EditText) findViewById(R.id.Search);
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
    public void clear(View view) {
        mEditText.setText("");
        mClearText.setVisibility(View.GONE);
    }
}
