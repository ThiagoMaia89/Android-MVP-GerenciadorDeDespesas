package com.simplesoftware.gerenciador_de_despesa.model.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.simplesoftware.gerenciador_de_despesa.model.entities.Despesa;

import java.util.ArrayList;

public class DespesaDAO {

    private Conexao conexao;
    private SQLiteDatabase banco;

    public DespesaDAO(Context context) {
        conexao = new Conexao(context);
        banco = conexao.getWritableDatabase();
    }

    public long cadastrarDespesa(Despesa despesa) {
        ContentValues values = new ContentValues();
        values.put("data", despesa.getData());
        values.put("nome", despesa.getNome());
        values.put("preco", despesa.getPreco());
        values.put("local", despesa.getLocal());
        return banco.insert("tb_despesas", null, values);
    }

    public ArrayList<Despesa> buscarDespesas(){
        ArrayList<Despesa> listaDespesas = new ArrayList<>();
        Cursor cursor = banco.query("tb_despesas", new String[]{"id", "preco", "data", "local", "nome"}, null, null, null, null, null);

        while (cursor.moveToNext()){
            Despesa d = new Despesa();
            d.setId(cursor.getInt(0));
            d.setPreco(cursor.getDouble(1));
            d.setData(cursor.getString(2));
            d.setLocal(cursor.getString(3));
            d.setNome(cursor.getString(4));
            listaDespesas.add(d);
        }
        return listaDespesas;
    }

    public void deletarDespesa(Despesa despesa){
        banco.delete("tb_despesas", "id = ?", new String[]{despesa.getId().toString()});

    }


}
