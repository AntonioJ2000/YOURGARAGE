package com.anto.yourgarage.views;

import android.graphics.Color;
import android.os.Bundle;

import com.anto.yourgarage.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
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
        final ImageButton infoButton = findViewById(R.id.infoButton);
        infoButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Toast.makeText(FormActivity.this, "Consideramos como grave aquel vehículo que no puede circular", Toast.LENGTH_LONG).show();
            }
        });



        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        String[] options = {"Seleccione combustible","Gasolina","Diésel","Otro"};
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, options));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id)
            {
                if(pos > 0){
                    Toast.makeText(spinner.getContext(), "Has seleccionado " + spinner.getItemAtPosition(pos).toString(),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(spinner.getContext(), "Nada seleccionado", Toast.LENGTH_LONG).cancel();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {}
        });
        


    }
}