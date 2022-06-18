import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {User} from 'src/app/models/entities-model';
import {AuthServiceService} from '../auth-service.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  public user : User = new User();
  constructor(
    private authService : AuthServiceService,
    private router : Router
  ) { }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

  ngOnInit(): void {
  }

  onRegister() {
    console.log(this.user);
    this.authService.signUp(this.user)
          .subscribe({
        next: (data) => {
          console.log(data);
          localStorage.setItem("user", this.user.username);
          localStorage.setItem("email", this.user.email);
          localStorage.setItem("password", this.user.password);
          setTimeout(()=>{
            window.location.reload();
          }, 100);
          this.goToPage('');
        },
        error: (error) => {console.log(error)},
        complete: () => {console.log('No error')}});

  }
}
