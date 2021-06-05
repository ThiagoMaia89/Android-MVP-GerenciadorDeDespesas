package com.simplesoftware.gerenciador_de_despesa.presenter;

import android.content.Context;

import com.simplesoftware.gerenciador_de_despesa.model.data.DespesaDAO;
import com.simplesoftware.gerenciador_de_despesa.model.entities.Despesa;

public interface CadastrarDespesaContract {

    interface MvpView{}


    interface Presenter{

        long handleWithDataBaseInsert(Context context, Despesa despesa);


    }
}
