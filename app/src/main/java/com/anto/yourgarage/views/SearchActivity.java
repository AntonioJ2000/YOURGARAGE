package com.anto.yourgarage.views;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;

import com.anto.yourgarage.R;
import com.anto.yourgarage.interfaces.SearchInterface;
import com.anto.yourgarage.presenters.FormPresenter;
import com.anto.yourgarage.presenters.SearchPresenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        myContext = this;
        setContentView(R.layout.activity_search);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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

        final EditText etReceptionDate = (EditText) findViewById(R.id.editTextReceptionDate);
        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        etReceptionDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(myContext, new DatePickerDialog.OnDateSetListener() {
                    // Definir la acción al pulsar OK en el calendario
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        etReceptionDate.setText(String.valueOf(day) + "/" + String.valueOf(month+1) + "/" + String.valueOf(year));
                    }
                },Year, Month, Day);
                datePickerDialog.show();
            }
        });

        presenter = new SearchPresenter(this);
        spinner = (Spinner) findViewById(R.id.searchSpinner);
        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.spinnerSelectItem));
        options.add(getString(R.string.spinnerFuelType1));
        options.add(getString(R.string.spinnerFuelType2));
        options.add(getString(R.string.spinnerFuelType3));

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
    public void closeSearchActivity() {
        finish();
    }
}