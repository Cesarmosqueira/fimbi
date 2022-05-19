import {CommonModule} from '@angular/common';
import {NgModule} from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatGridListModule} from '@angular/material/grid-list';
import {MatIconModule} from '@angular/material/icon';
import {MatInputModule} from '@angular/material/input';
import {MatToolbarModule} from '@angular/material/toolbar';
import {AuthRoutingModule} from './auth-routing.module';
import {LoginLayoutComponent} from './login-layout/login-layout.component';
import {LoginComponent} from './login/login.component';
import {RegisterComponent} from './register/register.component';
import {MatCardModule} from '@angular/material/card';


@NgModule({
  declarations: [
    LoginComponent,
    RegisterComponent,
    LoginLayoutComponent
  ],
  imports: [
    CommonModule,
    AuthRoutingModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatGridListModule,
    MatFormFieldModule,
    MatInputModule,
    MatCardModule,
  ]
})
export class AuthModule { }
