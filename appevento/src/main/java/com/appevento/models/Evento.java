package com.appevento.models;

import java.io.Serializable;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Evento implements Serializable{
    private static final long serialVersionUID=1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long codigo;
    @NotEmpty
    private String nome;
    @NotEmpty
    private String local;
    @NotEmpty
    private String data;
    @NotEmpty
    private String horario;
    @OneToMany
    private List<Convidado> convidados;
}