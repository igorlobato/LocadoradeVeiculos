package DAO;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.cliente.Cliente;

public class ClienteDAO {
    public void cadastrarCliente(Cliente cliente){
            String sql = "INSERT INTO CLIENTE (NOME, CPF, ENDERECO, TELEFONE, DATADENASCIMENTO, SEXO, ANOCNH) VALUES (?,?,?,?,?,?,?)";
            PreparedStatement ps = null;

            try{
                ps = Conexao.getConexao().prepareStatement(sql);
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
    
        public List<Cliente> listarClientes() {
            List<Cliente> clientes = new ArrayList<>();

            String sql = "SELECT * FROM CLIENTE";

            try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    String nome = rs.getString("NOME");
                    String cpf = rs.getString("CPF");
                    String endereco = rs.getString("ENDERECO");
                    String telefone = rs.getString("TELEFONE");
                    String datadenascimento = rs.getString("DATADENASCIMENTO");
                    String sexo = rs.getString("SEXO");
                    int cnh = rs.getInt("ANOCNH");

                    Cliente cliente = new Cliente(nome, cpf, endereco, telefone, datadenascimento, sexo, cnh);
                    clientes.add(cliente);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return clientes;
        }
        
        public void excluirCliente(String cpf) {
        String sql = "DELETE FROM CLIENTE WHERE CPF = ?";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setString(1, cpf);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        public void atualizarCliente(Cliente cliente) {
            String sql = "UPDATE CLIENTE SET NOME = ?, SEXO = ?, TELEFONE = ? WHERE CPF = ?";

            try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
                ps.setString(1, cliente.getNome());
                ps.setString(2, cliente.getSexo());
                ps.setString(3, cliente.getTelefone());
                ps.setString(4, cliente.getCpf());

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        
}