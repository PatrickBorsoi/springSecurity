package br.com.senac.security;

import br.com.senac.domain.Usuario;
import br.com.senac.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserDetailsService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario us = usuarioRepository.findByLogin(login);

        if (us == null)
            throw new UsernameNotFoundException("Usuario nao encontrado");
        return us;
    }
}
