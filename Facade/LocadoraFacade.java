package Facade;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import model.aluguel.Aluguel;
import model.cliente.Cliente;
import model.veiculos.Categoria;
import model.veiculos.Veiculo;
import repository.aluguel.AluguelJaCadastradoException;
import repository.aluguel.AluguelNaoCadastradoException;
import repository.aluguel.RepositorioAluguel;
import repository.aluguel.RepositorioAluguelDB;
import repository.categoria.CategoriaJaCadastradaException;
import repository.categoria.CategoriaNaoCadastradaException;
import repository.categoria.RepositorioCategoria;
import repository.categoria.RepositorioCategoriaDB;
import repository.cliente.CPFJaCadastradoException;
import repository.cliente.ClienteNaoCadastradoException;
import repository.cliente.RepositorioCliente;
import repository.cliente.RepositorioClienteDB;
import repository.veiculos.RepositorioVeiculo;
import repository.veiculos.RepositorioVeiculoDB;
import repository.veiculos.VeiculoJaCadastradoException;
import repository.veiculos.VeiculoNaoCadastradoException;

public class LocadoraFacade{
    
  private final File file = new File("locadora.dat");  
    
  private RepositorioCliente repositorioCliente;
  private RepositorioVeiculo repositorioVeiculo;
  private RepositorioCategoria repositorioCategoria;
  private RepositorioAluguel repositorioAluguel;
  
  private static LocadoraFacade instance = null;
  
  public LocadoraFacade() {
		if (file.exists()) {
			loadData();
		} else {
			repositorioCliente = new RepositorioClienteDB();
                        repositorioVeiculo = new RepositorioVeiculoDB();
                        repositorioCategoria = new RepositorioCategoriaDB();
                        repositorioAluguel = new RepositorioAluguelDB();
		}
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
      //repositorioVeiculo.alterarVeiculo(veiculo);
  }
    
public void deletarVeiculo(Veiculo veiculo) throws VeiculoNaoCadastradoException, LocadoraException {
      repositorioVeiculo.deletarVeiculo(veiculo);
}
    
  public List<Veiculo> getAllVeiculos() {
    return repositorioVeiculo.getAll();
  }

  public List<Veiculo> getAllVeiculos(String modelo) {
    //return repositorioVeiculo.getAll(modelo);
      return null;
    //return repositorioVeiculo.getAll(modelo);
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

  /*public List<Categoria> getAllCategorias(String para) {
    return repositorioCategoria.getAll(para);
  }
  */
  public Aluguel novoAluguel(Aluguel aluguel) throws AluguelJaCadastradoException {
    repositorioAluguel.novoAluguel(aluguel);
    return aluguel;
  }

  public Aluguel verificarAluguel(String placa) throws AluguelNaoCadastradoException {
        return repositorioAluguel.verificarAluguel(placa);
  }
  
  public Aluguel verificarAluguelAtivo(String placa) throws AluguelNaoCadastradoException  {
        return repositorioAluguel.verificarAluguelAtivo(placa);
  }
    
  public void devolverVeiculo(Aluguel aluguel) throws AluguelNaoCadastradoException{
      repositorioAluguel.devolverVeiculo(aluguel);
  }
  
  public List<Aluguel> getAllAlugueis() {
    return repositorioAluguel.getAll();
  }

  public List<Aluguel> getAllAlugueis(Cliente cliente) throws ClienteNaoCadastradoException{
    return repositorioAluguel.getAll(cliente);
  }
  
  private void loadData() {
		try {
			FileInputStream f = new FileInputStream(file);
				ObjectInputStream o = new ObjectInputStream(f);

			repositorioCliente = (RepositorioCliente) o.readObject();
			repositorioVeiculo = (RepositorioVeiculo) o.readObject();
                        repositorioCategoria = (RepositorioCategoria) o.readObject();
                        repositorioAluguel = (RepositorioAluguel) o.readObject();

			o.close();
			f.close();
		} catch (ClassNotFoundException e) {
			System.err.println("Definição da classe não encontrada");
		} catch (IOException e) {
			System.err.println("Erro ao carregar dados do arquivo");
		}
	}

	public void exit() {
		try {
			FileOutputStream f = new FileOutputStream(file);
			ObjectOutputStream o = new ObjectOutputStream(f);

			
			o.writeObject(repositorioCliente);
			o.writeObject(repositorioVeiculo);
                        o.writeObject(repositorioCategoria);
                        o.writeObject(repositorioAluguel);
                        
			o.close();
			f.close();
		} catch (IOException e) {
			System.err.println("Erro de serialização de objeto");
			e.printStackTrace();
		}
  
}
}
