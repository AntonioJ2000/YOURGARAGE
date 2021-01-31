package com.anto.yourgarage.views;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.anto.yourgarage.R;
import com.anto.yourgarage.interfaces.ListInterface;
import com.anto.yourgarage.models.CarEntity;
import com.anto.yourgarage.models.CarModel;
import com.anto.yourgarage.presenters.ListPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ListActivity extends AppCompatActivity implements ListInterface.View {

    String TAG = "YOURGARAGE/MainActivity";
    private ListInterface.Presenter presenter;
    private ArrayList<CarEntity> items;
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION=123;
    private Context myContext;
    private ConstraintLayout constraintLayoutListActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        myContext = this;

        constraintLayoutListActivity = findViewById(R.id.constraintLayoutMainActivity);

        //Vemos si tenemos permiso de internet.
        /*
        Button buttonNormal = findViewById(R.id.buttonNormal);
        buttonNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int InternetPermission = ContextCompat.checkSelfPermission(myContext, Manifest.permission.INTERNET);
                Log.d(TAG, "INTERNET Permission: " + InternetPermission);
                if (InternetPermission != PackageManager.PERMISSION_GRANTED) {
                    // Permiso denegado
                    Snackbar.make(constraintLayoutListActivity, getResources().getString(R.string.internet_permission_denied), Snackbar.LENGTH_LONG)
                            .show();
                } else {
                    // Permiso aceptado
                    Snackbar.make(constraintLayoutListActivity, getResources().getString(R.string.internet_permission_granted), Snackbar.LENGTH_LONG)
                            .show();
                }
            }
        });
        */

        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);

        presenter = new ListPresenter(this);

        FloatingActionButton fab = findViewById(R.id.listFab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "Click on floating button");
                presenter.onClickAddCar();

            }
        });

        //Datos de ejemplo.
        items = this.getAllItemsSumarize();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list, menu);
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        items = presenter.getAllItemsSumarize();

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Crea el Adaptador con los datos de la lista anterior
        final CarAdapter adaptador = new CarAdapter(items);

        // Asocia el elemento de la lista con una acción al ser pulsado
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Acción al pulsar el elemento
                int position = recyclerView.getChildAdapterPosition(v);
                Toast.makeText(ListActivity.this, "Posición: " + String.valueOf(position) + " Nombre: " + items.get(position).getName() + " Modelo: " + items.get(position).getModelName(), Toast.LENGTH_SHORT)
                        .show();
                System.out.println(items.get(position).getId());
                presenter.onClickRecyclerViewItem(items.get(position).getId());
            }
        });

        recyclerView.setAdapter(adaptador);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder target, int i) {
                int position = target.getAdapterPosition();
                System.out.println(position);
                items.remove(position);
                adaptador.notifyDataSetChanged();
                Toast.makeText(ListActivity.this, "El elemento " + position +" ha sido borrado con éxito", Toast.LENGTH_SHORT).show();
            }
        });
        helper.attachToRecyclerView(recyclerView);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        presenter = new ListPresenter(this);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }else if(id == R.id.action_search){
            presenter.onClickSearch();
        }else if(id == R.id.action_about){
            presenter.onClickAbout();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startFormActivity() {
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        startActivity(intent);
    }

    @Override
    public void startFormActivity(String id) {
        Intent intent = new Intent(getApplicationContext(), FormActivity.class);
        intent.putExtra("id",id);
        startActivity(intent);
    }

    @Override
    public void startSearchActivity(){
        Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
        startActivity(intent);
    }

    @Override
    public void startAboutActivity() {
        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public ArrayList<CarEntity> getAllItemsSumarize() {
        ArrayList<CarEntity> myCars;
        myCars = CarModel.getAllSummarize();

        return myCars;
    }


}