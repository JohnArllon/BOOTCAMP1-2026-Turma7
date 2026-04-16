package br.uninter.medalerta.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer idUsuario;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "enderecoRua", length = 100)
    private String enderecoRua;

    @Column(name = "enderecoNumero")
    private Integer enderecoNumero;

    @Column(name = "enderecoComplemento", length = 50)
    private String enderecoComplemento;

    @Column(name = "enderecoBairro", length = 50)
    private String enderecoBairro;

    @Column(name = "enderecoCEP", length = 10)
    private String enderecoCEP;

    @Column(name = "enderecoCidade", length = 50)
    private String enderecoCidade;

    @Column(name = "enderecoEstado", columnDefinition = "CHAR(2)")
    private String enderecoEstado;

    public Usuario() {}

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getEnderecoRua() { return enderecoRua; }
    public void setEnderecoRua(String enderecoRua) { this.enderecoRua = enderecoRua; }

    public Integer getEnderecoNumero() { return enderecoNumero; }
    public void setEnderecoNumero(Integer enderecoNumero) { this.enderecoNumero = enderecoNumero; }

    public String getEnderecoComplemento() { return enderecoComplemento; }
    public void setEnderecoComplemento(String enderecoComplemento) { this.enderecoComplemento = enderecoComplemento; }

    public String getEnderecoBairro() { return enderecoBairro; }
    public void setEnderecoBairro(String enderecoBairro) { this.enderecoBairro = enderecoBairro; }

    public String getEnderecoCEP() { return enderecoCEP; }
    public void setEnderecoCEP(String enderecoCEP) { this.enderecoCEP = enderecoCEP; }

    public String getEnderecoCidade() { return enderecoCidade; }
    public void setEnderecoCidade(String enderecoCidade) { this.enderecoCidade = enderecoCidade; }

    public String getEnderecoEstado() { return enderecoEstado; }
    public void setEnderecoEstado(String enderecoEstado) { this.enderecoEstado = enderecoEstado; }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", enderecoEstado='" + enderecoEstado + '\'' +
                '}';
    }
}