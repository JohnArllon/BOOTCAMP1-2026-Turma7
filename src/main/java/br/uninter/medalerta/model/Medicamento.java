package br.uninter.medalerta.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Medicamento")
public class Medicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMedicamento")
    private Integer idMedicamento;

    @Column(name = "nomeComercial", nullable = false, length = 100)
    private String nomeComercial;

    @Column(name = "nomeGenerico", length = 100)
    private String nomeGenerico;

    @Enumerated(EnumType.STRING)
    @Column(name = "quantidade", length = 20)
    private Quantidade quantidade;

    @Column(name = "formaUso", length = 100)
    private String formaUso;

    @Column(name = "observacao", length = 200)
    private String observacao;

    @OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL)
    private List<UsuarioMedicamento> usuarioMedicamentos = new ArrayList<>();

    public Medicamento() {}

    public Integer getIdMedicamento() { return idMedicamento; }
    public void setIdMedicamento(Integer idMedicamento) { this.idMedicamento = idMedicamento; }

    public String getNomeComercial() { return nomeComercial; }
    public void setNomeComercial(String nomeComercial) { this.nomeComercial = nomeComercial; }

    public String getNomeGenerico() { return nomeGenerico; }
    public void setNomeGenerico(String nomeGenerico) { this.nomeGenerico = nomeGenerico; }

    public Quantidade getQuantidade() { return quantidade; }
    public void setQuantidade(Quantidade quantidade) { this.quantidade = quantidade; }

    public String getFormaUso() { return formaUso; }
    public void setFormaUso(String formaUso) { this.formaUso = formaUso; }

    public String getObservacao() { return observacao; }
    public void setObservacao(String observacao) { this.observacao = observacao; }
}