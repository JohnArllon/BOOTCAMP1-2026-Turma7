package br.uninter.medalerta.service;

import br.uninter.medalerta.model.Frequencia;
import br.uninter.medalerta.repository.FrequenciaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;

@Service
public class FrequenciaService {

    private final FrequenciaRepository repository;

    public FrequenciaService(FrequenciaRepository repository) {
        this.repository = repository;
    }

    public Frequencia salvar(Integer idUsuario, Integer idMedicamento, LocalTime horarioUso, Integer intervaloHoras, Integer vezesPorDia) {
        Frequencia frequencia = new Frequencia();
        frequencia.setIdUsuario(idUsuario);
        frequencia.setIdMedicamento(idMedicamento);
        frequencia.setHorarioUso(horarioUso);
        frequencia.setIntervaloHoras(intervaloHoras);
        frequencia.setVezesPorDia(vezesPorDia);
        return repository.save(frequencia);
    }

    public List<Frequencia> listarTodos() {
        return repository.findAll();
    }

    public void deletar(Integer id) {
        repository.deleteById(id);
    }
}