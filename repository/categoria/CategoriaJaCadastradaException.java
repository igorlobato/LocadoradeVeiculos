package repository.categoria;
public class CategoriaJaCadastradaException extends Exception{
    public CategoriaJaCadastradaException() {
        super("Categoria já cadastrada");
    }
}
