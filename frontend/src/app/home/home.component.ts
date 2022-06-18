import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Bond, Issuer, Purchase} from '../models/entities-model';
import {BondsService} from '../services/bonds.service';
import {IssuerService} from '../services/issuer.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  bonds: Bond[];
  purchases: Purchase[];
  issuers : Issuer[];
  displayedColumns: string[] = ['Issuer', 'Value', 'Interest', 'Quantity', 'Detail'];
  displayedColumnsIssuer: string[] = ['ID'];
  today = new Date();

  // capitalization_rate: string;
  // due_date: Date;
  // external_interest_rate: string;
  // interest_rate: number;
  // issuer_identifier: string;
  // nominal_value: number;
  // splits: number;

  // diff_in_days(date : Date) {
  //   console.log(this.purchases);
  //   let diff = Math.abs(this.today.getTime() - date.getTime());
  //   let diffDays = Math.ceil(diff / (1000 * 3600 * 24)) - 1;
  //   if (diffDays == 0) {
  //     return 'Today';
  //   } else {
  //     return diffDays + " days ago"
  //   }
  // }

  constructor(private router : Router, 
              private bondService : BondsService,
              private issuerService : IssuerService) { }

  display_interest(bond : Bond) {
    if(bond.interest_rate == -1) {
      return bond.external_interest_rate;
    } else {
      return bond.interest_rate;
    }
  }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

  ngOnInit(): void {
    this.retrieveBonds();
    this.retrievePurchases();
    this.retrieveIssuers();
  }

  retrievePurchases() : void {
    this.bondService.getLastPurchases(5).subscribe({
      next: (data) => {
        this.purchases = data;
      },
      error: (e) => console.error(e),
    });
  }

  retrieveBonds() : void {
    this.bondService.getLast(5).subscribe({
      next: (data) => {
        this.bonds = data;
      },
      error: (e) => console.error(e),
    });
  }

  retrieveIssuers() : void {
    this.issuerService.getAll().subscribe({
      next: (data) => {
        this.issuers = data;
      },
      error: (e) => console.error(e),
    });
  }
}
