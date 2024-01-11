package com.appevento.repository;

import org.springframework.data.repository.CrudRepository;

import com.appevento.models.Convidado;
import com.appevento.models.Evento;



public interface ConvidadoRepository extends CrudRepository<Convidado,Long> {
    Iterable<Convidado> findByEvento(Evento evento);
    Convidado findByIdConvidado(long idConvidado);
}