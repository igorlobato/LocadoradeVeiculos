package com.mycompany.locadoradeveiculos;
public class LocadoradeVeiculos {
    public static void main(String[] args) {
        
        Cliente c1 = new Cliente("Joao", "05468765428", "Rua salve jorge","(93)99191-9116",
                "18/02/2000", "M", 2020);
        
        Carro c3 = new Carro("Fusca","Volks", "azq-1234","Vermelho", 1996, 2,
                "Gasolina", 5);
        
        Moto m1 = new Moto("Numsei", "Honda","pad-9764","Preto", 2016,250,"tricilindral");
        
        System.out.println(c1);
        System.out.println(c3);
        System.out.println(m1);
    }
}
