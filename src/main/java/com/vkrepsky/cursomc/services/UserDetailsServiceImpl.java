package com.vkrepsky.cursomc.services;

import com.vkrepsky.cursomc.domain.Cliente;
import com.vkrepsky.cursomc.repositories.ClienteRepository;
import com.vkrepsky.cursomc.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Cliente cli= clienteRepository.findByEmail(email);
        if(cli == null){
            throw new UsernameNotFoundException(email);
        }
        return new UserSS(cli.getId(), cli.getEmail(), cli.getSenha(),cli.getPerfil());
    }
}
