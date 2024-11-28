import { Component } from '@angular/core';
import { HistorialService } from '../../../service/HistorialService';
import { RegistroMedicoDTO } from '../../../dominio/RegistroMedicoDTO';
import { ResponseDTO } from '../../../dominio/ResposeDTO';

@Component({
  selector: 'app-obtener-registro',
  templateUrl: './obtener-registro.component.html',
  styleUrl: './obtener-registro.component.css'
})
export class ObtenerRegistroComponent {

  registros: RegistroMedicoDTO[] = [];
  ci: string = ''; // Cédula del paciente
  page: number = 0; // Página inicial
  size: number = 1; // Cantidad de registros por página
  totalPages: number = 0; // Total de páginas, si es necesario para paginación
  totalElements: number = 0; // Total de registros

  constructor(private historialService: HistorialService) {}

  ngOnInit(): void {
    // Llamada inicial para cargar el historial
    if (this.ci) {
      this.obtenerRegistros();
    }
  }

  obtenerRegistros(): void {
    this.historialService.obtenerHistorial(this.ci, this.page, this.size).subscribe({
      next: (data: ResponseDTO<RegistroMedicoDTO[]>) => {
        this.registros = data.data;
        // Si tienes los datos de paginación en la respuesta, puedes asignarlos aquí
        // this.totalPages = data.totalPages;
        // this.totalElements = data.totalElements;
      },
      error: (error) => {
        console.error('Error al obtener los registros', error);
      }
    });
  }

  // Método para cambiar de página
  cambiarPagina(page: number): void {
    this.page = page;
    this.obtenerRegistros();
  }

}
