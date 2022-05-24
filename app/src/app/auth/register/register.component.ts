import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {User} from '../../entities/model-auth';
import {AuthService} from '../auth-service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  public user : User = new User();
  constructor(
    private authService : AuthService,
    private router : Router
  ) { }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

  ngOnInit(): void {
  }

  onRegister() {
    this.user.image_url = "fimbi";
    console.log(this.user);
    this.authService.signUp(this.user)
          .subscribe({
        next: (data) => {console.log(data)},
        error: (error) => {console.log(error)},
        complete: () => {console.log('No error')}});
    
  }

}
