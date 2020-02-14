package br.com.alura.agenda.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import br.com.alura.agenda.database.converter.ConversorCalendar;
import br.com.alura.agenda.database.dao.RoomAlunoDAO;
import br.com.alura.agenda.model.Aluno;

@Database(entities = {Aluno.class}, version = 4, exportSchema = false)
@TypeConverters({ConversorCalendar.class})
public abstract class AgendaDatabase extends RoomDatabase {

    private static final String NOME_BANCO = "agenda.db";

    public abstract RoomAlunoDAO getRoomAlunoDAO();

    public static AgendaDatabase getInstance(Context context){
        return Room
                .databaseBuilder(context, AgendaDatabase.class, NOME_BANCO)
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addMigrations(new Migration(1, 2) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        database.execSQL("ALTER TABLE aluno ADD COLUMN sobrenome TEXT");
                    }
                }, new Migration(2, 3) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        database.execSQL("CREATE TABLE IF NOT EXISTS `Aluno_novo`" +
                                "(`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                                "`nome` TEXT, " +
                                "`telefone` TEXT, " +
                                "`email` TEXT)");

                        database.execSQL("INSERT INTO Aluno_novo (id, nome, telefone, email)" +
                                "SELECT id, nome, telefone, email FROM Aluno");

                        database.execSQL("DROP TABLE Aluno");

                        database.execSQL("ALTER TABLE  Aluno_novo RENAME TO Aluno");
                    }
                }, new Migration(3, 4) {
                    @Override
                    public void migrate(@NonNull SupportSQLiteDatabase database) {
                        database.execSQL("ALTER TABLE Aluno ADD COLUMN momentoDeCadastro INTEGER");
                    }
                })
                .build();
    }
}
