package com.simplesoftware.gerenciador_de_despesa.presenter;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.simplesoftware.gerenciador_de_despesa.model.data.DespesaDAO;
import com.simplesoftware.gerenciador_de_despesa.model.entities.Despesa;

public class CadastrarDespesaPresenter implements CadastrarDespesaContract.Presenter {

    private CadastrarDespesaContract.MvpView mview;

    public CadastrarDespesaPresenter(CadastrarDespesaContract.MvpView mview) {
        this.mview = mview;
    }

    @Override
    public long dataBaseInsert(Context context, Despesa despesa) {

        DespesaDAO dao = new DespesaDAO(context);
        return dao.cadastrarDespesa(despesa);

    }

    @Override
    public void createNewDespesa(Despesa despesa, EditText et_data, EditText et_nome, EditText et_preco, EditText et_local) {
        despesa.setData(et_data.getText().toString());
        despesa.setNome(et_nome.getText().toString());
        despesa.setPreco(Double.parseDouble(et_preco.getText().toString()));
        despesa.setLocal(et_local.getText().toString());
    }

    @Override
    public void putSlashOnDate(EditText et_data) {
        et_data.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = et_data.getText().toString();
                if (et_data.getText().length() == 3) {
                    et_data.setText(new StringBuilder(et_data.getText().toString()).insert(str.length() - 1, "/").toString());
                    et_data.setSelection(et_data.getText().length());
                }
                if (et_data.getText().length() == 6) {
                    et_data.setText(new StringBuilder(et_data.getText().toString()).insert(str.length() - 1, "/").toString());
                    et_data.setSelection(et_data.getText().length());
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }
}
