package utu.nosql.proyecto.dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Document(collection = "pacientes")
@TypeAlias("Paciente") // Define un alias para eliminar la ruta completa
public class Paciente {
    @Id
    private String ci;
    private String nombre;
    private String apellido;
    private LocalDate fechaNacimiento;
    private String sexo;
    @JsonIgnore
    private List<RegistroMedico> registrosMedicos = new ArrayList<>();

    public String getCi() {
        return ci;
    }
    public String getNombre() {
        return nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public String getSexo() {
        return sexo;
    }
    public void setCi(String ci) {
        this.ci = ci;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public void setRegistrosMedicos(List<RegistroMedico> registrosMedicos) {
        this.registrosMedicos = registrosMedicos;
    }
    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public List<RegistroMedico> getRegistrosMedicos() {
        if (registrosMedicos == null) {
            registrosMedicos = new ArrayList<>();
        }
        return registrosMedicos;
    }

    public void setId(String ci){
        this.ci = ci;
    }
}
