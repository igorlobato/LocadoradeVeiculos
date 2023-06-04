package locadoradeveiculos;

import model.cliente.Cliente;
import model.veiculos.Veiculo;
import repository.cliente.ClienteNaoCadastradoException;
import repository.cliente.CPFJaCadastradoException;
import repository.cliente.RepositorioCliente;
import repository.cliente.RepositorioClienteLista;
import repository.veiculos.RepositorioVeiculo;
import repository.veiculos.RepositorioVeiculoLista;
import repository.veiculos.VeiculoJaCadastradoException;

public class LocadoradeVeiculos {
    private static RepositorioCliente repClientes = new RepositorioClienteLista();
    private static RepositorioVeiculo repVeiculos = new RepositorioVeiculoLista();

    public static void main(String[] args) {
        Teste();
        
            for (Cliente cliente : repClientes.getAll()) {
                System.out.println(cliente);
            }
        
            System.out.println("");
            
            for (Veiculo veiculo : repVeiculos.getAll()) {
                System.out.println(veiculo);
    }}

    private static void Teste() {
        try {
            repClientes.inserirCliente(new Cliente("Pedro", "024.204.348.45", "Santarenzinho 55", "(55)991919116",
                    "5/5/15", "M", 2018));

            repClientes.inserirCliente(new Cliente("Ana", "055.465.128.35", "Santana 33", "(55)991654621",
                    "1/4/98", "F", 2020));
            
            repVeiculos.cadastrarVeiculo(new Veiculo("CG 160 Titan", "Honda", "OSA-6549", "Vermolho", 2023,
            5.5, "Moto") {});
            
            repVeiculos.cadastrarVeiculo(new Veiculo("Mobi", "Fiat", "ASO-6549", "Preto", 2020,
            55.5, "A") {});
            
        } catch (CPFJaCadastradoException e1) {
            System.out.println(e1.getMessage());
        } catch (VeiculoJaCadastradoException e2) {
            System.out.println(e2.getMessage());
    }
    }
}

