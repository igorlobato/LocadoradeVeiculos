package repository.aluguel;

import java.util.List;
import model.aluguel.Aluguel;
import model.cliente.Cliente;

public interface RepositorioAluguel {
    Aluguel novoAluguel(Aluguel aluguel) throws AluguelJaCadastradoException;

    void alterarAluguel(Aluguel aluguel) throws AluguelNaoCadastradoException;

    void devolverVeiculo(Aluguel aluguel) throws AluguelNaoCadastradoException;

    Aluguel verificarAluguel(String placa) throws AluguelNaoCadastradoException;
    
    Aluguel verificarAluguelAtivo (String placa)throws AluguelNaoCadastradoException;

    List<Aluguel> getAll();
    
    List<Aluguel> getAll(Cliente cliente);
}
