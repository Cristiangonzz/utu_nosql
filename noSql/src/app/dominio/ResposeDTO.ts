export interface ResponseDTO<T> {
    status: string; // Esto será el HttpStatus, ej. "CREATED"
    message: string; // Mensaje como "Paciente agregado correctamente"
    data: T; // Datos generics, puede ser null o un objeto
  }