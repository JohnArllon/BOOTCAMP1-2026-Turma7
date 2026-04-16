package br.uninter.medalerta.service;

import br.uninter.medalerta.model.Alerta;
import br.uninter.medalerta.model.StatusAlerta;
import br.uninter.medalerta.repository.AlertaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AlertaService {

    private final AlertaRepository repository;

    public AlertaService(AlertaRepository repository) {
        this.repository = repository;
    }

    public Alerta salvar(Integer idFrequencia, LocalDateTime dataHoraAlerta, String status) {

        Alerta alerta = new Alerta();
        alerta.setIdFrequencia(idFrequencia);
        alerta.setDataHoraAlerta(dataHoraAlerta);
        alerta.setStatusAlerta(StatusAlerta.valueOf(status));

        return repository.save(alerta);
    }

    public List<Alerta> listarTodos() {
        return repository.findAll();
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}