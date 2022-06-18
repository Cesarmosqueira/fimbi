import { Component, OnInit } from '@angular/core';
import {MatRadioChange} from '@angular/material/radio';
import {Bond} from '../models/entities-model';
import {BondsService} from '../services/bonds.service';

@Component({
  selector: 'app-bond-publish',
  templateUrl: './bond-publish.component.html',
  styleUrls: ['./bond-publish.component.css']
})
export class BondPublishComponent implements OnInit {

  selectedValue: number;
  selectedCapitalization : string;
  interest: number;
  bond = new Bond;
  first: boolean;
  error: boolean;
  error_msg :  string;
  submitted: boolean;

  picker : Date;
  constructor(private bondService : BondsService) { 
    this.error = false;
    this.submitted = false;
    this.error_msg = "";
  }

  ngOnInit(): void {
    this.first = true;
    this.interest = 1;
  }

  onChange(event: MatRadioChange) {
    this.interest = event.value;
    this.selectedValue = event.value;
    this.first = false;
  }

  postbond() : void {
    this.bond.capitalization_rate = this.selectedCapitalization;
    console.log(this.selectedValue);
    if (this.interest == 1) {
      this.bond.external_interest_rate = "";
    } else {
      this.bond.interest_rate = -1;
    }
    console.log(this.bond);
    this.bondService.publish(this.bond).subscribe({
      next: (data) => {
        console.log(data);
        this.error = false;
        this.submitted = true;
      },
      error: (e) => {
        this.submitted = true;
        console.error(e);
        this.error = true;
        this.error_msg = e.error.message ? e.error.message : e.name;
      },
    });

  }
}
