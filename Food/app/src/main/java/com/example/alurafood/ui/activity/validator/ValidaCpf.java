package com.example.alurafood.ui.activity.validator;

import android.widget.EditText;

import androidx.annotation.NonNull;

import com.google.android.material.textfield.TextInputLayout;

import br.com.caelum.stella.format.CPFFormatter;
import br.com.caelum.stella.validation.CPFValidator;
import br.com.caelum.stella.validation.InvalidStateException;

public class ValidaCpf {

    private final TextInputLayout textInputCpf;
    private final EditText campoCpf;
    private final ValidacaoPadrao validacaoPadrao;
    private final CPFFormatter formatador = new CPFFormatter();

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

    public boolean estaValido() {
        if(!validacaoPadrao.estaValido()) return false;
        String cpf = getCpf();
        if(!validaCampoComOnzeDigitos(cpf)) return false;
        if(!validaCalculoCpf(cpf)) return false;
        adicionaFormatacao(cpf);
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
