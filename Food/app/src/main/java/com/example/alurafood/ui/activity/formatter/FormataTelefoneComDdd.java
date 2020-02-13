package com.example.alurafood.ui.activity.formatter;

public class FormataTelefoneComDdd {

    public String formata(String telefonecomDdd){
        return telefonecomDdd
                .replaceAll("([0-9]{2})([0-9]{4,5})([0-9]{4})", "($1) $2 - $3");
    }

    public String remove(String telefone) {
        return telefone.replace("(","")
                .replace(")", "")
                .replace(" ","")
                .replace("-", "");

    }

}
