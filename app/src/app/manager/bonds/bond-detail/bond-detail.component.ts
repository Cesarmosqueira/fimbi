import { Component, OnInit } from '@angular/core';
import {Bond} from 'src/app/entities/entities-model';
import {BondService} from '../bond.service';

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
