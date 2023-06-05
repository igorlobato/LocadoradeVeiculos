package repository.veiculos;
import java.util.ArrayList;
import java.util.List;
import model.veiculos.Veiculo;

public class RepositorioVeiculoLista implements RepositorioVeiculo{
    List<Veiculo> veiculos;

    public RepositorioVeiculoLista() {
        veiculos = new ArrayList<>();
    }

    @Override
    public Veiculo cadastrarVeiculo(Veiculo veiculo) throws VeiculoJaCadastradoException {
      try {
      verificarDisponibilidade(veiculo.getModelo());
      throw new VeiculoJaCadastradoException();
    } catch (VeiculoNaoCadastradoException ex) {
      veiculos.add(veiculo);
    }
        return veiculo;
    }

    @Override
    public void alterarVeiculo(Veiculo veiculo) throws VeiculoNaoCadastradoException {
        verificarDisponibilidade(veiculo.getModelo());
    //verificar depois se dá para alterar por placa
    }

    @Override
    public void deletarVeiculo(Veiculo veiculo) throws VeiculoNaoCadastradoException {
        if (!veiculos.remove(veiculo)) {
            throw new VeiculoNaoCadastradoException();
        }
    }

    @Override
    public Veiculo verificarDisponibilidade(String modelo) throws VeiculoNaoCadastradoException {
        for (Veiculo veiculo : veiculos) {
          if (veiculo.getModelo().equals(modelo)) {
            return veiculo;
          }
        }
        throw new VeiculoNaoCadastradoException();
    }


    @Override
    public List<Veiculo> getAll() {
        return new ArrayList<>(veiculos);
    }

    @Override
    public List<Veiculo> getAll(String modelo) {
        List<Veiculo> lista = new ArrayList<>();
        for (Veiculo veiculo : veiculos) {
            if (veiculo.getModelo().getCpf().equals(cpf)) {
                lista.add(veiculo);
            }
        }
        return lista;
    }
    
}
