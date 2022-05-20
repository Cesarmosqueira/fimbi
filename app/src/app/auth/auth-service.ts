import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';
import {environment} from 'src/environments/environment';
import {FimbiResponse} from '../entities/entities-model';
import {Login, User} from '../entities/model-auth';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private apiBase: string = environment.apiURL;

  constructor(private http: HttpClient) {}

  signIn(login: Login) {
    return this.http.put<FimbiResponse>(`${this.apiBase}/users/login`, login);
  }

  signUp(user: User) {
    return this.http.post<FimbiResponse>(`${this.apiBase}/users/signup`, user);
  }
}
