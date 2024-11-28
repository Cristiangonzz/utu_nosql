import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CrearPacienteComponent } from './crear-paciente/crear-paciente.component';
import { FormsModule } from '@angular/forms';
import { CrearRegistroComponent } from './crear-registro/crear-registro.component';
import { BrowserModule } from '@angular/platform-browser';
import { ObtenerRegistroComponent } from './obtener-registro/obtener-registro.component';
import { ObtenerRegistroCriterioComponent } from './obtener-registro-criterio/obtener-registro-criterio.component';



@NgModule({
  declarations: [CrearPacienteComponent,CrearRegistroComponent,ObtenerRegistroComponent, ObtenerRegistroCriterioComponent],
  imports: [
    BrowserModule,
    CommonModule,
    FormsModule
  ],
  exports:[CrearPacienteComponent,CrearRegistroComponent,ObtenerRegistroComponent,ObtenerRegistroCriterioComponent]
})
export class PacienteModule { }
