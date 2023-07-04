package repository.categoria;

import conexao.Conexao;
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.veiculos.Categoria;

public class RepositorioCategoriaDB implements RepositorioCategoria, Serializable {
    
    public RepositorioCategoriaDB() {
    }

    @Override
    public Categoria cadastrarCategoria(Categoria categoria) throws CategoriaJaCadastradaException {
    try {
    verificarDescricao(categoria.getCategoria());
    throw new CategoriaJaCadastradaException();
  } catch (CategoriaNaoCadastradaException ex) {
      String sql = "INSERT INTO CATEGORIA (CATEGORIA, DESCRICAO, VALORDIARIA, PARA) VALUES (?,?,?,?)";
      try{
          PreparedStatement ps = Conexao.getConexao().prepareStatement(sql);
          ps.setString(1, categoria.getCategoria());
          ps.setString(2, categoria.getDescricao());
          ps.setDouble(3, categoria.getValorDiaria());
          ps.setString(4, categoria.getPara());

          ps.executeUpdate();
          System.out.println("Categoria cadastrada!");
      } catch (SQLException e){
          e.printStackTrace();
      }
  }
        return null;
}

    @Override
    public void alterarCategoria(Categoria categoria) throws CategoriaNaoCadastradaException {
        String sql = "UPDATE CATEGORIA SET CATEGORIA = ?, DESCRICAO = ?, VALORDIARIA = ?, PARA = ?";

            try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
                ps.setString(1, categoria.getCategoria());
                ps.setString(2, categoria.getDescricao());
                ps.setDouble(3, categoria.getValorDiaria());
                ps.setString(4, categoria.getPara());

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    @Override
    public void deletarCategoria(Categoria categoria) throws CategoriaNaoCadastradaException {
        String sql = "DELETE FROM CATEGORIA WHERE CATEGORIA = ?";

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {
            ps.setString(1, categoria.getCategoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
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

    @Override
    public List<Categoria> getAll() {
        List<Categoria> categorias = new ArrayList<>(); 
        String sql = "SELECT * FROM CATEGORIA";
        Categoria categoria = null;

        try (PreparedStatement ps = Conexao.getConexao().prepareStatement(sql)) {

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String categoriaa = rs.getString("CATEGORIA");
                    String descricao = rs.getString("DESCRICAO");
                    double valorDiaria = rs.getDouble("VALORDIARIA");
                    String para = rs.getString("PARA");

                    categoria = new Categoria(categoriaa, descricao, valorDiaria, para);
                    categorias.add(categoria);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return new ArrayList<>(categorias);
    }

    /*@Override
    public List<Categoria> getAll(String para) {
        List<Categoria> lista = new ArrayList<>();
        for (Categoria categoria : categorias) {
            if (categoria.getPara().equals(para)) {
                lista.add(categoria);
            }
        }
        return lista;
    }*/
    }