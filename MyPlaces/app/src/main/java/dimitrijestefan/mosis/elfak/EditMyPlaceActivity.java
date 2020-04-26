package dimitrijestefan.mosis.elfak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditMyPlaceActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_place);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Button finishedButton = (Button) findViewById(R.id.editmyplace_finished_button);
        finishedButton.setOnClickListener(this);
        finishedButton.setEnabled(false);
        finishedButton.setText("Add");
        Button cancelButton = (Button) findViewById(R.id.editmyplace_cancel_button);
        cancelButton.setOnClickListener(this);
        EditText nameEditText=(EditText)findViewById(R.id.editmyplace_name_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        nameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                finishedButton.setEnabled(s.length()>0);
            }
        });


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.editmyplace_finished_button: {
                EditText etName = (EditText) findViewById(R.id.editmyplace_name_edit);
                String name = etName.getText().toString();
                EditText etDesc = (EditText) findViewById(R.id.editmyplace_desc_edit);
                String desc = etDesc.getText().toString();


                MyPlace place = new MyPlace(name, desc);
                MyPlacesData.getInstance().addNewPlace(place);

                setResult(Activity.RESULT_OK);
                finish();
                break;
            }
            case R.id.editmyplace_cancel_button: {
                setResult(Activity.RESULT_CANCELED);
                finish();
                break;
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_my_place, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id){
            case R.id.show_map_item :{
                Toast.makeText(this,"Show map", Toast.LENGTH_SHORT).show();
                break;
            }

            case R.id.my_places_list_item:{
                Toast.makeText(this, "My places ", Toast.LENGTH_SHORT).show();
                Intent k= new Intent(this, MyPlacesList.class);
                startActivity(k);
                break;
            }

            case R.id.about_item:{
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                Intent i =new Intent(this, About.class);
                startActivity(i);
                break;
            }
            case android.R.id.home:{
                finish();
                break;
            }

        }


        return super.onOptionsItemSelected(item);
    }




}
