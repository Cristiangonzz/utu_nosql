import { NgModule } from '@angular/core';
import { BrowserModule, provideClientHydration } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ServiceModule } from './service/service.module';
import { PacienteModule } from './componente/paciente/paciente.module';
import { HomeModule } from './componente/home/home.module';
import { PacienteRoutingModule } from './componente/paciente/paciente-routing.module';
import { HomeRoutingModule } from './componente/home/home-routing.module';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    ServiceModule,
    PacienteModule,
    HomeModule,
    PacienteRoutingModule,
    HomeRoutingModule,
    AppRoutingModule
  ],
  providers: [
    provideClientHydration()
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
