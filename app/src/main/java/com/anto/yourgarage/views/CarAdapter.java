package com.anto.yourgarage.views;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anto.yourgarage.R;
import com.anto.yourgarage.models.CarEntity;

import java.util.ArrayList;

public class CarAdapter extends RecyclerView.Adapter<CarAdapter.CarViewHolder>
                        implements View.OnClickListener{

    private ArrayList<CarEntity> items;
    private View.OnClickListener listener;

    // Clase interna:
    // Se implementa el ViewHolder que se encargará
    // de almacenar la vista del elemento y sus datos
    public static class CarViewHolder
            extends RecyclerView.ViewHolder {

        private ImageView Image;
        private TextView ownerName;
        private TextView modelName;
        private TextView brandName;
        private TextView enrollmentName;
        private TextView receptionDate;
        private TextView fuelType;
        private TextView carFault;
        private TextView carStatusSerious;


        public CarViewHolder(View itemView) {
            super(itemView);
            Image = (ImageView) itemView.findViewById(R.id.carImage);

            ownerName = (TextView) itemView.findViewById(R.id.ownerName);
            modelName = (TextView) itemView.findViewById(R.id.modelName);
            brandName = (TextView) itemView.findViewById(R.id.brandName);
            enrollmentName = (TextView) itemView.findViewById(R.id.enrollmentName);
            receptionDate = (TextView) itemView.findViewById(R.id.receptionDate);
            fuelType = (TextView) itemView.findViewById(R.id.fuelType);
            carFault = (TextView) itemView.findViewById(R.id.carFault);
            carStatusSerious  = (TextView) itemView.findViewById(R.id.carStatus);
        }

        public void CarBind(CarEntity car) {
            //getImage();
            if(!car.getImage().equals("")){
                byte[] decodedString = Base64.decode(car.getImage(), Base64.DEFAULT);
                Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                Image.setImageBitmap(decodedByte);
            }else{
                Image.setImageDrawable(MyApplication.getContext().getDrawable(R.drawable.about_icon));
            }

            ownerName.setText(car.getName());
            modelName.setText(car.getModelName());
            brandName.setText(car.getBrandName());
            enrollmentName.setText(car.getEnrollmentName());
            // receptionDate.setText(car.getReceptionDate());
            fuelType.setText(car.getFuelType());
            carFault.setText(car.getCarFault());
            carStatusSerious.setText(car.getCarStatus());
        }
    }

    // Contruye el objeto adaptador recibiendo la lista de datos
    public CarAdapter(@NonNull ArrayList<CarEntity> items) {
        this.items = items;
    }

    // Se encarga de crear los nuevos objetos ViewHolder necesarios
    // para los elementos de la colección.
    // Infla la vista del layout, crea y devuelve el objeto ViewHolder
    @Override
    public CarViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.carlist, parent, false);
        row.setOnClickListener(this);

        CarViewHolder cvh = new CarViewHolder(row);
        return cvh;
    }

    // Se encarga de actualizar los datos de un ViewHolder ya existente.
    @Override
    public void onBindViewHolder(CarViewHolder viewHolder, int position) {
        CarEntity item = items.get(position);
        viewHolder.CarBind(item);
    }

    // Indica el número de elementos de la colección de datos.
    @Override
    public int getItemCount() {
        return items.size();
    }

    // Asigna un listener al elemento
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.onClick(view);
    }

}
