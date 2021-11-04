package ar.test.banco;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {


    private List<ListElement> mData;
    private LayoutInflater mInflater;
    private Context context;


    public ListAdapter(List<ListElement> itemList, Context context) {

        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;

    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) { //

        View view = mInflater.inflate(R.layout.list_element, null);


        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() { // nos da el tamaño de la lista
        return mData.size();
    }

    public void setItems(List<ListElement> items){ mData = items;}
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView inconImage;
        TextView status,saldo,fecha;

        ViewHolder(View itemView){
           super(itemView);
            inconImage = itemView.findViewById(R.id.iconImageView);
            saldo = itemView.findViewById(R.id.saldo);
            status = itemView.findViewById(R.id.status);
            fecha = itemView.findViewById(R.id.fecha);

        }

        void bindData(final ListElement item){
            inconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
            status.setText(item.getStatus());
            fecha.setText(item.getFecha());
            saldo.setText(item.getSaldo());
        }

    }


}
