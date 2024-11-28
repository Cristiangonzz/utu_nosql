package utu.nosql.proyecto.dominio;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY) // No incluir si está vacío
public class RegistroMedico {

    private String idRegistro;
    private LocalDate fecha;
    private String tipo;
    private String diagnostico;
    private String medico;
    private String institucion;
    private String descripcion;
    private String medicacion;

    public LocalDate getFecha() {
        return fecha;
    }

    public String getIdRegistro() {
        return idRegistro;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMedicacion() {
        return medicacion;
    }

    public void setMedicacion(String medicacion) {
        this.medicacion = medicacion;
    }

    public void setIdRegistro(String idRegistro){
        this.idRegistro = idRegistro;
    }
}
