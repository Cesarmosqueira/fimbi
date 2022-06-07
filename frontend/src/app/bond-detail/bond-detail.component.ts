import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {Bond, Issuer} from '../models/entities-model';
import {BondsService} from '../services/bonds.service';
import {IssuerService} from '../services/issuer.service';

@Component({
  selector: 'app-bond-detail',
  templateUrl: './bond-detail.component.html',
  styleUrls: ['./bond-detail.component.css']
})
export class BondDetailComponent implements OnInit {
  
  bond_id : number;
  bond : Bond = new Bond;
  issuer : Issuer = new Issuer;
  constructor(private router : ActivatedRoute, 
              private bondService : BondsService,
              private issuerService : IssuerService,
              private navigator : Router) { }


  goToPage(pageName:string){
    this.navigator.navigate([`${pageName}`]);
  }
  ngOnInit(): void {
    let optional  = this.router.snapshot.paramMap.get('bondid');
    if(optional) {
      this.bond_id = Number(optional);
      this.load_bond();
    }
  }

  load_bond() : void {
    this.bondService.getById(this.bond_id).subscribe({
      next: (data) => {
        this.bond = data;
        this.load_issuer(this.bond.issuer_identifier)
      },
      error: (e) => console.error(e),
    });
  }

  load_issuer(identifier : string) : void {
    this.issuerService.getByIdentifier(identifier).subscribe({
      next: (data) => {
        this.issuer = data;
      },
      error: (e) => console.error(e),
    })
  }
}
