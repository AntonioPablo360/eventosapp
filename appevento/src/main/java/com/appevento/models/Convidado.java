package com.appevento.models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Convidado implements Serializable{
    
    private static final long serialVersionUID=1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @NotEmpty
    private long idConvidado;
    private String rg;
    @NotEmpty
    private String nomeConvidado;
    @ManyToOne
    private Evento evento;
}