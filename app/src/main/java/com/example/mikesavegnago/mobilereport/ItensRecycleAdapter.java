package com.example.mikesavegnago.mobilereport;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by Mike on 17/11/15.
 */
public class ItensRecycleAdapter extends RecyclerView.Adapter<ItensRecycleAdapter.MyViewHolder> {

    private List<Itens> itensList;
    private LayoutInflater mLayoutInflater;
    private DecimalFormat formato = new DecimalFormat("#.00");

    public ItensRecycleAdapter(Context context, List<Itens> list) {
        itensList = list;
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View v = mLayoutInflater.inflate(R.layout.item_recycle_view, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.textNome.setText(itensList.get(position).getDescricao());
        holder.textCodigo.setText(String.valueOf(formato.format(itensList.get(position).getValor())));
    }

    @Override
    public int getItemCount() {
        return itensList.size();
    }

    public void addListItem(Itens i, int position) {
        itensList.add(i);
        notifyItemInserted(position);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        //public ImageView imgViewRotas;
        public TextView textNome;
        public TextView textCodigo;

        public MyViewHolder(View itemView) {
            super(itemView);

            textNome = (TextView) itemView.findViewById(R.id.text_nome);
            textCodigo = (TextView) itemView.findViewById(R.id.text_codigo);
        }
    }
}
