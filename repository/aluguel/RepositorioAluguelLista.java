package repository.aluguel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.aluguel.Aluguel;
import model.cliente.Cliente;

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
    public void devolverVeiculo(Aluguel aluguel) throws AluguelNaoCadastradoException {
        if (!alugueis.remove(aluguel)) {
            throw new AluguelNaoCadastradoException();
        }
    }

    @Override
    public Aluguel verificarAluguel(String placa) throws AluguelNaoCadastradoException {
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getVeiculo().getPlaca().equals(placa)) {
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
    public List<Aluguel> getAll(Cliente cliente) {
        List<Aluguel> lista = new ArrayList<>();
        for (Aluguel aluguel : alugueis) {
            if (aluguel.getCliente().equals(cliente)) {
                lista.add(aluguel);
            }
        }
        return lista;
    }
    }
    
