package com.simplesoftware.gerenciador_de_despesa.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.simplesoftware.gerenciador_de_despesa.R;
import com.simplesoftware.gerenciador_de_despesa.model.entities.Despesa;
import com.simplesoftware.gerenciador_de_despesa.model.data.DespesaDAO;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {

    private ArrayList<Despesa> listaDespesas;
    private ArrayList<Despesa> listaDespesasFull;
    private Context context;


    public RecyclerAdapter(Context context, ArrayList<Despesa> listaDespesas) {
        this.context = context;
        this.listaDespesas = listaDespesas;
        listaDespesasFull = listaDespesas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        DespesaDAO dao = new DespesaDAO(this.context);
        Despesa despesa = listaDespesas.get(position);
        holder.rv_image.setImageResource(R.drawable.icon_despesa);
        holder.rv_data.setText(despesa.getData());
        holder.rv_local.setText(despesa.getLocal());
        holder.rv_nome.setText(despesa.getNome());
        holder.rv_preco.setText(String.valueOf(despesa.getPreco()));

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                new AlertDialog.Builder(v.getContext())
                        .setIcon(R.drawable.icon_info)
                        .setTitle("Remover")
                        .setMessage("Deseja apagar este item?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        dao.deletarDespesa(despesa);
                                        listaDespesas.remove(position);
                                        notifyItemRemoved(position);
                                        notifyDataSetChanged();

                                        Toasty.success(context, "Registro excluido com sucesso!", Toast.LENGTH_SHORT).show();

                                    }
                                })
                        .setNegativeButton("Não", null)
                        .show();

                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaDespesas.size();
    }

    @Override
    public Filter getFilter() {
        return filtro;
    }

    private Filter filtro = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<Despesa> listaFiltrada = new ArrayList<>();

            if ((constraint == null || constraint.length() == 0)) {
                listaFiltrada.addAll(listaDespesasFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Despesa despesa : listaDespesasFull) {
                    if (despesa.getNome().toLowerCase().contains(filterPattern)) {
                        listaFiltrada.add(despesa);
                    } else if (despesa.getLocal().toLowerCase().contains(filterPattern)) {
                        listaFiltrada.add(despesa);
                    } else if (despesa.getData().toLowerCase().contains(filterPattern)) {
                        listaFiltrada.add(despesa);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = listaFiltrada;

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            listaDespesas = (ArrayList<Despesa>) results.values;
            notifyDataSetChanged();
        }
    };

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView rv_image;
        TextView rv_data, rv_local, rv_nome, rv_preco;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rv_image = itemView.findViewById(R.id.rv_image);
            rv_data = itemView.findViewById(R.id.rv_data);
            rv_local = itemView.findViewById(R.id.rv_local);
            rv_nome = itemView.findViewById(R.id.rv_nome);
            rv_preco = itemView.findViewById(R.id.rv_preco);

        }
    }

}
