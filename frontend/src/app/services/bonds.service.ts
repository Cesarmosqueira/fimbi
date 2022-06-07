import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {Bond, Purchase} from '../models/entities-model';

@Injectable({
  providedIn: 'root'
})
export class BondsService {
  private apiBase: string = environment.apiURL;

  constructor(private http: HttpClient) {}

  getAll(): Observable<Bond[]> {
    return this.http.get<Bond[]>(`${this.apiBase}/bonds`);
  }
  getById(id : number): Observable<Bond> {
    return this.http.get<Bond>(`${this.apiBase}/bonds/${id}`);
  }

  getLast(n : number): Observable<Bond[]> {
    return this.http.get<Bond[]>(`${this.apiBase}/bonds/latest/${n}`);
  }

  getLastPurchases(n : number): Observable<Purchase[]> {
    return this.http.get<Purchase[]>(`${this.apiBase}/bonds/purchases/${n}`);
  }
}
