package com.example.alurafood.ui.activity.validator;

import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.tinytype.CPF;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidaCpf implements Validador{

    private final TextInputLayout textInputCpf;
    private final EditText campoCpf;
    private final ValidacaoPadrao validacaoPadrao;
    private final CPFFormatter formatador = new CPFFormatter();
    public static final  String ERRO_FORMATAO_CPF = "erro formatacao cpf";

    public ValidaCpf(TextInputLayout textInputCpf){
        this.textInputCpf = textInputCpf;
        this.campoCpf = textInputCpf.getEditText();
        this.validacaoPadrao = new ValidacaoPadrao(textInputCpf);
    }

    private boolean validaCalculoCpf(String cpf){
        try {
            CPFValidator cpfValidator = new CPFValidator();
            cpfValidator.assertValid(cpf);
        } catch (InvalidStateException e) {
            textInputCpf.setError("CPF invalido");
            return false;
        }
        return true;
    }

    private boolean validaCampoComOnzeDigitos(String cpf) {
        if(cpf.length() != 11){
            textInputCpf.setError("O CPF precisa ter 11 digitos");
            return false;
        }
        return true;
    }

    @Override
    public boolean estaValido() {
        if(!validacaoPadrao.estaValido()) return false;
        String cpf = getCpf();
        String cpfSemFormato = cpf;
        try {
            cpfSemFormato = formatador.unformat(cpf);
        } catch (IllegalArgumentException e){
            Log.e(ERRO_FORMATAO_CPF, e.getMessage());
        }
        if(!validaCampoComOnzeDigitos(cpfSemFormato)) return false;
        if(!validaCalculoCpf(cpfSemFormato)) return false;
        adicionaFormatacao(cpfSemFormato);
        return true;
    }

    private void adicionaFormatacao(String cpf) {
        String cpfFormatado = formatador.format(cpf);
        campoCpf.setText(cpfFormatado);
    }

    @NonNull
    private  String getCpf(){
        return campoCpf.getText().toString();
    }
}
