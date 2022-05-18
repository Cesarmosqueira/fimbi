import { Component, OnInit } from '@angular/core';
import {Bond} from '../bond.model';
import {BondService} from '../bond.service';

@Component({
  selector: 'app-bond-detail',
  templateUrl: './bond-detail.component.html',
  styleUrls: ['./bond-detail.component.css']
})
export class BondDetailComponent implements OnInit {
  bond : Bond;

  constructor(private bondService: BondService) {}

  ngOnInit(): void {
    this.getBond();
  }

  getBond() {
    this.bondService.get_bond().subscribe((data) => {
      this.bond = data;
    });
  }

  create_chart() {
  }

}
