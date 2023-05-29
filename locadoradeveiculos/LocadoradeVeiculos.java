package locadoradeveiculos;

import model.cliente.Cliente;
import repository.cliente.ClienteNaoCadastradoException;
import repository.cliente.CPFJaCadastradoException;
import repository.cliente.RepositorioCliente;
import repository.cliente.RepositorioClienteLista;
import repository.veiculos.RepositorioVeiculo;
import repository.veiculos.RepositorioVeiculoLista;

public class LocadoradeVeiculos {
    private static RepositorioCliente repClientes = new RepositorioClienteLista();
    private static RepositorioVeiculo repVeiculos = new RepositorioVeiculoLista();

    public static void main(String[] args) {
        Teste();
        try {
            Cliente c = repClientes.buscarCliente("024.204.348.45");
            
            for (Cliente cliente : repClientes.getAll()) {
                System.out.println(cliente);
            }
        } catch (ClienteNaoCadastradoException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void Teste() {
        try {
            repClientes.inserirCliente(new Cliente("Pedro", "024.204.348.45", "Santarenzinho 55", "(55)991919116",
                    "5/5/15", "M", 2018));

            repClientes.inserirCliente(new Cliente("Ana", "055.465.128.35", "Santana 33", "(55)991654621",
                    "1/4/98", "F", 2020));
            
            
        } catch (CPFJaCadastradoException e1) {
            System.out.println(e1.getMessage());
        } 
    }
}