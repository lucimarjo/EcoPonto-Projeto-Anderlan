
package br.com.ecoponto.ecoponto_api.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.ecoponto.ecoponto_api.dto.CadastroUsuarioDTO;
import br.com.ecoponto.ecoponto_api.dto.LoginDTO;
import br.com.ecoponto.ecoponto_api.dto.UsuarioDTO;
import br.com.ecoponto.ecoponto_api.enums.PerfilUsuario;
import br.com.ecoponto.ecoponto_api.model.Usuario;
import br.com.ecoponto.ecoponto_api.repository.UsuarioRepository;

@Service
public class AuthService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(
            UsuarioRepository usuarioRepository,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public UsuarioDTO cadastrar(CadastroUsuarioDTO dados) {

        if (usuarioRepository.existsByEmail(dados.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        Usuario usuario = new Usuario();

        usuario.setNome(dados.nome());
        usuario.setEmail(dados.email());
        usuario.setSenha(passwordEncoder.encode(dados.senha()));
        usuario.setPerfil(PerfilUsuario.USUARIO);

        Usuario usuarioSalvo = usuarioRepository.save(usuario);

        return converterParaDTO(usuarioSalvo);
    }

    public String login(LoginDTO dados) {

        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                dados.email(),
                dados.senha()
            )
        );

        Usuario usuario = usuarioRepository.findByEmail(dados.email())
                .orElseThrow(() -> new IllegalArgumentException(
                    "Usuário não encontrado."
                ));

        return jwtService.gerarToken(usuario);
    }

    private UsuarioDTO converterParaDTO(Usuario usuario) {

        return new UsuarioDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.getPerfil()
        );
    }
}
