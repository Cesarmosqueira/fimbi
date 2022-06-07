import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Bond, Purchase} from '../models/entities-model';
import {BondsService} from '../services/bonds.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  bonds: Bond[];
  purchases: Purchase[];
  displayedColumns: string[] = ['Issuer', 'Value', 'Interest Rate', 'Quantity', 'Detail'];
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
              private bondService : BondsService) { }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }
  ngOnInit(): void {
    this.retrieveBonds();
    this.retrievePurchases();
  }

  retrievePurchases() : void {
    this.bondService.getLastPurchases(5).subscribe({
      next: (data) => {
        this.purchases = data;
        console.log(this.purchases);

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
}
