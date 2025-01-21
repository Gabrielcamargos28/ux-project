package com.ux_project.conclusion.domain.doacao;

import com.ux_project.conclusion.domain.categoria.Categoria;
import com.ux_project.conclusion.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "doacao")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Doacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    private Integer quantidade;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dataDaRetirada;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "endereco_id", nullable = false)
    private Endereco enderecoRetirada;
}
