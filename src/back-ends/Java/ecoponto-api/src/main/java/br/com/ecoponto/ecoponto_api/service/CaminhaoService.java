
package br.com.ecoponto.ecoponto_api.service;

import br.com.ecoponto.ecoponto_api.dto.CaminhaoDTO;
import br.com.ecoponto.ecoponto_api.model.Caminhao;
import br.com.ecoponto.ecoponto_api.repository.CaminhaoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class CaminhaoService {

    private final CaminhaoRepository caminhaoRepository;

    public CaminhaoService(CaminhaoRepository caminhaoRepository) {
        this.caminhaoRepository = caminhaoRepository;
    }

    @Transactional
    public Caminhao criar(CaminhaoDTO dados) {

        if (caminhaoRepository.existsByPlaca(dados.placa())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe um caminhão com essa placa"
            );
        }

        Caminhao caminhao = new Caminhao();

        caminhao.setIdentificacao(dados.identificacao());
        caminhao.setPlaca(dados.placa());
        caminhao.setAtivo(
                dados.ativo() == null ? true : dados.ativo()
        );

        return caminhaoRepository.save(caminhao);
    }

    @Transactional(readOnly = true)
    public List<Caminhao> listarTodos() {
        return caminhaoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Caminhao buscarPorId(Long id) {
        return buscarEntidade(id);
    }

    @Transactional
    public Caminhao atualizar(Long id, CaminhaoDTO dados) {

        Caminhao caminhao = buscarEntidade(id);

        if (!caminhao.getPlaca().equals(dados.placa())
                && caminhaoRepository.existsByPlaca(dados.placa())) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Já existe um caminhão com essa placa"
            );
        }

        caminhao.setIdentificacao(dados.identificacao());
        caminhao.setPlaca(dados.placa());

        if (dados.ativo() != null) {
            caminhao.setAtivo(dados.ativo());
        }

        return caminhaoRepository.save(caminhao);
    }

    @Transactional
    public void excluir(Long id) {
        Caminhao caminhao = buscarEntidade(id);
        caminhaoRepository.delete(caminhao);
    }

    private Caminhao buscarEntidade(Long id) {
        return caminhaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Caminhão não encontrado"
                ));
    }
}
