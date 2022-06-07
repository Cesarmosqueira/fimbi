import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from 'src/environments/environment';
import {FimbiResponse, User} from '../models/entities-model';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  private apiBase: string = environment.apiURL;

  constructor(private http: HttpClient) {}

  signIn(login: any) {
    return this.http.put<FimbiResponse>(`${this.apiBase}/users/login`, login);
  }

  signUp(user: User) {
    return this.http.post<FimbiResponse>(`${this.apiBase}/users/signup`, user);
  }
}
