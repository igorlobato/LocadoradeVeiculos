package repository.cliente;
import model.cliente.Cliente;
import java.util.ArrayList;
import java.util.List;

public class RepositorioClienteLista implements RepositorioCliente{
    List<Cliente> clientes;

  public RepositorioClienteLista() {
    this.clientes = new ArrayList<Cliente>();
  }

    @Override
    public void inserirCliente(Cliente cliente) throws CPFJaCadastradoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void alterarCliente(Cliente cliente) throws ClienteNaoCadastradoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void deletarCliente(Cliente cliente) throws ClienteNaoCadastradoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Cliente buscarCliente(String cpf) throws ClienteNaoCadastradoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cliente> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
