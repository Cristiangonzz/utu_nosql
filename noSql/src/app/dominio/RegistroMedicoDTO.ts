
export interface RegistroMedicoDTO {
    fecha: string; // Fecha del registro médico en formato ISO (yyyy-MM-dd)
    tipo: string; // Tipo de registro médico (ej. consulta, emergencia, etc.)
    diagnostico: string; // Diagnóstico del paciente
    medico: string; // Nombre del médico
    institucion: string; // Institución donde se realizó el registro
    descripcion: string; // Descripción adicional
    medicacion: string; // Medicamentos recetados
  }