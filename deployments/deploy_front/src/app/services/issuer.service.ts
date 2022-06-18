import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {Issuer, IssuerReq} from '../models/entities-model';

@Injectable({
  providedIn: 'root'
})
export class IssuerService {

  // http://localhost:8383/api/issuers/pDDc
  private apiBase: string = environment.apiURL;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Issuer[]> {
    return this.http.get<Issuer[]>(`${this.apiBase}/issuers`);
  }

  getByIdentifier(identifier : string): Observable<Issuer> {
    return this.http.get<Issuer>(`${this.apiBase}/issuers/${identifier}`);
  }

  register(issuer : IssuerReq) : Observable<IssuerReq> {
    return this.http.post<IssuerReq>(`${this.apiBase}/issuers`, issuer);
  }
}
