import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {FimbiResponse, Login} from 'src/app/models/entities-model';
import {AuthServiceService} from '../auth-service.service';

function validateEmail (emailAdress : string)
{
  let regexEmail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
  if (emailAdress.match(regexEmail)) {
    return true;
  } else {
    return false;
  }
}

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public login : Login = new Login();
  public wrong_username = false;
  public submitted = false;
  public success = false;


  loginResponse : FimbiResponse;

  constructor(
      private authService : AuthServiceService,
      private router : Router,
    ) {
  }
  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
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
      localStorage.setItem("user", this.login.username);
      localStorage.setItem("email", this.login.email);
      localStorage.setItem("password", this.login.password);
      setTimeout(()=>{
        window.location.reload();
      }, 100);
      this.goToPage("");
    }
  }
  onLogin() {
    this.submitted = false;
    this.wrong_username = false;

    let input = this.login.username;

    if(validateEmail(input)) {
      this.login.username = "-1";
      this.login.email = input;
    } else {
      this.login.username = input;
      this.login.email = "-1";
    }


    this.authService.signIn(this.login)
          .subscribe({
        next: (data) => { this.handle_data(data) },
        error: () => { this.handle_error()},
        complete: () => { this.handle_completion()}}
    );
  }

}
