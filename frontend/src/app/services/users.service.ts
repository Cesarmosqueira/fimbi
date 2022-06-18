import {HttpClient} from '@angular/common/http';
import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {environment} from 'src/environments/environment';
import {User} from '../models/entities-model';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private apiBase: string = environment.apiURL;
  constructor(private http: HttpClient) {}

  getUser(username : string): Observable<User> {
    return this.http.get<User>(`${this.apiBase}/users/${username}`);
  }
}
