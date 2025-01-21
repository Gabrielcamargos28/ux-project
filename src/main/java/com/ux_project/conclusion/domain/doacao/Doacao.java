package com.ux_project.conclusion.domain.doacao;

import com.ux_project.conclusion.domain.categoria.Categoria;
import com.ux_project.conclusion.domain.endereco.Endereco;
import com.ux_project.conclusion.domain.usuario.Usuario;
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

    @ManyToOne
    @JoinColumn(name = "doador_id")
    private Usuario doador;

    @ManyToOne
    @JoinColumn(name = "beneficiario_id")
    private Usuario beneficiario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Date getDataDaRetirada() {
        return dataDaRetirada;
    }

    public void setDataDaRetirada(Date dataDaRetirada) {
        this.dataDaRetirada = dataDaRetirada;
    }

    public Endereco getEnderecoRetirada() {
        return enderecoRetirada;
    }

    public void setEnderecoRetirada(Endereco enderecoRetirada) {
        this.enderecoRetirada = enderecoRetirada;
    }
}
