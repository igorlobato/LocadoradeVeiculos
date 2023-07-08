package repository.veiculos;

import conexao.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.veiculos.Carro;
import model.veiculos.Categoria;
import model.veiculos.Moto;
import model.veiculos.Veiculo;
import repository.categoria.CategoriaNaoCadastradaException;

public class RepositorioVeiculoDB implements RepositorioVeiculo {

    @Override
    public Veiculo cadastrarVeiculo(Veiculo veiculo) throws VeiculoJaCadastradoException {
        try {
            verificarDisponibilidade(veiculo.getPlaca());
            throw new VeiculoJaCadastradoException();
        } catch (VeiculoNaoCadastradoException ex) {
            String sql = "INSERT INTO VEICULO (MODELO, MARCA, PLACA, COR, ANODEFABRICACAO, QUILOMETRAGEM, CATEGORIA, ALUGADO,"
                    + " VEICULO, NUMERODEPORTAS, TIPODECOMBUSTIVEL, CAPACIDADEDOPORTAMALAS, CILINDRADA, TIPODEMOTOR) VALUES "
                    + "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
                ps.setString(1, veiculo.getModelo());
                ps.setString(2, veiculo.getMarca());
                ps.setString(3, veiculo.getPlaca());
                ps.setString(4, veiculo.getCor());
                ps.setInt(5, veiculo.getAnodefabricao());
                ps.setDouble(6, veiculo.getQuilometragem());
                ps.setString(7, veiculo.getCategoria().getCategoria());
                ps.setBoolean(8, veiculo.isAlugado());


                if (veiculo instanceof Carro) {
                    ps.setString(9, "carro");
                    Carro carro = (Carro) veiculo;
                    ps.setInt(10, carro.getNumerodeportas());
                    ps.setString(11, carro.getTipodecombustivel());
                    ps.setInt(12, carro.getCapacidadedoportamalas());
                    ps.setNull(13, java.sql.Types.INTEGER);
                    ps.setNull(14, java.sql.Types.INTEGER);
                } else if (veiculo instanceof Moto) {
                    ps.setString(9, "moto");
                    Moto moto = (Moto) veiculo;
                    ps.setNull(10, java.sql.Types.INTEGER);
                    ps.setNull(11, java.sql.Types.INTEGER);
                    ps.setNull(12, java.sql.Types.INTEGER);
                    ps.setInt(13, moto.getCilindrada());
                    ps.setString(14, moto.getTipodemotor());
                }

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return veiculo;
    }

    @Override
    public void deletarVeiculo(Veiculo veiculo) throws VeiculoNaoCadastradoException {
        String sql = "DELETE FROM VEICULO WHERE PLACA = ?";
        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setString(1, veiculo.getPlaca());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new VeiculoNaoCadastradoException();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
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


    @Override
    public List<Veiculo> getAll() {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM VEICULO";
        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                String modelo = rs.getString("MODELO");
                String marca = rs.getString("MARCA");
                String placa = rs.getString("PLACA");
                String cor = rs.getString("COR");
                int anodefabricao = rs.getInt("ANODEFABRICACAO");
                double quilometragem = rs.getDouble("QUILOMETRAGEM");
                String categoriaNome = rs.getString("CATEGORIA");
                boolean alugado = rs.getBoolean("ALUGADO");
                String veiculoType = rs.getString("VEICULO");

                Categoria categoria = verificarDescricao(categoriaNome);

                Veiculo veiculo;

                if (veiculoType.equals("carro")) {
                    int numerodeportas = rs.getInt("NUMERODEPORTAS");
                    String tipodecombustivel = rs.getString("TIPODECOMBUSTIVEL");
                    int capacidadedoportamalas = rs.getInt("CAPACIDADEDOPORTAMALAS");

                    veiculo = new Carro(modelo, marca, placa, cor, anodefabricao, quilometragem, categoria,
                            numerodeportas, tipodecombustivel, capacidadedoportamalas);
                    veiculos.add(veiculo);
                } else if (veiculoType.equals("moto")) {
                    int cilindrada = rs.getInt("CILINDRADA");
                    String tipodemotor = rs.getString("TIPODEMOTOR");

                    veiculo = new Moto(modelo, marca, placa, cor, anodefabricao, quilometragem, categoria,
                            cilindrada, tipodemotor);
                    veiculos.add(veiculo);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (CategoriaNaoCadastradaException ex) {
            Logger.getLogger(RepositorioVeiculoDB.class.getName()).log(Level.SEVERE, null, ex);
        }
        return veiculos;
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
}
