package utu.nosql.proyecto.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import utu.nosql.proyecto.dominio.Paciente;
import utu.nosql.proyecto.dominio.RegistroMedico;
import utu.nosql.proyecto.repositorios.PacienteRepository;

@Service
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    
    @Autowired
    private MongoTemplate mongoTemplate;

    public PacienteService(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    public void agregarPaciente(Paciente paciente) {
        if (pacienteRepository.existsById(paciente.getCi())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "El paciente ya existe");
        }
        pacienteRepository.save(paciente);
    }

    public void agregarRegistro(String ci, RegistroMedico registro) {
        Paciente paciente = pacienteRepository.findById(ci)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un paciente con la cédula aportada como parámetro"));
        
        registro.setIdRegistro(UUID.randomUUID().toString()); // Genera un ID único para el registro
        paciente.getRegistrosMedicos().add(registro); // Agrega el registro al historial del paciente
        pacienteRepository.save(paciente); // Guarda los cambios
    }

    public List<RegistroMedico> obtenerHistorial(String ci, Pageable pageable) {
        Paciente paciente = pacienteRepository.findById(ci)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No existe un paciente con la cédula aportada como parámetro"));

        List<RegistroMedico> registros = paciente.getRegistrosMedicos();
        registros.sort((r1, r2) -> r2.getFecha().compareTo(r1.getFecha())); // Ordena por fecha descendente

        int start = Math.min((int) pageable.getOffset(), registros.size());
        int end = Math.min(start + pageable.getPageSize(), registros.size());
        return registros.subList(start, end);
    }

    // public List<RegistroMedico> obtenerRegistrosPorCriterio(Map<String, String> criterios) {
    //     List<Paciente> pacientes = pacienteRepository.findAll();
    //     List<RegistroMedico> registrosFiltrados = new ArrayList<>();

    //     for (Paciente paciente : pacientes) {
    //         for (RegistroMedico registro : paciente.getRegistrosMedicos()) {
    //             boolean cumple = criterios.entrySet().stream()
    //                     .allMatch(entry -> {
    //                         try {
    //                             String fieldValue = (String) registro.getClass().getDeclaredField(entry.getKey()).get(registro);
    //                             return fieldValue != null && fieldValue.equalsIgnoreCase(entry.getValue());
    //                         } catch (Exception e) {
    //                             return false; // Campo no encontrado o error al acceder
    //                         }
    //                     });
    //             if (cumple) {
    //                 registrosFiltrados.add(registro);
    //             }
    //         }
    //     }
    //     return registrosFiltrados;
    // }
    public List<RegistroMedico> obtenerRegistrosPorCriterio(Map<String, String> criterios) {
        Query query = new Query();

        // Construir condiciones para los pacientes
        for (Map.Entry<String, String> criterio : criterios.entrySet()) {
            query.addCriteria(Criteria.where(criterio.getKey()).regex(".*" + criterio.getValue() + ".*", "i"));
        }

        // Buscar pacientes que cumplan con los criterios
        List<Paciente> pacientes = mongoTemplate.find(query, Paciente.class);

        // Extraer y combinar registros médicos
        List<RegistroMedico> registrosFiltrados = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            registrosFiltrados.addAll(paciente.getRegistrosMedicos());
        }

        return registrosFiltrados;
    }
}