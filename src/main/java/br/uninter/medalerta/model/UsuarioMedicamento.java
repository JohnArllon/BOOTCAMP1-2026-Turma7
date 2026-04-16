package br.uninter.medalerta.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Table(name = "UsuarioMedicamento")
public class UsuarioMedicamento {

    @EmbeddedId
    private UsuarioMedicamentoId id;

    @ManyToOne
    @MapsId("idUsuario")
    @JoinColumn(name = "idUsuario", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @MapsId("idMedicamento")
    @JoinColumn(name = "idMedicamento", nullable = false)
    private Medicamento medicamento;

    @Column(name = "dosagem", length = 50)
    private String dosagem;

    public UsuarioMedicamento() {}

    public UsuarioMedicamentoId getId() { return id; }
    public void setId(UsuarioMedicamentoId id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Medicamento getMedicamento() { return medicamento; }
    public void setMedicamento(Medicamento medicamento) { this.medicamento = medicamento; }

    public String getDosagem() { return dosagem; }
    public void setDosagem(String dosagem) { this.dosagem = dosagem; }

    @Override
    public String toString() {
        return "UsuarioMedicamento{" +
                "idUsuario=" + (id != null ? id.getIdUsuario() : null) +
                ", idMedicamento=" + (id != null ? id.getIdMedicamento() : null) +
                ", dosagem='" + dosagem + '\'' +
                '}';
    }
}