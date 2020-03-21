package com.vkrepsky.cursomc.services;

import com.vkrepsky.cursomc.domain.Cliente;
import com.vkrepsky.cursomc.repositories.ClienteRepository;
import com.vkrepsky.cursomc.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BCryptPasswordEncoder be;

    @Autowired
    private EmailService emailService;

    private Random random = new Random();

    public void sendNewPassword(String email){

        Cliente cliente = clienteRepository.findByEmail(email);
        if(cliente == null){
            throw new ObjectNotFoundException("Email nao encontrado");
        }
        String newPass = newPassword();
        cliente.setSenha(be.encode(newPass));

        clienteRepository.save(cliente);
        emailService.sendNewPasswordEmail(cliente, newPass);
    }

    private String newPassword(){
        char[] vet = new char[10];
        for (int i=0; i<10; i++){
            vet[i] = randomChar();
        }
        return new String(vet);
    }

    private char randomChar(){
        int opt = random.nextInt(3);
        if(opt == 0){
            return (char) (random.nextInt(10) + 48);
        }
        else  if(opt == 1){
            return (char) (random.nextInt(26) + 65);
        }
        else {
            return (char) (random.nextInt(26) + 97);
        }
    }
}
