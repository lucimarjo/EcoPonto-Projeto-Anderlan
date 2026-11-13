
package br.com.ecoponto.ecoponto_api.service;

import br.com.ecoponto.ecoponto_api.dto.HorarioDTO;
import br.com.ecoponto.ecoponto_api.model.Caminhao;
import br.com.ecoponto.ecoponto_api.model.Horario;
import br.com.ecoponto.ecoponto_api.repository.CaminhaoRepository;
import br.com.ecoponto.ecoponto_api.repository.HorarioRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class HorarioService {

    private final HorarioRepository horarioRepository;
    private final CaminhaoRepository caminhaoRepository;

    public HorarioService(
            HorarioRepository horarioRepository,
            CaminhaoRepository caminhaoRepository) {

        this.horarioRepository = horarioRepository;
        this.caminhaoRepository = caminhaoRepository;
    }

    @Transactional
    public Horario criar(HorarioDTO dados) {

        Caminhao caminhao = buscarCaminhao(dados.caminhaoId());

        Horario horario = new Horario();

        horario.setDiaSemana(dados.diaSemana());
        horario.setHorario(dados.horario());
        horario.setBairro(dados.bairro());
        horario.setRota(dados.rota());
        horario.setCaminhao(caminhao);

        return horarioRepository.save(horario);
    }

    @Transactional(readOnly = true)
    public List<Horario> listarTodos() {
        return horarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Horario> buscarPorBairro(String bairro) {
        return horarioRepository.findByBairroIgnoreCase(bairro);
    }

    @Transactional(readOnly = true)
    public List<Horario> buscarPorDia(String diaSemana) {
        return horarioRepository.findByDiaSemanaIgnoreCase(diaSemana);
    }

    @Transactional
    public Horario atualizar(Long id, HorarioDTO dados) {

        Horario horario = buscarHorario(id);
        Caminhao caminhao = buscarCaminhao(dados.caminhaoId());

        horario.setDiaSemana(dados.diaSemana());
        horario.setHorario(dados.horario());
        horario.setBairro(dados.bairro());
        horario.setRota(dados.rota());
        horario.setCaminhao(caminhao);

        return horarioRepository.save(horario);
    }

    @Transactional
    public void excluir(Long id) {
        horarioRepository.delete(buscarHorario(id));
    }

    private Horario buscarHorario(Long id) {
        return horarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Horário não encontrado"
                ));
    }

    private Caminhao buscarCaminhao(Long id) {
        return caminhaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Caminhão não encontrado"
                ));
    }
}
