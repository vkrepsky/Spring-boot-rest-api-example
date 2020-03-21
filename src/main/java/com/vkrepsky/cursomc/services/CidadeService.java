package com.vkrepsky.cursomc.services;

import com.vkrepsky.cursomc.domain.Cidade;
import com.vkrepsky.cursomc.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class CidadeService {

        @Autowired
        private CidadeRepository repo;

        public List<Cidade> findByEstado(Integer estadoId) {
            return repo.findCidades(estadoId);
        }
    }

