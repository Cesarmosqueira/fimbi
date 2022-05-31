import { Component, OnInit } from '@angular/core';
import {Bond} from '../entities/entities-model';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  latest_bonds : Bond[];
  constructor() { }

  ngOnInit(): void {
  }

}
