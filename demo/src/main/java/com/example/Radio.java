package com.example;

public class Radio extends Produto{

    private String caixa;
    
    public Radio(String tipo, String marca, Double preco){
        super(tipo, marca, preco);
        this.caixa="padrão";
    }

    public Radio(String tipo, String marca, Double preco, String caixa){
        super(tipo, marca, preco);
        this.caixa="profissional";
    }

    public String getCaixa() {
        return caixa;
    }

    public void setCaixa(String caixa) {
        this.caixa = caixa;
    }

    @Override
    public void ligar() {
        System.out.println("Faz som");
        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((caixa == null) ? 0 : caixa.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Radio other = (Radio) obj;
        if (caixa == null) {
            if (other.caixa != null)
                return false;
        } else if (!caixa.equals(other.caixa))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Radio ["+super.toString() + "caixa=" + caixa + ", toString()=" +  "]";
    }

    

    

    

}
