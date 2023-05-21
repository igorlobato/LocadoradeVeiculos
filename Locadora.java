package com.mycompany.locadoradeveiculos;
public class Locadora {
    private Carro listacarros;
    private Moto listamotos;
    private int diasalugados;

    public Carro getListacarros() {
        return listacarros;
    }

    public void setListacarros(Carro listacarros) {
        this.listacarros = listacarros;
    }

    public Moto getListamotos() {
        return listamotos;
    }

    public void setListamotos(Moto listamotos) {
        this.listamotos = listamotos;
    }

    public int getDiasalugados() {
        return diasalugados;
    }

    public void setDiasalugados(int diasalugados) {
        this.diasalugados = diasalugados;
    }
    
    
    public void verificarDisponibilidade(String modelo){
        
    }
    
    public void alugar(Carro c, int d){
        
    }
    
    public void alugar(Moto m, int d){
        
    }
    
    public void devolver(Carro c, int d){
        
    }
    
    public void devolver(Moto m, int d){
        
    }
}
