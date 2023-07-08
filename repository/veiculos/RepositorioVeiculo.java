package repository.veiculos;
import java.util.ArrayList;
import model.veiculos.Veiculo;
import java.util.List;

public interface RepositorioVeiculo {
    Veiculo cadastrarVeiculo(Veiculo veiculo) throws VeiculoJaCadastradoException;

    void deletarVeiculo(Veiculo veiculo) throws VeiculoNaoCadastradoException;

    Veiculo verificarDisponibilidade(String placa) throws VeiculoNaoCadastradoException;

    List<Veiculo> getAll();
}
