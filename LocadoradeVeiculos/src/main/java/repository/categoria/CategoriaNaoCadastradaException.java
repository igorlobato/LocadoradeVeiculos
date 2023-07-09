package repository.categoria;

import repository1.RepositoryException;

public class CategoriaNaoCadastradaException extends RepositoryException{
    public CategoriaNaoCadastradaException() {
        super("Categoria não cadastrada");
    }
}
