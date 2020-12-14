package com.anto.yourgarage.views;

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

        private ImageView carImage;
        private TextView ownerName;
        private TextView modelName;

        public CarViewHolder(View itemView) {
            super(itemView);
            carImage = (ImageView) itemView.findViewById(R.id.carImage);
            ownerName = (TextView) itemView.findViewById(R.id.ownerName);
            modelName = (TextView) itemView.findViewById(R.id.modelName);
        }

        public void CarBind(CarEntity item) {
            //getImage();
            ownerName.setText(item.getOwnerName());
            modelName.setText(item.getModelName());
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
