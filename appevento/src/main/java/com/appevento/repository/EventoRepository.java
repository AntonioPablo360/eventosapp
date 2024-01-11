package com.appevento.repository;

import org.springframework.data.repository.CrudRepository;

import com.appevento.models.Evento;

public interface EventoRepository extends CrudRepository<Evento, Long> {
    Evento findByCodigo(long codigo);
}