package Facade;
import java.util.List;
import model.aluguel.Aluguel;
import model.cliente.Cliente;
import model.veiculos.Categoria;
import model.veiculos.Veiculo;
import repository.aluguel.AluguelJaCadastradoException;
import repository.aluguel.AluguelNaoCadastradoException;
import repository.aluguel.RepositorioAluguel;
import repository.categoria.CategoriaJaCadastradaException;
import repository.categoria.CategoriaNaoCadastradaException;
import repository.categoria.RepositorioCategoria;
import repository.categoria.RepositorioCategoriaLista;
import repository.cliente.CPFJaCadastradoException;
import repository.cliente.ClienteNaoCadastradoException;
import repository.cliente.RepositorioCliente;
import repository.cliente.RepositorioClienteLista;
import repository.veiculos.RepositorioVeiculo;
import repository.veiculos.RepositorioVeiculoLista;
import repository.veiculos.VeiculoJaCadastradoException;
import repository.veiculos.VeiculoNaoCadastradoException;

public class LocadoraFacade{
  private RepositorioCliente repositorioCliente;
  private RepositorioVeiculo repositorioVeiculo;
  private RepositorioCategoria repositorioCategoria;
  private RepositorioAluguel repositorioAluguel;

  public LocadoraFacade() {
    repositorioCliente = new RepositorioClienteLista();
    repositorioVeiculo = new RepositorioVeiculoLista();
    repositorioCategoria = new RepositorioCategoriaLista();
  }  

  public void inserirCliente(Cliente cliente) throws CPFJaCadastradoException {
      repositorioCliente.inserirCliente(cliente);
  }
    
  public void alterarCliente(Cliente cliente) throws ClienteNaoCadastradoException {
      repositorioCliente.alterarCliente(cliente);
  }
    
  public Cliente buscarCliente(String cpf) throws ClienteNaoCadastradoException {
      return repositorioCliente.buscarCliente(cpf);
  }
    
  public void excluirCliente(Cliente cliente) throws LocadoraException, ClienteNaoCadastradoException {
  List<Veiculo> contasDoCliente = repositorioVeiculo.getAll(cliente.getCpf());
  if (!contasDoCliente.isEmpty()) {
    throw new LocadoraException("Não é possível excluir um cliente com veiculos alugados");
  }
  repositorioCliente.deletarCliente(cliente);
}

    
  public List<Cliente> getAllClientes() {
      return repositorioCliente.getAll();
  }
    
  public Veiculo cadastrarVeiculo(Veiculo veiculo) throws VeiculoJaCadastradoException {
    repositorioVeiculo.cadastrarVeiculo(veiculo);
    return veiculo;
  }

  public Veiculo verificarDisponibilidade(String placa) throws VeiculoNaoCadastradoException {
        return repositorioVeiculo.verificarDisponibilidade(placa);
  }

  public void alterarVeiculo(Veiculo veiculo) throws VeiculoNaoCadastradoException  {
      repositorioVeiculo.alterarVeiculo(veiculo);
  }
    
public void deletarVeiculo(Veiculo veiculo) throws VeiculoNaoCadastradoException, LocadoraException {
      repositorioVeiculo.deletarVeiculo(veiculo);
}

    /*
  public void deposito(String numero, double valor) throws ContaNaoCadastradaException {
    Conta conta = repositorioConta.buscarConta(numero);
    conta.depositar(valor);
    repositorioConta.alterarConta(conta);
  }

  public void saque(String numero, double valor) throws ContaNaoCadastradaException, SaldoInsuficienteException {
    Conta conta = repositorioConta.buscarConta(numero);
    conta.sacar(valor);
    repositorioConta.alterarConta(conta);
  }

  public void tranferir(String origem, String destino, double valor) throws ContaNaoCadastradaException, SaldoInsuficienteException {
    Conta conta1 = repositorioConta.buscarConta(origem);
    Conta conta2 = repositorioConta.buscarConta(destino);
    conta1.transferirPara(conta2, valor);
    repositorioConta.alterarConta(conta1);
    repositorioConta.alterarConta(conta2);
  }

*/
    
  public List<Veiculo> getAllVeiculos() {
    return repositorioVeiculo.getAll();
  }

  public List<Veiculo> getAllVeiculos(String modelo) {
    return repositorioVeiculo.getAll(modelo);
  }

  public void exit() {
  }
  
    public Categoria cadastrarCategoria(Categoria categoria) throws CategoriaJaCadastradaException {
    repositorioCategoria.cadastrarCategoria(categoria);
    return categoria;
  }

  public Categoria verificarDescricao(String categoria) throws CategoriaNaoCadastradaException {
        return repositorioCategoria.verificarDescricao(categoria);
  }
  
  public void alterarCategoria(Categoria categoria) throws CategoriaNaoCadastradaException  {
      repositorioCategoria.alterarCategoria(categoria);
  }
    
  public void deletarCategoria(Categoria categoria) throws CategoriaNaoCadastradaException, LocadoraException {
      repositorioCategoria.deletarCategoria(categoria);
  }
  
  public List<Categoria> getAllCategorias() {
    return repositorioCategoria.getAll();
  }

  public List<Categoria> getAllCategorias(String para) {
    return repositorioCategoria.getAll(para);
  }
  
  public Aluguel novoAluguel(Aluguel aluguel) throws AluguelJaCadastradoException {
    repositorioAluguel.novoAluguel(aluguel);
    return aluguel;
  }

  public Aluguel verificarAluguel(String aluguel) throws AluguelNaoCadastradoException {
        return repositorioAluguel.verificarAluguel(aluguel);
  }
  
  public void alterarAluguel(Categoria categoria) throws AluguelNaoCadastradoException  {
      //repositorioCategoria.alterarCategoria(categoria);
  }
    
  public void cancelarAluguel(Categoria categoria) throws AluguelNaoCadastradoException, LocadoraException {
      //repositorioCategoria.deletarCategoria(categoria);
  }
  
  public List<Aluguel> getAllAlugueis() {
    return repositorioAluguel.getAll();
  }

  public List<Aluguel> getAllAlugueis(String categoria) {
    return repositorioAluguel.getAll(categoria);
  }
  
}
