package com.simplesoftware.gerenciador_de_despesa.presenter;

import android.content.Context;

import com.simplesoftware.gerenciador_de_despesa.model.data.DespesaDAO;
import com.simplesoftware.gerenciador_de_despesa.model.entities.Despesa;

import java.util.ArrayList;

public interface BuscarDespesaContract {

    interface MvpView{



    }

    interface Presenter{

        DespesaDAO handleDataFromDB(Context context);

        ArrayList<Despesa> getDataFromDB(Context context);

        double setTotalPrice(double total, ArrayList<Despesa> listaDespesa);

        double setMediaLancamentos(double total, double totalLancamentos);

        double setMediaLista(double total, ArrayList<Despesa> listaFiltrada);

        ArrayList<Despesa> handleFilterList(ArrayList<Despesa> listaDespesas, ArrayList<Despesa> listaFiltrada, String newText);


    }

}
