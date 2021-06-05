package com.simplesoftware.gerenciador_de_despesa.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.simplesoftware.gerenciador_de_despesa.R;
import com.simplesoftware.gerenciador_de_despesa.presenter.BuscarDespesaContract;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.fragContainerView, new BuscarDespesaFragment()).commit();
        }
    }

    public void irCadastro(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragContainerView, new CadastrarDespesaFragment())
                .commit();

    }

    public void irLancamentos(View view){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragContainerView, new BuscarDespesaFragment())
                .commit();
    }


}