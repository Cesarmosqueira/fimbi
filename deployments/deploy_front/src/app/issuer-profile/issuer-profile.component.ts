import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Bond, Issuer} from '../models/entities-model';
import {BondsService} from '../services/bonds.service';
import {IssuerService} from '../services/issuer.service';

@Component({
  selector: 'app-issuer-profile',
  templateUrl: './issuer-profile.component.html',
  styleUrls: ['./issuer-profile.component.css']
})
export class IssuerProfileComponent implements OnInit {

  bonds : Bond[];
  issuer = new Issuer;
  identifier : string;
  today = new Date();
  years : number;

  constructor(private navigator : Router,
              private bondService : BondsService,
              private issuerService : IssuerService,
              private router : Router) { 

    this.years = 0;
    let uname = this.router.url.split('/', 3)[2];
    console.log(uname);
    if (uname) {
      this.identifier = uname;
    } else {
      this.identifier = "undefined";
      console.error("Couldn't load profile")
    }
  }

  ngOnInit(): void {
    this.retreiveIssuer();
    this.retreiveBondsByIssuer();
  }

  setAge(birth_date : string) {
    let timeDiff = Math.abs(Date.now() - new Date(birth_date).getTime());
    this.years = Math.floor((timeDiff / (1000 * 3600 * 24))/365.25);
  }

  retreiveBondsByIssuer() {
    this.bondService.getByIssuer(this.identifier).subscribe({
      next: (data) => { this.bonds = data; console.log(data);},
      error: (e) => { console.error(e.error.message); }
    });
  }
  retreiveIssuer() {
    this.issuerService.getByIdentifier(this.identifier).subscribe({
      next: (data) => { this.issuer = data; this.setAge(this.issuer.date_joined); },
      error: (e) => { console.error(e.error.message); }
    });
  }


  emptyBonds() {
    if (this.bonds) {
      return this.bonds.length == 0;
    } else {
      return true;
    }
  }

  goToPage(pageName:string){
    this.navigator.navigate([`${pageName}`]);
  }

}
