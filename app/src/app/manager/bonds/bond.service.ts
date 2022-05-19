import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Bond, Issuer} from 'src/app/entities/entities-model';
import {environment} from 'src/environments/environment';


@Injectable({
  providedIn: 'root'
})
export class BondService {
  private apiBase: string = environment.apiURL;

  constructor(private http:HttpClient) { }

  get_bond() {
    return this.http.get<Bond>(`${this.apiBase}/test`);
  }
  post_issuer() {
    return this.http.post<Issuer>(`${this.apiBase}/issuers/random`, null);
  }
}
