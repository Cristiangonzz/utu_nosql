import { HttpClient, HttpParams } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { PacienteDTO } from "../dominio/PacienteDTO";
import { RegistroMedicoDTO } from "../dominio/RegistroMedicoDTO";
import { ResponseDTO } from "../dominio/ResposeDTO";

@Injectable({ providedIn: 'root' })
export class HistorialService {
    private baseUrl = 'http://localhost:8080/api/pacientes';

    constructor(private http: HttpClient) {}

    agregarPaciente(paciente: PacienteDTO): Observable<ResponseDTO<PacienteDTO>> {
        return this.http.post<ResponseDTO<PacienteDTO>>(`${this.baseUrl}`, paciente);
    }

    agregarRegistro(ci: string, registro: RegistroMedicoDTO): Observable<ResponseDTO<RegistroMedicoDTO>>  {
        return this.http.post<ResponseDTO<RegistroMedicoDTO>>(`${this.baseUrl}/${ci}/registros`, registro);
    }

    obtenerHistorial(ci: string, page: number, size: number): Observable<ResponseDTO<RegistroMedicoDTO[]>>  {
        return this.http.get<ResponseDTO<RegistroMedicoDTO[]>>(`${this.baseUrl}/${ci}/registros`, {
            params: { page, size }
        });
    }
   
    obtenerRegistrosPorCriterio(criterios: { [key: string]: string }): Observable<any> {
    let params = new HttpParams();
    for (const key in criterios) {
        if (criterios[key]) {
        params = params.set(key, criterios[key]);
        }
    }

    return this.http.get(`${this.baseUrl}/registros`, { params });
    }
}