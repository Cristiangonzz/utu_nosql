import { Component } from '@angular/core';
import { RegistroMedicoDTO } from '../../../dominio/RegistroMedicoDTO';
import { HistorialService } from '../../../service/HistorialService';

@Component({
  selector: 'app-obtener-registro-criterio',
  templateUrl: './obtener-registro-criterio.component.html',
  styleUrl: './obtener-registro-criterio.component.css'
})
export class ObtenerRegistroCriterioComponent {

 
  criterios: { [key: string]: string } = {};
  registros: any[] = [];
  cargando: boolean = false;
  error: string | null = null;

  constructor(private historialService: HistorialService) {}

  buscarRegistros() {
    this.cargando = true;
    this.error = null;

    this.historialService.obtenerRegistrosPorCriterio(this.criterios).subscribe({
      next: (data) => {
        this.registros = data;
        this.cargando = false;
      },
      error: (err) => {
        this.error = 'Error al obtener registros. Por favor, inténtalo de nuevo.';
        this.cargando = false;
      }
    });
  }
}
