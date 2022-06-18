import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Bond, User} from '../models/entities-model';
import {BondsService} from '../services/bonds.service';
import {UsersService} from '../services/users.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  
  bonds : Bond[];
  user = new User;
  username : string;
  today = new Date();
  age : number;

  constructor(private bondService : BondsService, private router : Router, private userService : UsersService) { 
    let uname = this.router.url.split('/', 3)[2];
    console.log(uname);
    if (uname) {
      this.username = uname;
    } else {
      this.username = "undefined";
      console.error("Couldn't load profile")
    }
  }
  emptyBonds() {
    return this.bonds.length == 0;
  }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

  logout(){
    localStorage.removeItem('user');
    localStorage.removeItem('email');
    localStorage.removeItem('password');
    window.location.reload();
    this.goToPage("");
  }

  setAge(birth_date : string) {
    let timeDiff = Math.abs(Date.now() - new Date(birth_date).getTime());
    this.age = Math.floor((timeDiff / (1000 * 3600 * 24))/365.25);
  }

  ngOnInit(): void {
    this.retreiveUser();
    this.retreiveBondsByUser();
  }

  retreiveBondsByUser() {
    this.bondService.getByUsername(this.username).subscribe({
      next: (data) => { this.bonds = data; },
      error: (e) => { console.error(e.error.message); }
    });
  }
  retreiveUser() {
    this.userService.getUser(this.username).subscribe({
      next: (data) => { this.user = data; this.setAge(this.user.birth_date); },
      error: (e) => { console.error(e.error.message); }
    });
  }

}
