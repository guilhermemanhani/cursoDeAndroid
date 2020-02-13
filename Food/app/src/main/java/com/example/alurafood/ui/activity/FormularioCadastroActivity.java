package com.example.alurafood.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.alurafood.R;
import com.example.alurafood.ui.activity.formatter.FormataTelefoneComDdd;
import com.example.alurafood.ui.activity.validator.ValidaEmail;
import com.example.alurafood.ui.activity.validator.ValidaCpf;
import com.example.alurafood.ui.activity.validator.ValidaTelefoneComDDD;
import com.example.alurafood.ui.activity.validator.ValidacaoPadrao;
import com.example.alurafood.ui.activity.validator.Validador;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.stella.format.CPFFormatter;


public class FormularioCadastroActivity extends AppCompatActivity {

    public static final  String ERRO_FORMATAO_CPF = "erro formatacao cpf";
    private final List<Validador> validadores = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cadastro);

        inicializaCampos();
    }

    private void inicializaCampos() {
        configuraNomeCompleto();
        configuraCpf();
        configuraEmail();
        configuraTelefone();
        configuraSenha();
        configuraBotao();
    }

    private void configuraBotao() {
        final Button botaoCadastrar = findViewById(R.id.formulario_botao_cadastrar);
        botaoCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean formularioEstaValido = true;
                for (Validador validador : validadores) {
                    if(!validador.estaValido()){
                        formularioEstaValido = false;
                    }
                }
                if(formularioEstaValido) {
                    Toast.makeText(
                            FormularioCadastroActivity.this,
                            "Cadastro realizado com sucesso!",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void configuraSenha() {
        TextInputLayout textInputSenha = findViewById(R.id.formulario_senha);
        adicionaValidacaoPadrao(textInputSenha);
    }

    private void configuraEmail() {
        TextInputLayout textInputEmail = findViewById(R.id.formulario_email);
        EditText campoEmail = textInputEmail.getEditText();
        final ValidaEmail validador = new ValidaEmail(textInputEmail);
        validadores.add(validador);
        campoEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    validador.estaValido();
                }
//                adicionaValidacaoPadrao(textInputTelefone);
            }
        });

    }

    private void configuraTelefone() {
        TextInputLayout textInputTelefone = findViewById(R.id.formulario_telefone);
        final EditText campoTelefoneComDDD = textInputTelefone.getEditText();
        final ValidaTelefoneComDDD validador = new ValidaTelefoneComDDD(textInputTelefone);
        final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();
        validadores.add(validador);
        campoTelefoneComDDD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                String telefoneComDDD = campoTelefoneComDDD.getText().toString();
                if(hasFocus){
                    String telefoneComDDDSemFormatacao = formatador.remove(telefoneComDDD);
                    campoTelefoneComDDD.setText(telefoneComDDDSemFormatacao);
                } else {
                    validador.estaValido();
                }
            }
        });
    }

    private void configuraCpf() {
        TextInputLayout textInputCpf = findViewById(R.id.formulario_cpf);
        final EditText campoCpf = textInputCpf.getEditText();
        final CPFFormatter formatador = new CPFFormatter();
        final ValidaCpf validador = new ValidaCpf(textInputCpf);
        validadores.add(validador);
        campoCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    removeFormatacao(formatador, campoCpf);
                } else {
                    validador.estaValido();
                }
            }
        });
    }

    private void removeFormatacao(CPFFormatter formatador,  EditText campoCpf) {
        String cpf = campoCpf.getText().toString();
        try {
            String cpfSemFormato = formatador.unformat(cpf);
            campoCpf.setText(cpfSemFormato);
        } catch (IllegalArgumentException e){
            Log.e(ERRO_FORMATAO_CPF, e.getMessage());
        }
    }

    private void configuraNomeCompleto() {
        TextInputLayout textInputNomeCompleto = findViewById(R.id.formulario_nome_completo);
        adicionaValidacaoPadrao(textInputNomeCompleto);
    }

    private void adicionaValidacaoPadrao(final TextInputLayout textInputCampo) {
        final EditText campo = textInputCampo.getEditText();
        final ValidacaoPadrao validador = new ValidacaoPadrao(textInputCampo);
        validadores.add(validador);
        campo.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus) {
                    validador.estaValido();
                }
            }
        });
    }


}
