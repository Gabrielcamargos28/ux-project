package com.ux_project.conclusion.domain.usuario;

import com.ux_project.conclusion.domain.doacao.Doacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "login")
    private String login;
    @Column(name = "senha")
    private String senha;
    @Column(name = "email")
    private String email;

    @Column(name = "tipo")
    private String tipo;

    @OneToMany(mappedBy = "doador", cascade = CascadeType.ALL)
    private List<Doacao> doacoesRealizadas;

    @OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL)
    private List<Doacao> doacoesRecebidas;


    public Usuario(DadosCriarUsuario criarUsuario) {
        this.nome = criarUsuario.nome();
        this.login = criarUsuario.login();
        this.senha = criarUsuario.senha();
        this.email = criarUsuario.email();
        this.tipo = criarUsuario.tipo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<Doacao> getDoacoesRealizadas() {
        return doacoesRealizadas;
    }

    public void setDoacoesRealizadas(List<Doacao> doacoesRealizadas) {
        this.doacoesRealizadas = doacoesRealizadas;
    }

    public List<Doacao> getDoacoesRecebidas() {
        return doacoesRecebidas;
    }

    public void setDoacoesRecebidas(List<Doacao> doacoesRecebidas) {
        this.doacoesRecebidas = doacoesRecebidas;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
