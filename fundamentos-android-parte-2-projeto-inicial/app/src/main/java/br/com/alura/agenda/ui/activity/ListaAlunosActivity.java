package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String TITULO_APPBAR = "Lista de alunos";
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        {
            setContentView(R.layout.activity_lista_alunos);
            String[] alunos = {"Daniel", "Ronaldo", "Jeferson", "Felipe", "Daniel", "Ronaldo", "Jeferson", "Felipe", "Daniel", "Ronaldo", "Jeferson", "Felipe", "Daniel", "Ronaldo", "Jeferson", "Felipe"};
            ListView listaAlunos = (ListView) findViewById(R.id.lista_alunos);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alunos);
            listaAlunos.setAdapter(adapter);
        }
    }
}
