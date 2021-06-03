package com.simplesoftware.gerenciador_de_despesa;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.simplesoftware.gerenciador_de_despesa.adapters.RecyclerAdapter;
import com.simplesoftware.gerenciador_de_despesa.entities.Despesa;
import com.simplesoftware.gerenciador_de_despesa.helpers.DespesaDAO;
import java.util.ArrayList;


public class BuscarDespesaFragment extends Fragment {

    private TextView tv_total_lancado, tv_total_lancamentos, tv_media_por_lancamentos, tv_total_lancado_title;
    private SearchView et_buscar;
    private Button bt_copiar, bt_aplicar_busca;
    private RecyclerView rv_despesas;
    private RecyclerAdapter adapter;
    private DespesaDAO dao;
    private ArrayList<Despesa> listaDespesas;
    private double total = 0;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;

    public BuscarDespesaFragment() {
    }

    public static BuscarDespesaFragment newInstance(String param1, String param2) {
        BuscarDespesaFragment fragment = new BuscarDespesaFragment();
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
    public void onResume() {
        super.onResume();
    }



    @SuppressLint("DefaultLocale")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_buscar_despesa, container, false);

        rv_despesas = view.findViewById(R.id.rv_despesas);
        tv_total_lancado = view.findViewById(R.id.tv_total_lancado);
        tv_total_lancamentos = view.findViewById(R.id.tv_total_lancamentos);
        tv_media_por_lancamentos = view.findViewById(R.id.tv_media_por_lancamentos);
        et_buscar = view.findViewById(R.id.et_buscar);
        tv_total_lancado_title = view.findViewById(R.id.tv_total_lancado_title);
        bt_aplicar_busca = view.findViewById(R.id.bt_aplicar_busca);

        dao = new DespesaDAO(requireActivity().getApplicationContext());
        listaDespesas = dao.buscarDespesas();

        adapter = new RecyclerAdapter(requireActivity().getApplicationContext(), listaDespesas);
        rv_despesas.setAdapter(adapter);
        GridLayoutManager layoutManager = new GridLayoutManager(requireActivity().getApplicationContext(), 1);
        rv_despesas.setLayoutManager(layoutManager);

        for (Despesa despesa : listaDespesas) {
            total = total + despesa.getPreco();
        }
        tv_total_lancado.setText(String.format("%.2f", total));
        tv_total_lancamentos.setText(String.valueOf(listaDespesas.size()));

        double totalLancamentos = listaDespesas.size();
        double mediaPorLancamento = total / totalLancamentos;
        tv_media_por_lancamentos.setText(String.format("%.2f", mediaPorLancamento));

        et_buscar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @SuppressLint("SetTextI18n")
            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                ArrayList<Despesa> listaFiltrada = new ArrayList<>();
                total = 0;
                for (Despesa despesa : listaDespesas) {
                    if (despesa.getNome().toLowerCase().trim().contains(newText.toLowerCase().trim())) {
                        listaFiltrada.add(despesa);
                    } else if (despesa.getLocal().toLowerCase().trim().contains(newText.toLowerCase().trim())) {
                        listaFiltrada.add(despesa);
                    } else if (despesa.getData().toLowerCase().trim().contains(newText.toLowerCase().trim())) {
                        listaFiltrada.add(despesa);
                    }
                }

                for (Despesa despesa : listaFiltrada) {
                    total = total + despesa.getPreco();
                }
                tv_total_lancado.setText(String.format("%.2f", total));
                tv_total_lancamentos.setText(String.valueOf(listaFiltrada.size()));
                double media = total / listaFiltrada.size();
                tv_media_por_lancamentos.setText(String.format("%.2f", media));
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        bt_aplicar_busca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.fragContainerView, new BuscarDespesaFragment()).commit();
            }
        });


        return view;

    }
}