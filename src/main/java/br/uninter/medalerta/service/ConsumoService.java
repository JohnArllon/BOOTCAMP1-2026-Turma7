package br.uninter.medalerta.service;

import br.uninter.medalerta.model.Confirmacao;
import br.uninter.medalerta.model.Consumo;
import br.uninter.medalerta.repository.ConsumoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsumoService {

    private final ConsumoRepository repository;

    public ConsumoService(ConsumoRepository repository) {
        this.repository = repository;
    }

    public Consumo salvar(Integer idAlerta, LocalDateTime dataHoraConsumo, String confirmacao) {

        Consumo consumo = new Consumo();
        consumo.setIdAlerta(idAlerta);
        consumo.setDataHoraConsumo(dataHoraConsumo);
        consumo.setConfirmacao(Confirmacao.valueOf(confirmacao));

        return repository.save(consumo);
    }

    public List<Consumo> listarTodos() {
        return repository.findAll();
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}