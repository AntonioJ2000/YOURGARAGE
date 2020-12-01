package com.anto.yourgarage.views;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import com.anto.yourgarage.R;
import com.anto.yourgarage.interfaces.FormInterface;
import com.anto.yourgarage.models.CarEntity;
import com.anto.yourgarage.presenters.FormPresenter;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class FormActivity extends AppCompatActivity implements FormInterface.View {

    private FormInterface.Presenter presenter;

    CarEntity car;
    TextInputEditText ownerNameET;
    TextInputLayout ownerNameTIL;

    TextInputEditText brandNameET;
    TextInputLayout brandNameTIL;

    TextInputEditText modelNameET;
    TextInputLayout modelNameTIL;

    TextInputEditText enrollmentNameET;
    TextInputLayout enrollmentNameTIL;

    EditText receptionDate;

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
        setContentView(R.layout.activity_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Formulario");
            toolbar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Asignar la acción necesaria. En este caso "volver atrás"
                    onBackPressed();
                }
            });
        }

        presenter = new FormPresenter(this);
        spinner = (Spinner) findViewById(R.id.spinner);

        ImageView categoryImage = findViewById(R.id.infoImage);
        categoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(FormActivity.this, "Consideramos como grave aquel vehículo que no puede circular", Toast.LENGTH_LONG).show();
            }
        });

        car = new CarEntity();

        ownerNameET = findViewById(R.id.textInputEditText);
        ownerNameTIL = findViewById(R.id.textInputLayout);

        ownerNameET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(car.setName(ownerNameET.getText().toString()) == false){
                        ownerNameTIL.setError(presenter.getError("ownerName"));
                    }else{
                        ownerNameTIL.setError("");
                    }
                }else{
                    //Log.d
                }
            }
        });

        brandNameET = findViewById(R.id.textInputEditText2);
        brandNameTIL = findViewById(R.id.textInputLayout2);

        brandNameET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(car.setName(brandNameET.getText().toString()) == false){
                        brandNameTIL.setError(presenter.getError("brandName"));
                    }else{
                        brandNameTIL.setError("");
                    }
                }else{
                    //Log.d
                }
            }
        });

        modelNameET = findViewById(R.id.textInputEditText3);
        modelNameTIL = findViewById(R.id.textInputLayout3);

        modelNameET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(car.setName(modelNameET.getText().toString()) == false){
                        modelNameTIL.setError(presenter.getError("modelName"));
                    }else{
                        modelNameTIL.setError("");
                    }
                }else{
                    //Log.d
                }
            }
        });

        enrollmentNameET = findViewById(R.id.textInputEditText4);
        enrollmentNameTIL = findViewById(R.id.textInputLayout4);

        enrollmentNameET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(car.setName(enrollmentNameET.getText().toString()) == false){
                        enrollmentNameTIL.setError(presenter.getError("enrollmentName"));
                    }else{
                        enrollmentNameTIL.setError("");
                    }
                }else{
                    //Log.d
                }
            }
        });

        receptionDate = findViewById(R.id.textDate);

        receptionDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(car.setReceptionDate(receptionDate.getText().toString()) == false){
                        receptionDate.setError(presenter.getError("dateError"));
                    }
                }else{
                    receptionDate.setError("");
                }
            }
        });


        //String[] options = {"Seleccione combustible", "Otro" "Gasolina", "Diésel", "Eléctrico"};
        ArrayList<String> options = new ArrayList<>();
        options.add(getString(R.string.spinnerSelectItem));
        options.add(getString(R.string.spinnerSelectNew));
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
                if(pos == 1){
                    spinnerAdd();

                }else if(pos > 1){
                    Toast.makeText(spinner.getContext(), "Has seleccionado " + spinner.getItemAtPosition(pos).toString(),Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(spinner.getContext(), "Nada seleccionado", Toast.LENGTH_LONG).cancel();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {}
        });



        Button saveCar = findViewById(R.id.saveCar);
        saveCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.onClickSaveCar();
            }
        });


        Button deleteCar = findViewById(R.id.deleteCarButton);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar coche");
        builder.setMessage("¿Estás seguro de que quieres eliminar el coche seleccionado?");

        builder.setPositiveButton("Eliminar", null);
        builder.setNegativeButton("Cancelar", null);
        final AlertDialog dialog = builder.create();

        deleteCar.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  dialog.show();
              }
        });



        ImageView dateImage = findViewById(R.id.imageView7);
        final EditText textDate = findViewById(R.id.textDate);

        calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR);
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);

        dateImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog = new DatePickerDialog(myContext, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        textDate.setText(String.valueOf(day) + "/" + String.valueOf(month + 1) + "/" + String.valueOf(year));
                    }
                },Year, Month, Day);
                datePickerDialog.show();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_help, menu);
        return true;
    }

    public void spinnerAdd(){
        LayoutInflater layoutActivity = LayoutInflater.from(myContext);
        View viewAlertDialog = layoutActivity.inflate(R.layout.alert_dialog, null);

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(myContext);
        alertDialog.setView(viewAlertDialog);
        final EditText dialogInput = (EditText) viewAlertDialog.findViewById(R.id.dialogInput);

        alertDialog
                .setCancelable(false)
                // Botón Añadir
                .setPositiveButton(getResources().getString(R.string.add),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                adapter.add(dialogInput.getText().toString());
                                spinner.setSelection(adapter.getPosition(dialogInput.getText().toString()));
                            }
                        })
                // Botón Cancelar
                .setNegativeButton(getResources().getString(R.string.cancel),
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogBox, int id) {
                                spinner.setSelection(adapter.getPosition(getString(R.string.spinnerSelectItem)));
                                dialogBox.cancel();
                            }
                        }).create().show();
    }

    @Override
    public void closeFormActivity() {
        finish();
    }
}