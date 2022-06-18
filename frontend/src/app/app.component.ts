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
    if (localStorage.getItem("user")) {
      this.navtitle = "Logged as " + localStorage.getItem("user");
    } else {
      this.navtitle = "Not logged";
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
          next: () => { this.goToPage(`u/${login.username}`); },
          error: (e) => { console.error(e); }}
      );
    } else {
      this.goToPage("login");
    }
  }
}
