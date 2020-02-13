package com.example.alurafood.ui.activity.validator;

import android.widget.EditText;

import com.example.alurafood.ui.activity.formatter.FormataTelefoneComDdd;
import com.google.android.material.textfield.TextInputLayout;

public class ValidaTelefoneComDDD {

    public  static final String DEVE_TER_DEZ_OU_ONZE_DIGITOS = "Telefone deve ter entre 10 a 11 digitos";
    private final TextInputLayout textInputTelefoneComDDD;
    private final EditText campoTelefoneComDDD;
    private final ValidacaoPadrao validacaoPadrao;
    final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();

    public  ValidaTelefoneComDDD(TextInputLayout textInputTelefoneComDDD) {
        this.textInputTelefoneComDDD = textInputTelefoneComDDD;
        this.campoTelefoneComDDD = textInputTelefoneComDDD.getEditText();
        this.validacaoPadrao = new ValidacaoPadrao(textInputTelefoneComDDD);
    }

    private boolean validaEntreDezOuOnzeDigitos (String telefoneComDDD) {
        int digitos = telefoneComDDD.length();
        if(digitos < 10 || digitos > 11) {
            textInputTelefoneComDDD.setError(DEVE_TER_DEZ_OU_ONZE_DIGITOS);
            return false;
        }
        return true;
    }

    public boolean estaValido(){
        if(!validacaoPadrao.estaValido()) return false;
        String telefoneComDDD = campoTelefoneComDDD.getText().toString();
        if(!validaEntreDezOuOnzeDigitos(telefoneComDDD)) return false;
        adicionaFormatacao(telefoneComDDD);
        return true;
    }

    public void adicionaFormatacao(String telefonecomDdd) {
        String telefoneformatado = formatador.formata(telefonecomDdd);
        campoTelefoneComDDD.setText(telefoneformatado);
    }


}
