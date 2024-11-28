package com.intec.users.implementation;


import com.intec.users.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserSecurityImpl implements UserDetailsService {
    private final UserRepository usuarioRepository;

    @Autowired
    public UserSecurityImpl(UserRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.intec.users.entity.User usuario = this.usuarioRepository.findByUserUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("usuario: " + username + "no encontrado."));

        System.out.println("Informacion de usuario: " + usuario.toString());

        // se crea un arreglo, esto es por si un usuario tiene varios roles, pero en esta api la logica es que un usuario solo tiene un rol
        //String[] roles = new String[] { usuario.getTipoCliente().getNombreTipo() };
        return User.builder()
                .username(usuario.getUserUser())
                .password(usuario.getUserPassword())
                //.roles(roles) //se aplica por rol
                //.authorities(this.grantedAuthorities(roles)) // permisos por roles
                //.accountLocked(usuario.getUsuarioBloqueado())
                //.disabled(usuario.getUsuarioActivo())
                .build();
    }
    private String[] getAuthorities(String role){
        if("Invitado".equals(role)){
            return new String[] {"libro_books"};
        }
        return new String[]{};
    }
    /**
     *Este metodo es el hace internamente Spring Boot para los roles ctrl + click en .roles
     *
     * @param roles
     * @return retorna ROLE_(roles del usuario)
     */
    private List<GrantedAuthority> grantedAuthorities(String[] roles){
        List<GrantedAuthority> authority = new ArrayList<>(roles.length);
        for (String iteraRoles : roles ){
            authority.add(new SimpleGrantedAuthority("ROLE_" + iteraRoles));
            for(String iteraPermisos : this.getAuthorities(iteraRoles)){
                authority.add(new SimpleGrantedAuthority(iteraPermisos));
            }
        }

        return authority;
    }
}
