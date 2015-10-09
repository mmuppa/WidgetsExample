package edu.uw.tacoma.mmuppa.widgetsexample;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.widget.AdapterView.*;
import android.view.View;

public class MainActivity extends ActionBarActivity {

    private int mItemIndex;
    private Button mButton;
    private EditText mNameEditText;
    private EditText mCommentsEditText;
    private RadioButton mGenderRadioButton;
    private CheckBox mPizzaCheckBox;
    private Spinner mColorSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get a reference to the Spinner
        mColorSpinner = (Spinner) findViewById(R.id.color_spinner);

        // Create an Adapter that holds a list of colors
        // colors is defined in strings.xml
        // simple_spinner_item and simple_spinner_dropdown_item are predefined
        // Android layout elements.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.colors, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the Adapter for the spinner
        mColorSpinner.setAdapter(adapter);

        mItemIndex = mColorSpinner.getSelectedItemPosition();
        // Set an setOnItemSelectedListener on the spinner
        mColorSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {

                if (mItemIndex != pos) {
                    // Display a Toast message indicating the currently selected
                    // item
                    Toast.makeText(
                            parent.getContext(),
                            "The color is "
                                    + parent.getItemAtPosition(pos).toString(),
                            Toast.LENGTH_LONG).show();
                }
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        mButton = (Button) findViewById(R.id.submit_button);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StringBuilder builder = new StringBuilder();

                mNameEditText = (EditText)findViewById(R.id.name_edit_text);
                mCommentsEditText = (EditText)findViewById(R.id.comments_edit_text);
                mGenderRadioButton = (RadioButton)findViewById(R.id.male_radio_button);
                mPizzaCheckBox = (CheckBox) findViewById(R.id.pizza_check_box);
                mColorSpinner = (Spinner) findViewById(R.id.color_spinner);

                builder.append("Name is ")
                .append(mNameEditText.getText())
                .append("\nComments: ")
                .append(mCommentsEditText.getText())
                .append("\nYou are a ")
                .append(mGenderRadioButton.isChecked()?"Man":"Woman")
                .append("\nYou do")
                .append(mPizzaCheckBox.isChecked()?"":"'nt")
                .append(" like pizza")
                .append("\nYour favorite color is ")
                .append(mColorSpinner.getSelectedItem());

                Toast.makeText(v.getContext(), builder.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
