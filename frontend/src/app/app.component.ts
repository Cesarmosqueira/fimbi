import { Component } from '@angular/core';
import {Router} from '@angular/router';
import {AuthServiceService} from './auth/auth-service.service';
import {FimbiResponse, Login} from './models/entities-model';

function titleCaseWord(word: string) {
  if (!word) return word;
  return word[0].toUpperCase() + word.substr(1).toLowerCase();
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'frontend';
  navtitle = '';

  profile_button = "Login"

  loginResponse : FimbiResponse;
  constructor(private router : Router,
             private authService : AuthServiceService) { }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

  ngOnInit(): void {
    let page = this.router.url.split('/', 2)[1];
    if (page) {
      this.navtitle = titleCaseWord(page);
    } else {
      this.navtitle = "Home";
    }
    this.load_button();
  }

  load_button() {
    if (localStorage.getItem("user") ||
        localStorage.getItem("email") && 
        localStorage.getItem("password")) {
      this.profile_button = "Profile"
    } else {
      this.profile_button = "Login"

    }

  }

  handle_data(data : any) {
    this.loginResponse = data;
  }
  handle_error() {
    this.goToPage('login');

  }

  handle_completion() {

    if(this.loginResponse.code == 1) {
      this.goToPage("u/penemene");
    }
  }

  goToProfile() : void {
    if (localStorage.getItem("user") ||
        localStorage.getItem("email") && 
        localStorage.getItem("password")) {
      let login = {
        'username': localStorage.getItem("user"),
        'email': localStorage.getItem("email"),
        'password': localStorage.getItem("password"),
      }

      this.authService.signIn(login)
            .subscribe({
          next: (data) => { this.handle_data(data) },
          error: () => { this.handle_error()},
          complete: () => { this.handle_completion()}}
      );
    } else {
      this.handle_error();
    }
  }

}
