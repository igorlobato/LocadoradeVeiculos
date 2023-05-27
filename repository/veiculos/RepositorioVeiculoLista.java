package repository.veiculos;
import java.util.ArrayList;
import java.util.List;
import model.veiculos.Veiculo;

public class RepositorioVeiculoLista implements RepositorioVeiculo{
    
    List<Veiculo> veiculo;
    

    public RepositorioVeiculoLista() {
        veiculo = new ArrayList<>();
    }

    @Override
    public Veiculo cadastrarVeiculo(Veiculo veiculo) throws VeiculoJaCadastradoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void alterarVeiculo(Veiculo veiculo) throws VeiculoNaoCadastradoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deletarVeiculo(Veiculo veiculo) throws VeiculoNaoCadastradoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Veiculo verificarDisponibilidade(String modelo) throws VeiculoNaoCadastradoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Veiculo> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Veiculo> getAll(String modelo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
