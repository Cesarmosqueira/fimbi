import { Component, OnInit } from '@angular/core';
import {BondService} from '../bond.service';
import {Bond} from '../entities-model';

@Component({
  selector: 'app-bond-detail',
  templateUrl: './bond-detail.component.html',
  styleUrls: ['./bond-detail.component.css']
})
export class BondDetailComponent implements OnInit {
  output : Bond;

  constructor(private bondService: BondService) {}

  ngOnInit(): void {
    this.getBond();
    this.create_issuer();

  }

  getBond() {
    // this.bondService.get_bond().subscribe((data) => {
    //   this.output = data;
    // });
  }

  create_issuer() {
    this.bondService.post_issuer().subscribe((data) => {
      console.log(data);
    });
  }

}
