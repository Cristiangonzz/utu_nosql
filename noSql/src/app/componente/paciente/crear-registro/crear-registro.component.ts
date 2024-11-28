import { Component } from '@angular/core';
import { RegistroMedicoDTO } from '../../../dominio/RegistroMedicoDTO';
import { HistorialService } from '../../../service/HistorialService';
import { NgForm } from '@angular/forms';
import { ResponseDTO } from '../../../dominio/ResposeDTO';

@Component({
  selector: 'app-crear-registro',
  templateUrl: './crear-registro.component.html',
  styleUrl: './crear-registro.component.css'
})
export class CrearRegistroComponent {
  nuevoRegistro: RegistroMedicoDTO = {
    fecha: '',
    tipo: '',
    diagnostico: '',
    medico: '',
    institucion: '',
    descripcion: '',
    medicacion: ''
  };
  ci: string = ''; // Cédula del paciente

  mensajeExito: string = '';
  mensajeError: string = '';

  constructor(private Service: HistorialService) {}

  // Método para registrar el registro médico
  registrarRegistro(form: NgForm) {
    if (form.invalid) {
      return;
    }

    this.Service.agregarRegistro(this.ci, this.nuevoRegistro)
      .subscribe({
        next: (response: ResponseDTO<RegistroMedicoDTO>) => {
          console.log("Respuesta", JSON.stringify(response, null, 2));        
          alert(response.message);
          this.mensajeExito = response.message;
          this.mensajeError = '';
          this.ci='';
          this.nuevoRegistro = { 
            fecha: '',
            tipo: '',
            diagnostico: '',
            medico: '',
            institucion: '',
            descripcion: '',
            medicacion: ''
           };
        },
        error: (error) => {
           // Manejar el caso en el que el paciente no existe
            if (error.status === 402) {
              // Mostrar alerta personalizada si el error es 402 (Paciente no encontrado)
              alert('El paciente con la cédula proporcionada no existe.');
              this.mensajeError = 'No se pudo agregar el registro médico: Paciente no encontrado';
            } else {
              // Mostrar alerta genérica si ocurre otro tipo de error
              alert('Error al agregar el registro médico. Intente nuevamente.');
              this.mensajeError = 'Error al agregar el registro médico';
            }
            this.mensajeExito = '';
        }
      });
  }
}
