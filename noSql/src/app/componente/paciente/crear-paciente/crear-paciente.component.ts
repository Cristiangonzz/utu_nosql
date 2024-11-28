import { Component } from '@angular/core';
import { PacienteDTO } from '../../../dominio/PacienteDTO';
import { HistorialService } from '../../../service/HistorialService';
import { ResponseDTO } from '../../../dominio/ResposeDTO';

@Component({
  selector: 'app-crear-paciente',
  templateUrl: './crear-paciente.component.html',
  styleUrl: './crear-paciente.component.css'
})
export class CrearPacienteComponent {
  nuevoPaciente: PacienteDTO = {
    ci: '',
    nombre: '',
    apellido: '',
    fechaNacimiento: '',
    sexo: '',
  };

  constructor(private historialService: HistorialService) {}

  registrarPaciente(): void {
    this.historialService.agregarPaciente(this.nuevoPaciente).subscribe({
      next: (response:ResponseDTO<PacienteDTO>) => {
        console.log("Respuesta", JSON.stringify(response, null, 2));        
        alert('Paciente registrado correctamente');
        // Opcional: Reinicia el formulario
        this.nuevoPaciente = { ci: '', nombre: '', apellido: '', fechaNacimiento: '', sexo: '' };
      },
      error: (error) => {
        console.error(error);
        alert('El paciente ya existe' + error.message);
      },
    });
  }
}
