package br.com.ecoponto.ecoponto_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecoponto.ecoponto_api.model.Horario;

public interface HorarioRepository extends JpaRepository<Horario, Long> {

    List<Horario> findByBairroIgnoreCase(String bairro);

    List<Horario> findByDiaSemanaIgnoreCase(String diaSemana);

    List<Horario> findByCaminhaoId(Long caminhaoId);
}
