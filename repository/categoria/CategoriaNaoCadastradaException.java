package repository.categoria;
public class CategoriaNaoCadastradaException extends Exception{
    public CategoriaNaoCadastradaException() {
        super("Categoria não cadastrada");
    }
}
