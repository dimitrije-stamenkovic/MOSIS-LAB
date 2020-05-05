package dimitrijestefan.mosis.elfak;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static  int NEW_PLACE=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this , EditMyPlaceActivity.class);
                startActivityForResult(i,NEW_PLACE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
             //   Toast.makeText(this,"Show map", Toast.LENGTH_SHORT).show();
                Intent k= new Intent(this, MyPlacesMapsActivity.class);
                k.putExtra("state",MyPlacesMapsActivity.SHOW_MAP);
                startActivity(k);
            }
            break;
            case R.id.new_place_item:{
                Toast.makeText(this,"New place ",Toast.LENGTH_SHORT).show();
                Intent k= new Intent(this,EditMyPlaceActivity.class);
                startActivityForResult(k,NEW_PLACE);
            }
            break;
            case R.id.my_places_list_item:{
                Toast.makeText(this, "My places ", Toast.LENGTH_SHORT).show();
                Intent k= new Intent(this, MyPlacesList.class);
                startActivity(k);
            }
            break;
            case R.id.about_item:{
                Toast.makeText(this, "About", Toast.LENGTH_SHORT).show();
                Intent i =new Intent(this, About.class);
                startActivity(i);
            }
            break;
        }


        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== Activity.RESULT_OK){
            Toast.makeText(this, "New place added",Toast.LENGTH_SHORT).show();
        }
    }
}
