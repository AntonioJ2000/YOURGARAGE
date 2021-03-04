package com.anto.yourgarage.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.anto.yourgarage.R;
import com.anto.yourgarage.interfaces.SearchInterface;
import com.anto.yourgarage.presenters.ListPresenter;
import com.anto.yourgarage.presenters.SearchPresenter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class SearchActivity extends AppCompatActivity implements SearchInterface.View{

    private SearchInterface.Presenter presenter;

    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    Context myContext;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    int Year, Month, Day;

    EditText ownerNameText;
    EditText dateText;

    String helpHint = "search";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myContext = this;
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar3);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Buscar");
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
        }

        ownerNameText = findViewById(R.id.ownerNameSearch);

        dateText = (EditText) findViewById(R.id.editTextReceptionDate);
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(myContext, new DatePickerDialog.OnDateSetListener() {
                    // Definir la acción al pulsar OK en el calendario
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        dateText.setText(String.valueOf(day) + "/" + String.valueOf(month+1) + "/" + String.valueOf(year));
                    }
                },Year, Month, Day);
                datePickerDialog.show();
            }
        });

        presenter = new SearchPresenter(this);
        spinner = (Spinner) findViewById(R.id.searchSpinner);
        ArrayList<String> options = presenter.getAllFuelTypesForSearch();

        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, options);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                if(pos >= 1){
                    Toast.makeText(spinner.getContext(), "Has seleccionado " + spinner.getItemAtPosition(pos).toString(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(spinner.getContext(), "Nada seleccionado", Toast.LENGTH_LONG).cancel();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {}
        });

        Button searchCar = findViewById(R.id.searchButton);
        searchCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickSearchCar();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        presenter = new SearchPresenter(this);
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            presenter.onClickHelp();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startHelpActivity() {
        Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
        intent.putExtra("help", helpHint);
        startActivity(intent);
    }

    @Override
    public void SearchCar() {
        Intent i = getIntent();
        i.putExtra("ownerName", ownerNameText.getText().toString());
        i.putExtra("receptionDate", dateText.getText().toString());
        i.putExtra("spinner", spinner.getSelectedItemId());
        setResult(RESULT_OK,i);
        finish();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }



}