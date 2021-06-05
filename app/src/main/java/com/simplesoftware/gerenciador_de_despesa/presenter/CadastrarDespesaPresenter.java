package com.simplesoftware.gerenciador_de_despesa.presenter;

import android.content.Context;

import com.simplesoftware.gerenciador_de_despesa.model.data.DespesaDAO;
import com.simplesoftware.gerenciador_de_despesa.model.entities.Despesa;

public class CadastrarDespesaPresenter implements CadastrarDespesaContract.Presenter {

    private CadastrarDespesaContract.MvpView mview;

    public CadastrarDespesaPresenter(CadastrarDespesaContract.MvpView mview) {
        this.mview = mview;
    }

    @Override
    public long handleWithDataBaseInsert(Context context, Despesa despesa) {

        DespesaDAO dao = new DespesaDAO(context);
        return dao.cadastrarDespesa(despesa);

    }
}
