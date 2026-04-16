package br.uninter.medalerta.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Consumo")
public class Consumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idConsumo")
    private Integer idConsumo;

    @Column(name = "idAlerta", nullable = false)
    private Integer idAlerta;

    @Column(name = "dataHoraConsumo")
    private LocalDateTime dataHoraConsumo;

    @Enumerated(EnumType.STRING)
    @Column(name = "confirmacao", nullable = false, length = 10)
    private Confirmacao confirmacao;

    public Consumo() {}

    public Integer getIdConsumo() { return idConsumo; }
    public void setIdConsumo(Integer idConsumo) { this.idConsumo = idConsumo; }

    public Integer getIdAlerta() { return idAlerta; }
    public void setIdAlerta(Integer idAlerta) { this.idAlerta = idAlerta; }

    public LocalDateTime getDataHoraConsumo() { return dataHoraConsumo; }
    public void setDataHoraConsumo(LocalDateTime dataHoraConsumo) { this.dataHoraConsumo = dataHoraConsumo; }

    public Confirmacao getConfirmacao() { return confirmacao; }
    public void setConfirmacao(Confirmacao confirmacao) { this.confirmacao = confirmacao; }

    @Override
    public String toString() {
        return "Consumo{" +
                "idConsumo=" + idConsumo +
                ", idAlerta=" + idAlerta +
                ", dataHoraConsumo=" + dataHoraConsumo +
                ", confirmacao=" + confirmacao +
                '}';
    }
}