package com.tjrn.processosjudiciais.model;

import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "processos")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "numProcesso", nullable = false, unique = true)
    private String numProcesso;

    @Column(name = "vara")
    private String vara;

    @Column(name = "comarca")
    private String comarca;

    @Column(name = "assunto")
    private String assunto;

    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "processo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Audiencia> audienciaList;

}