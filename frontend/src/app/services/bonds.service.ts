import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {Bond, BondReq, ChartData, FimbiResponse, LoginOptional, Purchase} from '../models/entities-model';

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

  getCashFlowById(id : number): Observable<ChartData> {
    return this.http.get<ChartData>(`${this.apiBase}/bonds/cash_flow/${id}`);
  }

  getLast(n : number): Observable<Bond[]> {
    return this.http.get<Bond[]>(`${this.apiBase}/bonds/latest/${n}`);
  }

  getByUsername(username : String) : Observable<Bond[]> {
    return this.http.get<Bond[]>(`${this.apiBase}/bonds/user/${username}`);
  }

  getByIssuer(identifier : String) : Observable<Bond[]> {
    return this.http.get<Bond[]>(`${this.apiBase}/bonds/issuer/${identifier}`);
  }

  getLastPurchases(n : number): Observable<Purchase[]> {
    return this.http.get<Purchase[]>(`${this.apiBase}/bonds/purchases/${n}`);
  }

  purchase(login : LoginOptional, bond_id : number) : Observable<FimbiResponse> {
    return this.http.post<FimbiResponse>(`${this.apiBase}/users/adquire_bond/\?bond_id\=${bond_id}`, login);
  }

  publish(bond : BondReq) : Observable<BondReq> {
    return this.http.post<BondReq>(`${this.apiBase}/bonds`, bond);
  }

  getLiborData() : Observable<any> {
    return this.http.get<any>('https://data.nasdaq.com/api/v3/datasets/OPEC/ORB.json')
  }

}
