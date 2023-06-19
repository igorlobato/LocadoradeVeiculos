package repository.aluguel;

import java.util.List;
import model.aluguel.Aluguel;

public interface RepositorioAluguel {
    Aluguel novoAluguel(Aluguel aluguel) throws AluguelJaCadastradoException;

    void alterarAluguel(Aluguel aluguel) throws AluguelNaoCadastradoException;

    void cancelarAluguel(Aluguel aluguel) throws AluguelNaoCadastradoException;

    Aluguel verificarAluguel(String placa) throws AluguelNaoCadastradoException;

    List<Aluguel> getAll();
    
    List<Aluguel> getAll(String placa);
}
