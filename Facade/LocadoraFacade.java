package Facade;
import java.util.List;

public class LocadoraFacade{
  /*private RepositorioCliente repositorioCliente;
  private RepositorioConta repositorioConta;

  public BancoFacade() {
    repositorioCliente = new RepositorioClienteLista();
    repositorioConta = new RepositorioContaLista();
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
    
  public void excluirCliente(Cliente cliente) throws BancoException, ClienteNaoCadastradoException {
  List<Conta> contasDoCliente = repositorioConta.getAll(cliente.getCpf());
  if (!contasDoCliente.isEmpty()) {
    throw new BancoException("Não é possível excluir um cliente com contas cadastradas");
  }
  repositorioCliente.deletarCliente(cliente);
}

    
  public List<Cliente> getAllClientes() {
      return repositorioCliente.getAll();
  }
    
  public Conta inserirConta(Conta conta) throws ContaJaCadastradaException {
    repositorioConta.inserirConta(conta);
    return conta;
  }

  public Conta buscarConta(String numero) throws ContaNaoCadastradaException  {
    return repositorioConta.buscarConta(numero);
  }

  public void alterarConta(Conta conta) throws ContaNaoCadastradaException  {
      repositorioConta.alterarConta(conta);
  }
    
public void excluirConta(Conta conta) throws ContaNaoCadastradaException, BancoException {
  if (conta.getSaldo() != 0) {
    throw new BancoException("Não é possível excluir uma conta com saldo não zero");
  }
  repositorioConta.deletarConta(conta);
}

    
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
    
  public List<Conta> getAllContas() {
    return repositorioConta.getAll();
  }

  public List<Conta> getAllContas(String cpf) {
    return repositorioConta.getAll(cpf);
  }

  public void exit() {
  }*/
}
