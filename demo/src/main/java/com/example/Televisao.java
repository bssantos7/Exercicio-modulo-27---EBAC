package com.example;

public final class Televisao extends Produto{
    private Integer tela;

    public Televisao(){
        super(null, null, null);
        this.tela=null;
    }
    
    public Televisao(String tipo, String marca, Double preco, Integer tela){
        super(tipo, marca, preco);
        this.tela=tela;
    }

    public Integer getTela() {
        return tela;
    }

    public void setTela(Integer tela) {
        this.tela = tela;
    }

   

    @Override
    public void ligar() {
        System.out.println("MOstra imagem");
        
    }

    @Override
    public String toString() {
        return "Televisao [ " + super.toString() + "tela=" + tela+"]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + tela;
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
        Televisao other = (Televisao) obj;
        if (tela != other.tela)
            return false;
        return true;
    }

    

 

    

    

}
