package repository.categoria;

import java.util.ArrayList;
import java.util.List;
import model.veiculos.Categoria;

public class RepositorioCategoriaLista implements RepositorioCategoria{
    List<Categoria> categorias;
    
    public RepositorioCategoriaLista() {
        categorias = new ArrayList<>();
    }

    @Override
    public Categoria cadastrarCategoria(Categoria categoria) throws CategoriaJaCadastradaException {
      try {
      verificarDescricao(categoria.getCategoria());
      throw new CategoriaJaCadastradaException();
    } catch (CategoriaNaoCadastradaException ex) {
      categorias.add(categoria);
    }
        return categoria;
    }

    @Override
    public void alterarCategoria(Categoria categoria) throws CategoriaNaoCadastradaException {
        verificarDescricao(categoria.getCategoria());
    }

    @Override
    public void deletarCategoria(Categoria categoria) throws CategoriaNaoCadastradaException {
        if (!categorias.remove(categoria)) {
            throw new CategoriaNaoCadastradaException();
        }
    }

    @Override
    public Categoria verificarDescricao(String Categoria) throws CategoriaNaoCadastradaException {
        for (Categoria categoria : categorias) {
          if (categoria.getCategoria().equals(Categoria)) {
            return categoria;
          }
        }
        throw new CategoriaNaoCadastradaException();
    }

    @Override
    public List<Categoria> getAll() {
        return new ArrayList<>(categorias);
    }

    @Override
    public List<Categoria> getAll(String para) {
        List<Categoria> lista = new ArrayList<>();
        for (Categoria categoria : categorias) {
            if (categoria.getPara().equals(para)) {
                lista.add(categoria);
            }
        }
        return lista;
    }
    }
