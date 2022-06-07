import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import {Bond} from '../models/entities-model';
import {BondsService} from '../services/bonds.service';

@Component({
  selector: 'app-bond-index',
  templateUrl: './bond-index.component.html',
  styleUrls: ['./bond-index.component.css']
})
export class BondIndexComponent implements OnInit {
  bonds: Bond[];
  displayedColumns: string[] = ['Issuer', 'Value', 'Interest Rate', 'Quantity', 'Detail'];

  // capitalization_rate: string;
  // due_date: Date;
  // external_interest_rate: string;
  // interest_rate: number;
  // issuer_identifier: string;
  // nominal_value: number;
  // splits: number;

  constructor(private router : Router, 
              private bondService : BondsService) { }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }
  ngOnInit(): void {
    this.retrieveBonds();
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
