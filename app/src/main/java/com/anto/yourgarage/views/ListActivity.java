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
import androidx.annotation.Nullable;
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
import android.widget.TextView;
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
    private Context myContext;
    private ConstraintLayout constraintLayoutListActivity;
    private TextView itemText;
    private int numberOfItems;

    CarAdapter adaptador;
    String ownerNameFilter = null;
    Date receptionDateFilter = null;
    String fuelTypeFilter = null;

    String helpHint = "list";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        myContext = this;

        constraintLayoutListActivity = findViewById(R.id.constraintLayoutMainActivity);

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


        if(ownerNameFilter==null && receptionDateFilter==null && fuelTypeFilter==null){
            items = presenter.getAllItemsSumarize();
        }else{
            items = presenter.getItemsFiltered(ownerNameFilter, receptionDateFilter, fuelTypeFilter);
            ownerNameFilter = null;
            receptionDateFilter = null;
            fuelTypeFilter = null;
        }

        numberOfItems = items.size();
        itemText = findViewById(R.id.textView3);
        itemText.setText("La lista contiene "+ numberOfItems + " elementos");

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        // Crea el Adaptador con los datos de la lista anterior
        adaptador = new CarAdapter(items);

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
                /**
                 System.out.println(position);
                 System.out.println(items.get(position).getName());
                 System.out.println(items.get(position).getId());
                 */
                presenter.deleteCarBySwipe((items.get(position)).getId());
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
        if (id == R.id.action_help) {
            presenter.onClickHelp();
        }else if(id == R.id.action_search){
            presenter.onClickSearch();
        }else if(id == R.id.action_about){
            presenter.onClickAbout();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_CANCELED){
            System.out.println("Back");
        }else{
            if(!data.getExtras().getString("ownerName").equals("")){
                ownerNameFilter = data.getExtras().getString("ownerName");
                System.out.println(ownerNameFilter);
            }

            if(!data.getExtras().getString("receptionDate").equals("")){
                SimpleDateFormat newDate = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    receptionDateFilter = newDate.parse(data.getExtras().getString("receptionDate"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            if(data.getExtras().getLong("spinner")!=0){
                ArrayList<String> items = presenter.getFuelTypes();
                fuelTypeFilter = items.get((int)data.getExtras().getLong("spinner"));
            }
        }
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
        startActivityForResult(intent,0);
    }

    @Override
    public void startAboutActivity() {
        Intent intent = new Intent(getApplicationContext(), AboutActivity.class);
        startActivity(intent);
    }

    @Override
    public void startHelpActivity(){
        Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
        intent.putExtra("help", helpHint);
        startActivity(intent);
    }

    @Override
    public ArrayList<CarEntity> getAllItemsSumarize() {
        ArrayList<CarEntity> myCars;
        myCars = CarModel.getAllSummarize();

        return myCars;
    }


}