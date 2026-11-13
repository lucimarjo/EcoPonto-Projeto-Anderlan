package br.com.ecoponto.ecoponto_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecoponto.ecoponto_api.model.Caminhao;

public interface CaminhaoRepository extends JpaRepository<Caminhao, Long> {

    boolean existsByPlaca(String placa);
}
