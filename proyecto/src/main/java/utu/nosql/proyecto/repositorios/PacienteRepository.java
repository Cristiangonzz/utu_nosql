package utu.nosql.proyecto.repositorios;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import utu.nosql.proyecto.dominio.Paciente;

@Repository
public interface PacienteRepository extends MongoRepository<Paciente, String> {
    // Optional<Paciente> findByCi(String ci); // Esto permite buscar por 'ci'
}
