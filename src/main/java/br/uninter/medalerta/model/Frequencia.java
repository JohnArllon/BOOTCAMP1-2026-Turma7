package br.uninter.medalerta.model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "Frequencia")
public class Frequencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFrequencia")
    private Integer idFrequencia;

    @Column(name = "idUsuario", nullable = false)
    private Integer idUsuario;

    @Column(name = "idMedicamento", nullable = false)
    private Integer idMedicamento;

    @Column(name = "horarioUso", nullable = false)
    private LocalTime horarioUso;

    @Column(name = "intervaloHoras")
    private Integer intervaloHoras;

    @Column(name = "vezesPorDia")
    private Integer vezesPorDia;

    public Frequencia() {}

    public Integer getIdFrequencia() { return idFrequencia; }
    public void setIdFrequencia(Integer idFrequencia) { this.idFrequencia = idFrequencia; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public Integer getIdMedicamento() { return idMedicamento; }
    public void setIdMedicamento(Integer idMedicamento) { this.idMedicamento = idMedicamento; }

    public LocalTime getHorarioUso() { return horarioUso; }
    public void setHorarioUso(LocalTime horarioUso) { this.horarioUso = horarioUso; }

    public Integer getIntervaloHoras() { return intervaloHoras; }
    public void setIntervaloHoras(Integer intervaloHoras) { this.intervaloHoras = intervaloHoras; }

    public Integer getVezesPorDia() { return vezesPorDia; }
    public void setVezesPorDia(Integer vezesPorDia) { this.vezesPorDia = vezesPorDia; }

    @Override
    public String toString() {
        return "Frequencia{" +
                "idFrequencia=" + idFrequencia +
                ", idUsuario=" + idUsuario +
                ", idMedicamento=" + idMedicamento +
                ", horarioUso=" + horarioUso +
                ", intervaloHoras=" + intervaloHoras +
                ", vezesPorDia=" + vezesPorDia +
                '}';
    }
}