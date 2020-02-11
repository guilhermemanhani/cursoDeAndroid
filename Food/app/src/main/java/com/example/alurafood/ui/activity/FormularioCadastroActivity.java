package com.example.alurafood.ui.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.alurafood.R;
import com.example.alurafood.ui.activity.validator.ValidaCpf;
import com.example.alurafood.ui.activity.validator.ValidaTelefoneComDDD;
import com.example.alurafood.ui.activity.validator.ValidacaoPadrao;
import com.google.android.material.textfield.TextInputLayout;

import br.com.caelum.stella.format.CPFFormatter;


public class FormularioCadastroActivity extends AppCompatActivity {

    public static final  String ERRO_FORMATAO_CPF = "erro formatacao cpf";

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
    }

    private void configuraSenha() {
        TextInputLayout textInputSenha = findViewById(R.id.formulario_senha);
        adicionaValidacaoPadrao(textInputSenha);
    }

    private void configuraEmail() {
        TextInputLayout textInputTelefone = findViewById(R.id.formulario_email);
        adicionaValidacaoPadrao(textInputTelefone);
    }

    private void configuraTelefone() {
        TextInputLayout textInputTelefone = findViewById(R.id.formulario_telefone);
        EditText campoTelefoneComDDD = textInputTelefone.getEditText();
        final ValidaTelefoneComDDD validador = new ValidaTelefoneComDDD(textInputTelefone);
        campoTelefoneComDDD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
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
        campoCpf.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    adicionaFormatacao(formatador, campoCpf);
                } else {
                    validador.estaValido();
                }
            }
        });
    }

    private void adicionaFormatacao(CPFFormatter formatador,  EditText campoCpf) {
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
