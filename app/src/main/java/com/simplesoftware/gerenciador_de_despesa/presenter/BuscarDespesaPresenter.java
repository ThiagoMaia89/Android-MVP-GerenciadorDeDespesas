package com.simplesoftware.gerenciador_de_despesa.presenter;

import android.content.Context;

import com.simplesoftware.gerenciador_de_despesa.model.data.DespesaDAO;
import com.simplesoftware.gerenciador_de_despesa.model.entities.Despesa;

import java.util.ArrayList;

public class BuscarDespesaPresenter implements BuscarDespesaContract.Presenter {

    private BuscarDespesaContract.MvpView mView;


    public BuscarDespesaPresenter(BuscarDespesaContract.MvpView mView) {
        this.mView = mView;
    }

    @Override
    public DespesaDAO handleDataFromDB(Context context) {

        return new DespesaDAO(context);
    }

    @Override
    public ArrayList<Despesa> getDataFromDB(Context context) {
        return new DespesaDAO(context).buscarDespesas();
    }

    @Override
    public double setTotalPrice(double total, ArrayList<Despesa> listaDespesa) {

        for (Despesa despesa : listaDespesa) {
            total = total + despesa.getPreco();
        }

        return total;
    }

    @Override
    public double setMediaLancamentos(double total, double totalLancamentos) {

        return total / totalLancamentos;
    }

    @Override
    public double setMediaLista(double total, ArrayList<Despesa> listaFiltrada) {
        return total / listaFiltrada.size();
    }

    @Override
    public ArrayList<Despesa> handleFilterList(ArrayList<Despesa> listaDespesas, ArrayList<Despesa> listaFiltrada, String newText) {

        for (Despesa despesa : listaDespesas) {
            if (despesa.getNome().toLowerCase().trim().contains(newText.toLowerCase().trim())) {
                listaFiltrada.add(despesa);
            } else if (despesa.getLocal().toLowerCase().trim().contains(newText.toLowerCase().trim())) {
                listaFiltrada.add(despesa);
            } else if (despesa.getData().toLowerCase().trim().contains(newText.toLowerCase().trim())) {
                listaFiltrada.add(despesa);
            }

        }
        return listaFiltrada;
    }


}
