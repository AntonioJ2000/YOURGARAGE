package com.anto.yourgarage.views;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.anto.yourgarage.R;
import com.anto.yourgarage.interfaces.FormInterface;
import com.anto.yourgarage.models.CarEntity;
import com.anto.yourgarage.models.CarModel;
import com.anto.yourgarage.presenters.FormPresenter;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

import io.realm.Realm;


public class FormActivity extends AppCompatActivity implements FormInterface.View {

    private FormInterface.Presenter presenter;

    private static final int REQUEST_SELECT_IMAGE = 201;
    final private int CODE_WRITE_EXTERNAL_STORAGE_PERMISSION = 123;

    private ConstraintLayout constraintLayoutFormActivity;

    private CarEntity car;

    private String id;

    ImageView imageView;

    TextInputEditText ownerNameET;
    TextInputLayout ownerNameTIL;

    TextInputEditText brandNameET;
    TextInputLayout brandNameTIL;

    TextInputEditText modelNameET;
    TextInputLayout modelNameTIL;

    TextInputEditText enrollmentNameET;
    TextInputLayout enrollmentNameTIL;

    TextInputEditText receptionDateET;
    TextInputLayout receptionDateTIL;

    TextInputEditText carFaultET;
    TextInputLayout carFaultTIL;

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


        Button addImage = findViewById(R.id.addImage);
        addImage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                presenter.addImage();
            }
        });

        Button deleteImage = findViewById(R.id.deleteImage);
        deleteImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView = findViewById(R.id.imageView);
                imageView.setImageDrawable(MyApplication.getContext().getDrawable(R.drawable.about_icon));
            }
        });



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
                        brandNameTIL.setError("");
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
                    if(car.setBrandName(brandNameET.getText().toString()) == false){
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
                    if(car.setModelName(modelNameET.getText().toString()) == false){
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
                    if(car.setEnrollmentName(enrollmentNameET.getText().toString()) == false){
                        enrollmentNameTIL.setError(presenter.getError("enrollmentName"));
                    }else{
                        enrollmentNameTIL.setError("");
                    }
                }else{
                    //Log.d
                }
            }
        });

        receptionDateET = findViewById(R.id.textInputEditText7);
        receptionDateTIL = findViewById(R.id.textInputLayout7);

        receptionDateET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(car.setReceptionDate(receptionDateET.getText().toString()) == false){
                        receptionDateTIL.setError(presenter.getError("dateError"));
                    }else{
                       receptionDateTIL.setError("");
                    }
                }else{
                    //Log.d
                }
            }
        });

        carFaultET = findViewById(R.id.textInputEditText5);
        carFaultTIL = findViewById(R.id.textInputLayout5);

        carFaultET.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    if(car.setCarFault(carFaultET.getText().toString()) == false){
                        carFaultTIL.setError(presenter.getError("faultError"));
                    }else{
                        receptionDateTIL.setError("");
                    }
                }
            }
        });

        final ToggleButton carStatus = findViewById(R.id.estadoButton);
        carStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(carStatus.isChecked()){
                    car.setCarStatus(true);
                }
            }
        });

        //String[] options = {"Seleccione combustible", "Otro" "Gasolina", "Diésel", "Eléctrico"};
        ArrayList<String> options = presenter.getAllFuelTypes();

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
                    car.setFuelType(spinner.getItemAtPosition(pos).toString());

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

                if(id != null){
                    //car = CarModel.getCarByID(id);
                    CarEntity newCar = new CarEntity();

                        if(newCar.setName(ownerNameET.getText().toString()) &&
                                newCar.setModelName(modelNameET.getText().toString()) &&
                                newCar.setBrandName(brandNameET.getText().toString()) &&
                                newCar.setEnrollmentName(enrollmentNameET.getText().toString()) &&
                                newCar.setReceptionDate(receptionDateET.getText().toString()) &&
                                spinner.getSelectedItemPosition() != 0 &&
                                newCar.setCarFault(carFaultET.getText().toString())){


                            imageView = findViewById(R.id.imageView);
                            if(imageView.getDrawable() == null || ((BitmapDrawable)imageView.getDrawable()).getBitmap() == null){
                                newCar.setImage("");
                            }else{
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                ((BitmapDrawable)imageView.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                                byte[] byteArray = byteArrayOutputStream.toByteArray();
                                String imageInBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                                newCar.setImage(imageInBase64);
                            }
                            newCar.setFuelType(spinner.getSelectedItem().toString());

                            if(carStatus.isChecked()){
                                newCar.setCarStatus(true);
                                System.out.println(newCar.getCarStatus());
                            }


                            newCar.setId(id);
                            System.out.println(newCar.getCarStatus());
                            presenter.onClickUpdateCar(newCar);
                        }
                }else{
                if(car.setName(ownerNameET.getText().toString()) &&
                        car.setModelName(modelNameET.getText().toString()) &&
                        car.setBrandName(brandNameET.getText().toString()) &&
                        car.setEnrollmentName(enrollmentNameET.getText().toString()) &&
                        car.setReceptionDate(receptionDateET.getText().toString()) &&
                        spinner.getSelectedItemPosition() != 0 &&
                        car.setCarFault(carFaultET.getText().toString())){

                    imageView = findViewById(R.id.imageView);
                    if(imageView.getDrawable() == null || ((BitmapDrawable)imageView.getDrawable()).getBitmap() == null){
                        car.setImage("");
                    }else{
                        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                        ((BitmapDrawable)imageView.getDrawable()).getBitmap().compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        String imageInBase64 = Base64.encodeToString(byteArray, Base64.DEFAULT);
                        car.setImage(imageInBase64);
                    }
                    car.setFuelType(spinner.getSelectedItem().toString());

                    presenter.onClickSaveCar(car);
                    }

                }
            }
        });


        Button deleteCar = findViewById(R.id.deleteCarButton);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Eliminar coche");
        builder.setMessage("¿Estás seguro de que quieres eliminar el coche seleccionado?");

        builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                presenter.deleteCar(id);
                closeFormActivity();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        final AlertDialog dialog = builder.create();

        deleteCar.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  dialog.show();

              }
        });



        ImageView dateImage = findViewById(R.id.imageView7);
        final EditText textDate = findViewById(R.id.textInputEditText7);

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

        //Pillas la id si un elemento ha sido seleccionado.
        id = getIntent().getStringExtra("id"); //Es nulo si el boton es flotante.

        if(id != null){
            Log.d("HEY","No soy nulo");
            CarEntity myCar = CarModel.getCarByID(id);

            //Rellenar campos.
            imageView = findViewById(R.id.imageView);
            if(!myCar.getImage().equals("")){
                byte[] decodedString = Base64.decode(myCar.getImage(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                imageView.setImageBitmap(decodedByte);
            }else{
                imageView.setImageDrawable(MyApplication.getContext().getDrawable(R.drawable.about_icon));
            }

            ownerNameET.setText(myCar.getName());
            brandNameET.setText(myCar.getBrandName());
            modelNameET.setText(myCar.getModelName());
            spinner.setSelection(getIndex(spinner, myCar.getFuelType()));
            enrollmentNameET.setText(myCar.getEnrollmentName());
            receptionDateET.setText(myCar.getReceptionDate());

            if(myCar.getCarStatus() == "Grave"){
                 carStatus.setChecked(true);
            } else {
                carStatus.setChecked(false);
            }
            carFaultET.setText(myCar.getCarFault());

        }else{
            Log.d("HEY","Soy nulo");
            //Deshabilitar el botón eliminar ya que estamos creando un objeto nuevo.
            deleteCar.setEnabled(false);

        }

    }

    private int getIndex(Spinner spinner, String myString){

        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).equals(myString)){
                index = i;
            }
        }
        return index;
    }

    private void selectPicture(){
        // Se le pide al sistema una imagen del dispositivo
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(intent, getResources().getString(R.string.choose_picture)),
                REQUEST_SELECT_IMAGE);
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

    @Override
    public void addImage() {
        int WriteExternalStoragePermission = ContextCompat.checkSelfPermission(myContext, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.d("MainActivity", "WriteExternalStoragePermission: " + WriteExternalStoragePermission);
        if(WriteExternalStoragePermission != -1){
            Toast.makeText(FormActivity.this, "Por favor, seleccione una imagen", Toast.LENGTH_SHORT).show();
            selectPicture();
        } else {
            if (WriteExternalStoragePermission != PackageManager.PERMISSION_GRANTED) {
                // Permiso denegado
                // A partir de Marshmallow (6.0) se pide aceptar o rechazar el permiso en tiempo de ejecución
                // En las versiones anteriores no es posible hacerlo
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    ActivityCompat.requestPermissions(FormActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, CODE_WRITE_EXTERNAL_STORAGE_PERMISSION);
                    // Una vez que se pide aceptar o rechazar el permiso se ejecuta el método "onRequestPermissionsResult" para manejar la respuesta
                    // Si el usuario marca "No preguntar más" no se volverá a mostrar este diálogo
                } else {
                    Snackbar.make(constraintLayoutFormActivity, getResources().getString(R.string.write_permission_denied), Snackbar.LENGTH_LONG)
                            .show();
                }
            } else {
                // Permiso aceptado
                Snackbar.make(constraintLayoutFormActivity, getResources().getString(R.string.write_permission_granted), Snackbar.LENGTH_LONG)
                        .show();

            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (REQUEST_SELECT_IMAGE):
                if (resultCode == Activity.RESULT_OK) {
                    // Se carga la imagen desde un objeto Bitmap
                    Uri selectedImage = data.getData();
                    String selectedPath = selectedImage.getPath();

                    if (selectedPath != null) {
                        // Se leen los bytes de la imagen
                        InputStream imageStream = null;
                        try {
                            imageStream = getContentResolver().openInputStream(selectedImage);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                        // Se transformam los bytes de la imagen a un Bitmap
                        Bitmap bmp = BitmapFactory.decodeStream(imageStream);

                        // Se carga el Bitmap en el ImageView
                        imageView = findViewById(R.id.imageView);
                        imageView.setImageBitmap(bmp);
                    }
                }
                break;
        }
    }
}