package br.uninter.medalerta.repository;

import br.uninter.medalerta.model.Consumo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsumoRepository extends JpaRepository<Consumo, Integer> {
}