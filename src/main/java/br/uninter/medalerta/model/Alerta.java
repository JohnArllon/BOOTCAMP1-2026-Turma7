package br.uninter.medalerta.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Alerta")
public class Alerta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlerta")
    private Integer idAlerta;

    @Column(name = "idFrequencia", nullable = false)
    private Integer idFrequencia;

    @Column(name = "dataHoraAlerta", nullable = false)
    private LocalDateTime dataHoraAlerta;

    @Enumerated(EnumType.STRING)
    @Column(name = "statusAlerta", nullable = false, length = 20)
    private StatusAlerta statusAlerta;

    public Alerta() {}

    public Integer getIdAlerta() { return idAlerta; }
    public void setIdAlerta(Integer idAlerta) { this.idAlerta = idAlerta; }

    public Integer getIdFrequencia() { return idFrequencia; }
    public void setIdFrequencia(Integer idFrequencia) { this.idFrequencia = idFrequencia; }

    public LocalDateTime getDataHoraAlerta() { return dataHoraAlerta; }
    public void setDataHoraAlerta(LocalDateTime dataHoraAlerta) { this.dataHoraAlerta = dataHoraAlerta; }

    public StatusAlerta getStatusAlerta() { return statusAlerta; }
    public void setStatusAlerta(StatusAlerta statusAlerta) { this.statusAlerta = statusAlerta; }

    @Override
    public String toString() {
        return "Alerta{" +
                "idAlerta=" + idAlerta +
                ", idFrequencia=" + idFrequencia +
                ", dataHoraAlerta=" + dataHoraAlerta +
                ", statusAlerta=" + statusAlerta +
                '}';
    }
}