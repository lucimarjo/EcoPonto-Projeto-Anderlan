
package br.com.ecoponto.ecoponto_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecoponto.ecoponto_api.enums.StatusDenuncia;
import br.com.ecoponto.ecoponto_api.model.Denuncia;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {

    List<Denuncia> findByUsuarioId(Long usuarioId);

    List<Denuncia> findByStatus(StatusDenuncia status);
}
