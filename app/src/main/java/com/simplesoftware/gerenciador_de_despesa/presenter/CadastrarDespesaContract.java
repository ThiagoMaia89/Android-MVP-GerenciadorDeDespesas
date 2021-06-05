package com.simplesoftware.gerenciador_de_despesa.presenter;

import android.content.Context;
import android.widget.EditText;

import com.simplesoftware.gerenciador_de_despesa.model.data.DespesaDAO;
import com.simplesoftware.gerenciador_de_despesa.model.entities.Despesa;

public interface CadastrarDespesaContract {

    interface MvpView{}


    interface Presenter{

        long dataBaseInsert(Context context, Despesa despesa);

        void createNewDespesa(Despesa despesa, EditText et_data, EditText et_nome, EditText et_preco, EditText et_local);

        void putSlashOnDate(EditText et_data);

    }
}
