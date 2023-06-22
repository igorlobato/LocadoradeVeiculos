package repository.aluguel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.aluguel.Aluguel;

public class RepositorioAluguelLista implements RepositorioAluguel, Serializable {
    List<Aluguel> alugueis;

    public RepositorioAluguelLista() {
        alugueis = new ArrayList<>();
    }

    @Override
    public Aluguel novoAluguel(Aluguel aluguel) throws AluguelJaCadastradoException {
        try {
        verificarAluguel(aluguel.getDataDevolucao());
        throw new AluguelJaCadastradoException();
      } catch (AluguelNaoCadastradoException ex) {
        alugueis.add(aluguel);
      }
          return aluguel;
    }

    @Override
    public void alterarAluguel(Aluguel aluguel) throws AluguelNaoCadastradoException {
        
    }

    @Override
    public void cancelarAluguel(Aluguel aluguel) throws AluguelNaoCadastradoException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Aluguel verificarAluguel(String placa) throws AluguelNaoCadastradoException {
        for (Aluguel aluguel : alugueis) {
          if (aluguel.getCategoria().equals(placa)) {
            return aluguel;
          }
        }
        throw new AluguelNaoCadastradoException();
    }

    @Override
    public List<Aluguel> getAll() {
        return new ArrayList<>(alugueis);
    }

    @Override
    public List<Aluguel> getAll(String categoria) {
        List<Aluguel> lista = new ArrayList<>();
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getCategoria().equals(categoria)) {
                lista.add(aluguel);
            }
        }
        return lista;
    }
    }
    
