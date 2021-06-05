package com.simplesoftware.gerenciador_de_despesa.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.simplesoftware.gerenciador_de_despesa.R;
import com.simplesoftware.gerenciador_de_despesa.model.entities.Despesa;
import com.simplesoftware.gerenciador_de_despesa.model.data.DespesaDAO;
import com.simplesoftware.gerenciador_de_despesa.presenter.CadastrarDespesaContract;
import com.simplesoftware.gerenciador_de_despesa.presenter.CadastrarDespesaPresenter;

import es.dmoral.toasty.Toasty;


public class CadastrarDespesaFragment extends Fragment implements CadastrarDespesaContract.MvpView {

    CadastrarDespesaPresenter mPresenter;

    private EditText et_data, et_nome, et_preco, et_local;
    private Button bt_salvar;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    public CadastrarDespesaFragment() {
        // Required empty public constructor
    }


    public static CadastrarDespesaFragment newInstance(String param1, String param2) {
        CadastrarDespesaFragment fragment = new CadastrarDespesaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    public void instanciarComponentes(View view){
        et_data = view.findViewById(R.id.et_data);
        et_nome = view.findViewById(R.id.et_nome);
        et_preco = view.findViewById(R.id.et_preco);
        et_local = view.findViewById(R.id.et_local);
        bt_salvar = view.findViewById(R.id.bt_salvar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastrar_despesa, container, false);

        mPresenter = new CadastrarDespesaPresenter(this);

        instanciarComponentes(view);
        mPresenter.putSlashOnDate(et_data);
        bt_salvar.setOnClickListener(v -> {
            try {

                Despesa despesa = new Despesa();

                mPresenter.createNewDespesa(despesa, et_data, et_nome, et_preco, et_local);
                mPresenter.dataBaseInsert(requireActivity().getApplicationContext(), despesa);
                Toasty.success(requireActivity().getApplicationContext(), "Despesa gerada com sucesso!", Toast.LENGTH_SHORT, true).show();

                et_nome.setText("");
                et_preco.setText("");
                et_nome.requestFocus();


            } catch (Exception e) {

                Toasty.error(requireActivity().getApplicationContext(), "Preencha todos os campos.", Toast.LENGTH_SHORT, true).show();

            }
        });

        return view;
    }
}