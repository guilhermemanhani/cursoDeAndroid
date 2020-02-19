package br.com.alura.agenda.ui.asynctask;

import android.os.AsyncTask;

import java.util.List;

import br.com.alura.agenda.database.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;
import br.com.alura.agenda.ui.adapter.ListaAlunosAdapter;

public class BuscaAlunoTask extends AsyncTask<Void, Void, List<Aluno>> {

    private final AlunoDAO dao;
    private final ListaAlunosAdapter adapter;

    public BuscaAlunoTask(AlunoDAO dao, ListaAlunosAdapter adapter) {

        this.dao = dao;
        this.adapter = adapter;
    }

    @Override
    protected List<Aluno> doInBackground(Void[] objects) {
        List<Aluno> todosAlunos = dao.todos();
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        adapter.atualiza(todosAlunos);
        return todosAlunos;
    }

    @Override
    protected void onPostExecute (List<Aluno> todosAlunos) {
        super.onPostExecute(todosAlunos);
        adapter.atualiza(todosAlunos);
    }
}
