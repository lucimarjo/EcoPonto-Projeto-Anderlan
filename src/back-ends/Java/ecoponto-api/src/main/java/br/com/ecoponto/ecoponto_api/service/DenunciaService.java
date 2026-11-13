
package br.com.ecoponto.ecoponto_api.service;

import br.com.ecoponto.ecoponto_api.dto.AtualizarStatusDTO;
import br.com.ecoponto.ecoponto_api.dto.CriarDenunciaDTO;
import br.com.ecoponto.ecoponto_api.dto.DenunciaDTO;
import br.com.ecoponto.ecoponto_api.enums.StatusDenuncia;
import br.com.ecoponto.ecoponto_api.model.Denuncia;
import br.com.ecoponto.ecoponto_api.model.Usuario;
import br.com.ecoponto.ecoponto_api.repository.DenunciaRepository;
import br.com.ecoponto.ecoponto_api.repository.UsuarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class DenunciaService {

    private final DenunciaRepository denunciaRepository;
    private final UsuarioRepository usuarioRepository;

    public DenunciaService(
            DenunciaRepository denunciaRepository,
            UsuarioRepository usuarioRepository) {

        this.denunciaRepository = denunciaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public DenunciaDTO criar(CriarDenunciaDTO dados, Long usuarioId) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"
                ));

        Denuncia denuncia = new Denuncia();

        denuncia.setDescricao(dados.descricao());
        denuncia.setEndereco(dados.endereco());
        denuncia.setLatitude(dados.latitude());
        denuncia.setLongitude(dados.longitude());
        denuncia.setUsuario(usuario);
        denuncia.setStatus(StatusDenuncia.PENDENTE);

        return converterDTO(denunciaRepository.save(denuncia));
    }

    @Transactional(readOnly = true)
    public List<DenunciaDTO> listarTodas() {
        return denunciaRepository.findAll()
                .stream()
                .map(this::converterDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public List<DenunciaDTO> listarDoUsuario(Long usuarioId) {
        return denunciaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::converterDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public DenunciaDTO buscarPorId(Long id) {
        return converterDTO(buscarEntidade(id));
    }

    @Transactional
    public DenunciaDTO atualizarStatus(
            Long id,
            AtualizarStatusDTO dados) {

        Denuncia denuncia = buscarEntidade(id);

        denuncia.setStatus(dados.status());

        return converterDTO(denunciaRepository.save(denuncia));
    }

    @Transactional
    public void excluir(Long id) {
        Denuncia denuncia = buscarEntidade(id);
        denunciaRepository.delete(denuncia);
    }

    private Denuncia buscarEntidade(Long id) {
        return denunciaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Denúncia não encontrada"
                ));
    }

    private DenunciaDTO converterDTO(Denuncia denuncia) {
        return new DenunciaDTO(
                denuncia.getId(),
                denuncia.getDescricao(),
                denuncia.getEndereco(),
                denuncia.getLatitude(),
                denuncia.getLongitude(),
                denuncia.getStatus(),
                denuncia.getDataCriacao(),
                denuncia.getUsuario().getId(),
                denuncia.getUsuario().getNome()
        );
    }
}
