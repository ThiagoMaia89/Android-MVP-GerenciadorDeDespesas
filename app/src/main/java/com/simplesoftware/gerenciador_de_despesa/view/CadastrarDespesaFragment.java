package com.simplesoftware.gerenciador_de_despesa.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.simplesoftware.gerenciador_de_despesa.R;
import com.simplesoftware.gerenciador_de_despesa.model.entities.Despesa;
import com.simplesoftware.gerenciador_de_despesa.model.data.DespesaDAO;

import es.dmoral.toasty.Toasty;


public class CadastrarDespesaFragment extends Fragment {

    private EditText et_data, et_nome, et_preco, et_local;
    private DespesaDAO dao;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cadastrar_despesa, container, false);

        et_data = view.findViewById(R.id.et_data);
        et_nome = view.findViewById(R.id.et_nome);
        et_preco = view.findViewById(R.id.et_preco);
        et_local = view.findViewById(R.id.et_local);
        bt_salvar = view.findViewById(R.id.bt_salvar);

        dao = new DespesaDAO(requireActivity().getApplicationContext());

        bt_salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Despesa despesa = new Despesa();
                    despesa.setData(et_data.getText().toString());
                    despesa.setNome(et_nome.getText().toString());
                    despesa.setPreco(Double.parseDouble(et_preco.getText().toString()));
                    despesa.setLocal(et_local.getText().toString());
                    long id = dao.cadastrarDespesa(despesa);

                    Toasty.success(requireActivity().getApplicationContext(), "Despesa gerada com id: " + id, Toast.LENGTH_SHORT, true).show();
//                    Toast.makeText(requireActivity().getApplicationContext(), "Despesa gerada com id: " + id, Toast.LENGTH_SHORT).show();

                    et_nome.setText("");
                    et_preco.setText("");
                    et_nome.requestFocus();


                } catch (Exception e) {
                    Toasty.error(requireActivity().getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT, true).show();
                    //Toast.makeText(requireActivity().getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
}