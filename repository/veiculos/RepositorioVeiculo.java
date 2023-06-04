package repository.veiculos;
import java.util.ArrayList;
import model.veiculos.Veiculo;
import java.util.List;

public interface RepositorioVeiculo {
    Veiculo cadastrarVeiculo(Veiculo veiculo) throws VeiculoJaCadastradoException;

    void alterarVeiculo(Veiculo veiculo) throws VeiculoNaoCadastradoException;

    void deletarVeiculo(Veiculo veiculo) throws VeiculoNaoCadastradoException;

    Veiculo verificarDisponibilidade(String modelo) throws VeiculoNaoCadastradoException;

    List<Veiculo> getAll();
    
}
