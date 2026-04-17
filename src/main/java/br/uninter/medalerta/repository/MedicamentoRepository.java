package br.uninter.medalerta.repository;

import br.uninter.medalerta.model.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {
    Optional<Medicamento> findByNomeComercial(String nomeComercial);
}