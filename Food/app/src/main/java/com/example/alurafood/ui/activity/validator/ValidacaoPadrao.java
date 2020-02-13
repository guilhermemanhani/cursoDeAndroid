package com.example.alurafood.ui.activity.validator;

import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class ValidacaoPadrao implements Validador  {

    public static final  String CAMPO_OBRIGATORIO = "Campo Obrigatorio";
    final private TextInputLayout textInputCampo;
    private final EditText campo;

    public ValidacaoPadrao(TextInputLayout textInputCampo) {
        this.textInputCampo = textInputCampo;
        this.campo = this.textInputCampo.getEditText();
    }

    private boolean validaCampoObrigatorio() {
        String  texto = campo.getText().toString();
        if(texto.isEmpty()){
            textInputCampo.setError(CAMPO_OBRIGATORIO);
            return false;
        }
        return true;
    }

    @Override
    public boolean estaValido(){
        if(!validaCampoObrigatorio()) return false;
        removeErro();
        return true;
    }

    private void removeErro() {
        textInputCampo.setError(null);
        textInputCampo.setErrorEnabled(false);
    }
}
