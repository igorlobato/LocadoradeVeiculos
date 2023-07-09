package repository.categoria;

import repository1.RepositoryException;

public class CategoriaJaCadastradaException extends RepositoryException{
    public CategoriaJaCadastradaException() {
        super("Categoria já cadastrada");
    }
}
