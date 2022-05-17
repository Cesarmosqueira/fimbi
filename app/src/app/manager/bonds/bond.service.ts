import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import {Bond} from './bond.model';

@Injectable({
  providedIn: 'root'
})
export class BondService {
  private apiBase: string = environment.apiURL;

  constructor(private http:HttpClient) { }

  get_bond() {
    return this.http.get<Bond>(`${this.apiBase}/test/bond`);
  }
}
