package br.uninter.medalerta.service;

import br.uninter.medalerta.model.*;
import br.uninter.medalerta.repository.MedicamentoRepository;
import br.uninter.medalerta.repository.UsuarioMedicamentoRepository;
import br.uninter.medalerta.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioMedicamentoService {

    private final UsuarioMedicamentoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final MedicamentoRepository medicamentoRepository;

    public UsuarioMedicamentoService(
            UsuarioMedicamentoRepository repository,
            UsuarioRepository usuarioRepository,
            MedicamentoRepository medicamentoRepository
    ) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.medicamentoRepository = medicamentoRepository;
    }

    public UsuarioMedicamento vincular(
            Integer idUsuario,
            Integer idMedicamento,
            String dosagem
    ) {
        Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + idUsuario));

        Medicamento medicamento = medicamentoRepository.findById(idMedicamento)
                .orElseThrow(() -> new RuntimeException("Medicamento não encontrado: " + idMedicamento));

        UsuarioMedicamentoId id = new UsuarioMedicamentoId(idUsuario, idMedicamento);

        if (repository.existsById(id)) {
            throw new RuntimeException("Esse vínculo já existe.");
        }

        UsuarioMedicamento entidade = new UsuarioMedicamento();
        entidade.setId(id);
        entidade.setUsuario(usuario);
        entidade.setMedicamento(medicamento);
        entidade.setDosagem(dosagem);

        return repository.save(entidade);
    }

    public List<UsuarioMedicamento> listarTodos() {
        return repository.findAll();
    }

    public List<UsuarioMedicamento> listarPorUsuario(Integer idUsuario) {
        return repository.findByUsuario_IdUsuario(idUsuario);
    }

    public UsuarioMedicamento buscarPorId(Integer idUsuario, Integer idMedicamento) {
        UsuarioMedicamentoId id = new UsuarioMedicamentoId(idUsuario, idMedicamento);
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vínculo não encontrado."));
    }

    public UsuarioMedicamento atualizar(
            Integer idUsuario,
            Integer idMedicamento,
            String dosagem
    ) {
        UsuarioMedicamento existente = buscarPorId(idUsuario, idMedicamento);

        existente.setDosagem(dosagem);

        return repository.save(existente);
    }

    public void remover(Integer idUsuario, Integer idMedicamento) {
        UsuarioMedicamentoId id = new UsuarioMedicamentoId(idUsuario, idMedicamento);

        if (!repository.existsById(id)) {
            throw new RuntimeException("Vínculo não encontrado.");
        }

        repository.deleteById(id);
    }
}