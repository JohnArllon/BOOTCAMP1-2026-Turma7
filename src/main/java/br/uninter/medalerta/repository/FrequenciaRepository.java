package br.uninter.medalerta.repository;

import br.uninter.medalerta.model.Frequencia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrequenciaRepository extends JpaRepository<Frequencia, Integer> {
}