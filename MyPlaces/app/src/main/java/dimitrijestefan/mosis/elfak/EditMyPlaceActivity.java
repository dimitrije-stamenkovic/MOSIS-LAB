package dimitrijestefan.mosis.elfak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
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

    int position = -1;
    boolean editMode = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_my_place);
        try {
            Intent listIntent = getIntent();
            Bundle positionBundle = listIntent.getExtras();
            if(positionBundle != null){
                position = positionBundle.getInt("position");
            }else{
                editMode=false;
            }


        }catch (Exception e){
            editMode=false;
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }

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

        if(!editMode){
            finishedButton.setEnabled(false);
            finishedButton.setText("Add");
        }else if (position >=0){
            finishedButton.setText("Save");
            MyPlace place =MyPlacesData.getInstance().getPlace(position);
            nameEditText.setText(place.getName());
            EditText descEditText=(EditText) findViewById(R.id.editmyplace_desc_edit);
            descEditText.setText(place.getDescription());
        }

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

        Button locationButton = (Button) findViewById(R.id.editmyplace_location_button);
        locationButton.setOnClickListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try{
            if(resultCode == Activity.RESULT_OK){
                String lon = data.getExtras().getString("lon");
                EditText lonText= (EditText) findViewById(R.id.editmyplace_lon_edit);
                lonText.setText(lon);
                String lat = data.getExtras().getString("lat");
                EditText latText= (EditText) findViewById(R.id.editmyplace_lat_edit);
                latText.setText(lat);
            }

        }catch (Exception e){
            Toast.makeText(this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.editmyplace_finished_button: {
                EditText etName = (EditText) findViewById(R.id.editmyplace_name_edit);
                String name = etName.getText().toString();
                EditText etDesc = (EditText) findViewById(R.id.editmyplace_desc_edit);
                String desc = etDesc.getText().toString();
                EditText latEdit = (EditText) findViewById(R.id.editmyplace_lat_edit);
                String lat = latEdit.getText().toString();
                EditText lonEdit = (EditText) findViewById(R.id.editmyplace_lon_edit);
                String lon = lonEdit.getText().toString();

                if(!editMode){
                    MyPlace place = new MyPlace(name,desc);
                    place.setLatitude(lat);
                    place.setLongitude(lon);
                    MyPlacesData.getInstance().addNewPlace(place);
                }else{
                    MyPlace place = MyPlacesData.getInstance().getPlace(position);
                    place.setName(name);
                    place.setDescription(desc);
                    place.setLatitude(lat);
                    place.setLongitude(lon);
                }


                setResult(Activity.RESULT_OK);
                finish();
                break;
            }
            case R.id.editmyplace_cancel_button: {
                setResult(Activity.RESULT_CANCELED);
                finish();
                break;
            }
            case R.id.editmyplace_location_button:{
                Intent i = new Intent(this,MyPlacesMapsActivity.class);
                i.putExtra("state",MyPlacesMapsActivity.SELECT_COORDINATES);
                startActivityForResult(i,1);
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
