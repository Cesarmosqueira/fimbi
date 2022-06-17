import { Component, OnInit } from '@angular/core';
import {Bond} from '../models/entities-model';

@Component({
  selector: 'app-bond-publish',
  templateUrl: './bond-publish.component.html',
  styleUrls: ['./bond-publish.component.css']
})
export class BondPublishComponent implements OnInit {

  bond = new Bond;
  constructor() { }

  ngOnInit(): void {
  }

  postbond() : void {

  }


}
