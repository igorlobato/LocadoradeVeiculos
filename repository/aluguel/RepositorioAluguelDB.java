package repository.aluguel;

import conexao.Conexao;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.aluguel.Aluguel;
import model.cliente.Cliente;
import model.veiculos.Carro;
import model.veiculos.Categoria;
import model.veiculos.Moto;
import model.veiculos.Veiculo;
import repository.categoria.CategoriaNaoCadastradaException;
import repository.cliente.ClienteNaoCadastradoException;
import repository.cliente.RepositorioClienteDB;
import repository.veiculos.RepositorioVeiculoDB;
import repository.veiculos.VeiculoJaCadastradoException;
import repository.veiculos.VeiculoNaoCadastradoException;

public class RepositorioAluguelDB implements RepositorioAluguel, Serializable {
    List<Aluguel> alugueis;

    public RepositorioAluguelDB() {
        alugueis = new ArrayList<>();
    }

    @Override
    public Aluguel novoAluguel(Aluguel aluguel) throws AluguelJaCadastradoException {
        try {
            verificarAluguel(aluguel.getVeiculo().getPlaca());
            throw new VeiculoJaCadastradoException();
        } catch (AluguelNaoCadastradoException ex) {
            String sql = "INSERT INTO ALUGUEL (VEICULO, DATASAIDA, DATADEVOLUCAO, QUANTIDADEDIARIAS, CLIENTE, ATIVO, VALORPAGAR,"
                    + "MULTA, DIASATRASADOS, QUILOMETROSRODADOS) VALUES "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
                ps.setString(1, aluguel.getVeiculo().getPlaca());
                ps.setString(2, aluguel.getDataSaida());
                ps.setString(3, aluguel.getDataDevolucao());
                ps.setInt(4, aluguel.getQuantidadeDiarias());
                ps.setString(5, aluguel.getCliente().getNome());
                ps.setBoolean(6, aluguel.isAtivo());
                ps.setDouble(7, aluguel.getValorPagar());
                ps.setDouble(8, aluguel.getMulta());
                ps.setInt(9, aluguel.getDiasAtrasados());
                ps.setDouble(10, aluguel.getQuilometrosRodados());


                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (VeiculoJaCadastradoException ex) {
            Logger.getLogger(RepositorioAluguelDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return aluguel;
    }

    @Override
    public void alterarAluguel(Aluguel aluguel) throws AluguelNaoCadastradoException {
        
    }

    @Override
    public void devolverVeiculo(Aluguel aluguel) throws AluguelNaoCadastradoException {
        if (!alugueis.remove(aluguel)) {
            throw new AluguelNaoCadastradoException();
        }
    }

    @Override
    public Aluguel verificarAluguel(String placa) throws AluguelNaoCadastradoException {
        String sql = "SELECT * FROM ALUGUEL WHERE VEICULO = ?";
                Aluguel aluguel = null;
                try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
                    ps.setString(1, placa);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String veiculoPlaca = rs.getString("VEICULO");
                            String dataSaida = rs.getString("DATASAIDA");
                            String dataDevolucao = rs.getString("DATADEVOLUCAO");
                            int quantidadeDiarias = rs.getInt("QUANTIDADEDIARIAS");
                            String clienteNome = rs.getString("CLIENTE");
                            boolean ativo = rs.getBoolean("ATIVO");
                            double valorPagar = rs.getDouble("VALORPAGAR");
                            double multa = rs.getDouble("MULTA");
                            int diasAtrasados = rs.getInt("DIASATRASADOS");
                            double quilometrosRodados = rs.getDouble("QUILOMETROSRODADOS");
                            
                            Veiculo veiculo = verificarDisponibilidade(veiculoPlaca);
                            
                            Cliente cliente = buscarCliente(clienteNome);

                            aluguel = new Aluguel(veiculo, dataSaida, dataDevolucao, cliente);
                        } else {
                            throw new AluguelNaoCadastradoException();
                        }
                    } catch (ClienteNaoCadastradoException ex) {
                Logger.getLogger(RepositorioAluguelDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (VeiculoNaoCadastradoException ex) {
                Logger.getLogger(RepositorioAluguelDB.class.getName()).log(Level.SEVERE, null, ex);
            }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return aluguel;
    }


    @Override
    public List<Aluguel> getAll() {
        List<Aluguel> alugueis = new ArrayList<>(); 
        String sql = "SELECT * FROM ALUGUEL";
        Aluguel aluguel = null;

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String veiculoPlaca = rs.getString("VEICULO");
                    String dataSaida = rs.getString("DATASAIDA");
                    String dataDevolucao = rs.getString("DATADEVOLUCAO");
                    int quantidadeDiarias = rs.getInt("QUANTIDADEDIARIAS");
                    String clienteNome = rs.getString("CLIENTE");
                    boolean ativo = rs.getBoolean("ATIVO");
                    double valorPagar = rs.getDouble("VALORPAGAR");
                    double multa = rs.getDouble("MULTA");
                    int diasAtrasados = rs.getInt("DIASATRASADOS");
                    double quilometrosRodados = rs.getDouble("QUILOMETROSRODADOS");

                    Veiculo veiculo = verificarDisponibilidade(veiculoPlaca);
                            
                    Cliente cliente = buscarCliente(clienteNome);

                    aluguel = new Aluguel(veiculo, dataSaida, dataDevolucao, cliente);
                    
                    alugueis.add(aluguel);
                }
            } catch (VeiculoNaoCadastradoException ex) {
                Logger.getLogger(RepositorioAluguelDB.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClienteNaoCadastradoException ex) {
                Logger.getLogger(RepositorioAluguelDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return new ArrayList<>(alugueis);
    }

    @Override
    public List<Aluguel> getAll(Cliente cliente) {
        List<Aluguel> lista = new ArrayList<>();
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getCliente().equals(cliente)) {
                lista.add(aluguel);
            }
        }
        return lista;
    }

    public Aluguel verificarAluguelAtivo(String placa) throws AluguelNaoCadastradoException {
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getVeiculo().getPlaca().equals(placa) && aluguel.isAtivo()) {
                return aluguel;
            }
        }
        throw new AluguelNaoCadastradoException();
    }
    
    
    public Veiculo verificarDisponibilidade(String placa) throws VeiculoNaoCadastradoException {
        String sql = "SELECT * FROM VEICULO WHERE PLACA = ?";
        Veiculo veiculo = null;
        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setString(1, placa);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String modelo = rs.getString("MODELO");
                    String marca = rs.getString("MARCA");
                    String cor = rs.getString("COR");
                    int anodefabricao = rs.getInt("ANODEFABRICACAO");
                    double quilometragem = rs.getDouble("QUILOMETRAGEM");
                    String categoriaNome = rs.getString("CATEGORIA");
                    String veiculoType = rs.getString("VEICULO");
                    
                    Categoria categoria = verificarDescricao(categoriaNome);

                    if (veiculoType.equals("carro")) {
                        int numerodeportas = rs.getInt("NUMERODEPORTAS");
                        String tipodecombustivel = rs.getString("TIPODECOMBUSTIVEL");
                        int capacidadedoportamalas = rs.getInt("CAPACIDADEDOPORTAMALAS");

                        veiculo = new Carro(modelo, marca, placa, cor, anodefabricao, quilometragem, categoria,
                                numerodeportas, tipodecombustivel, capacidadedoportamalas);
                    } else if (veiculoType.equals("moto")) {
                        int cilindrada = rs.getInt("CILINDRADA");
                        String tipodemotor = rs.getString("TIPODEMOTOR");

                        veiculo = new Moto(modelo, marca, placa, cor, anodefabricao, quilometragem, categoria,
                                cilindrada, tipodemotor);
                    }
                } else {
                    throw new VeiculoNaoCadastradoException();
                }
            } catch (CategoriaNaoCadastradaException ex) {
                Logger.getLogger(RepositorioVeiculoDB.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculo;
    }

    public Categoria verificarDescricao(String categorian) throws CategoriaNaoCadastradaException {
        String sql = "SELECT * FROM CATEGORIA WHERE CATEGORIA = ?";
                Categoria categoria = null;
                try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
                    ps.setString(1, categorian);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs.next()) {
                            String categoriaa = rs.getString("CATEGORIA");
                            String descricao = rs.getString("DESCRICAO");
                            double valorDiaria = rs.getDouble("VALORDIARIA");
                            String para = rs.getString("PARA");

                            categoria = new Categoria(categoriaa, descricao, valorDiaria, para);
                        } else {
                            throw new CategoriaNaoCadastradaException();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return categoria;
    }
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
}
