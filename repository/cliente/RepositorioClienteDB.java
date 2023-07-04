package repository.cliente;

import conexao.Conexao;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.cliente.Cliente;
import java.util.ArrayList;
import java.util.List;

public class RepositorioClienteDB implements RepositorioCliente, Serializable {
    

  public RepositorioClienteDB() {
    
  }

    @Override
  public void inserirCliente(Cliente cliente) throws CPFJaCadastradoException {
    try {
      buscarCliente(cliente.getCpf());
      throw new CPFJaCadastradoException();
    } catch (ClienteNaoCadastradoException ex) {
        String sql = "INSERT INTO CLIENTE (NOME, CPF, ENDERECO, TELEFONE, DATADENASCIMENTO, SEXO, ANOCNH) VALUES (?,?,?,?,?,?,?)";
        try{
            PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getCpf());
            ps.setString(3, cliente.getEndereco());
            ps.setString(4, cliente.getTelefone());
            ps.setString(5, cliente.getDatadenascimento());
            ps.setString(6, cliente.getSexo());
            ps.setInt(7, cliente.getAnocnh());

            ps.executeUpdate();
            System.out.println("Cliente cadastrado!");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
  }

  @Override
  public void alterarCliente(Cliente cliente) throws ClienteNaoCadastradoException {
            String sql = "UPDATE CLIENTE SET NOME = ?, SEXO = ?, TELEFONE = ?, CPF = ?, ENDERECO = ?, DATADENASCIMENTO = ?, ANOCNH = ? WHERE CPF = ?";

            try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
                ps.setString(1, cliente.getNome());
                ps.setString(2, cliente.getSexo());
                ps.setString(3, cliente.getTelefone());
                ps.setString(4, cliente.getCpf());
                ps.setString(5, cliente.getEndereco());
                ps.setString(6, cliente.getDatadenascimento());
                ps.setInt(7, cliente.getAnocnh());
                ps.setString(8, cliente.getCpf());

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
  }

  @Override
  public void deletarCliente(Cliente cliente) throws ClienteNaoCadastradoException {
        String sql = "DELETE FROM CLIENTE WHERE CPF = ?";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setString(1, cliente.getCpf());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
  }

  @Override
  public Cliente buscarCliente(String cpf) throws ClienteNaoCadastradoException {
                String sql = "SELECT * FROM CLIENTE WHERE CPF = ?";
                Cliente cliente = null;

                try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
                    ps.setString(1, cpf);

                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String nome = rs.getString("NOME");
                            String endereco = rs.getString("ENDERECO");
                            String telefone = rs.getString("TELEFONE");
                            String datadenascimento = rs.getString("DATADENASCIMENTO");
                            String sexo = rs.getString("SEXO");
                            int cnh = rs.getInt("ANOCNH");

                            cliente = new Cliente(nome, cpf, endereco, telefone, datadenascimento, sexo, cnh);
                        } else {
                            throw new ClienteNaoCadastradoException();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return cliente;
  }

  @Override
  public List<Cliente> getAll() {
      List<Cliente> clientes = new ArrayList<>(); 
        String sql = "SELECT * FROM CLIENTE";
        Cliente cliente = null;

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String cpf = rs.getString("CPF");
                    String nome = rs.getString("NOME");
                    String endereco = rs.getString("ENDERECO");
                    String telefone = rs.getString("TELEFONE");
                    String datadenascimento = rs.getString("DATADENASCIMENTO");
                    String sexo = rs.getString("SEXO");
                    int cnh = rs.getInt("ANOCNH");

                    cliente = new Cliente(nome, cpf, endereco, telefone, datadenascimento, sexo, cnh);
                    clientes.add(cliente);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return new ArrayList<>(clientes);
  }

}
