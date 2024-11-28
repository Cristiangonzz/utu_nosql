package utu.nosql.proyecto.controladores;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import utu.nosql.proyecto.dominio.Paciente;
import utu.nosql.proyecto.dominio.RegistroMedico;
import utu.nosql.proyecto.dominio.DTO.PacienteDTO;
import utu.nosql.proyecto.dominio.DTO.RegistroMedicoDTO;
import utu.nosql.proyecto.dominio.DTO.ResponseDTO;
import utu.nosql.proyecto.service.PacienteService;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteService pacienteService;

    public PacienteController(PacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping
    public ResponseEntity<ResponseDTO<Void>> agregarPaciente(@RequestBody PacienteDTO pacienteDTO) {
        try {
            Paciente paciente = new Paciente();
            paciente.setCi(pacienteDTO.getCi());
            paciente.setNombre(pacienteDTO.getNombre());
            paciente.setApellido(pacienteDTO.getApellido());
            paciente.setFechaNacimiento(pacienteDTO.getFechaNacimiento());
            paciente.setSexo(pacienteDTO.getSexo());

            pacienteService.agregarPaciente(paciente);

            // Retornar un Response genérico
            ResponseDTO<Void> response = new ResponseDTO<Void>(
                HttpStatus.CREATED,
                "Paciente agregado correctamente",
                null
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ResponseStatusException e) {

            HttpStatus status = (HttpStatus) e.getStatusCode();

            ResponseDTO<Void> response = new ResponseDTO<>(
                status,
                "Error al agregar paciente: " + e.getReason(),
                null
            );
            return ResponseEntity.status(status).body(response);
        }
    }

    @PostMapping("/{ci}/registros")
    public ResponseEntity<ResponseDTO<Void>> agregarRegistro(@PathVariable String ci, @RequestBody RegistroMedicoDTO registroDTO) {
        try {
            // Convertir el DTO a un objeto RegistroMedico
            RegistroMedico nuevoRegistro = new RegistroMedico();
            nuevoRegistro.setFecha(registroDTO.getFecha());
            nuevoRegistro.setTipo(registroDTO.getTipo());
            nuevoRegistro.setDiagnostico(registroDTO.getDiagnostico());
            nuevoRegistro.setMedico(registroDTO.getMedico());
            nuevoRegistro.setInstitucion(registroDTO.getInstitucion());
            nuevoRegistro.setDescripcion(registroDTO.getDescripcion());
            nuevoRegistro.setMedicacion(registroDTO.getMedicacion());
    
            // Guardar el registro médico para el paciente
            pacienteService.agregarRegistro(ci, nuevoRegistro);

            // Retornar un Response genérico
            ResponseDTO<Void> response = new ResponseDTO<Void>(
                HttpStatus.CREATED,
                "Registro médico agregado correctamente.",
                null
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
            
    
        } catch (ResponseStatusException e) {
            HttpStatus status = (HttpStatus) e.getStatusCode();
            
            if (status == HttpStatus.NOT_FOUND) {
                ResponseDTO<Void> response = new ResponseDTO<>(
                    HttpStatus.PAYMENT_REQUIRED, // Cambiar el código de estado a 402
                    "No existe un paciente con la cédula aportada como parámetro.",
                    null
                );
                return ResponseEntity.status(HttpStatus.PAYMENT_REQUIRED).body(response);
            }

            ResponseDTO<Void> response = new ResponseDTO<>(
                status,
                "Error al agregar registro médico: " + e.getReason(),
                null
            );
            return ResponseEntity.status(status).body(response);
        }
    }

    @GetMapping("/{ci}/registros")
    public ResponseEntity<?> obtenerHistorial(@PathVariable String ci, Pageable pageable) {
        try {
            List<RegistroMedico> historial = pacienteService.obtenerHistorial(ci, pageable);
             // Retornar un Response genérico
             ResponseDTO<List<RegistroMedico>> response = new ResponseDTO<List<RegistroMedico>>(
                HttpStatus.CREATED,
                "Registro del paciente : " + ci,
                historial
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(response);

        } catch (ResponseStatusException e) {

            HttpStatus status = (HttpStatus) e.getStatusCode();

            ResponseDTO<Void> response = new ResponseDTO<>(
                status,
                "Error al obtener registros de un paciente: " + e.getReason(),
                null
            );
            return ResponseEntity.status(status).body(response);

        }
    }

    @GetMapping("/registros")
    public ResponseEntity<?> obtenerRegistrosPorCriterio(@RequestParam Map<String, String> criterios) {
        Map<String, String> criteriosHardcodeados = new HashMap<>();
        criteriosHardcodeados.put("medico", "Alfredo");
        criteriosHardcodeados.put("institucion", "Medica Uruguaya");
        List<RegistroMedico> registros = pacienteService.obtenerRegistrosPorCriterio(criteriosHardcodeados);
        return ResponseEntity.ok(registros);
    }
    
}