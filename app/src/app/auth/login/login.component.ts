import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FimbiResponse} from 'src/app/entities/entities-model';
import {Login} from 'src/app/entities/model-auth';
import {AuthService} from '../auth-service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public login : Login = new Login();
  public wrong_username : boolean;
  public submitted : boolean;
  public success : boolean;

  loginResponse : FimbiResponse

  constructor(
    private authService : AuthService,
    private router : Router
  ) { 
  }

  ngOnInit(): void {
    this.submitted = false;
    this.success = false;
  }
  
  handle_data(data : any) {
    this.loginResponse = data;
  }
  handle_error() {
    this.wrong_username = true;
    this.success = false;
    this.submitted = true;
  }
  handle_completion() {
    this.wrong_username = false;
    this.submitted = true;

    if(this.loginResponse.code == 1) {
      this.success = true;
    }
  }

  onLogin() {
    this.submitted = false;
    this.wrong_username = false;

    this.authService.signIn(this.login)
          .subscribe({
        next: (data) => { this.handle_data(data) },
        error: () => { this.handle_error()},
        complete: () => { this.handle_completion()}}
    );

  }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }
}
