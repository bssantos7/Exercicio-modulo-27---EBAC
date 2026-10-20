package com.example;

public enum TiposProdutos {

    Televisao(com.example.Televisao.class),
    Radio(com.example.Radio.class);

    private final String caminhoCompleto;

    TiposProdutos(Class<?> classe){
        this.caminhoCompleto=classe.getName();
    }

    public String getCaminhoCompleto(){
        return caminhoCompleto;
    }

}
