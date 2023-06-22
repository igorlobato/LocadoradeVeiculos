package repository.aluguel;

import repository1.RepositoryException;

public class AluguelJaCadastradoException extends RepositoryException{
    public AluguelJaCadastradoException() {
        super("Aluguel já cadastrado");
    }
}
