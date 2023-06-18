package repository.categoria;

import java.util.List;
import model.veiculos.Categoria;


public interface RepositorioCategoria {
    Categoria cadastrarCategoria(Categoria categoria) throws CategoriaJaCadastradaException;

    void alterarCategoria(Categoria categoria) throws CategoriaNaoCadastradaException;

    void deletarCategoria(Categoria categoria) throws CategoriaNaoCadastradaException;

    Categoria verificarDescricao(String categoria) throws CategoriaNaoCadastradaException;

    List<Categoria> getAll();
    
    List<Categoria> getAll(String para);
}
