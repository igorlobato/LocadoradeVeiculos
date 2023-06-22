package repository.aluguel;

import repository1.RepositoryException;

public class AluguelNaoCadastradoException extends RepositoryException{
    public AluguelNaoCadastradoException() {
        super("Aluguel não cadastrado");
    }
}
