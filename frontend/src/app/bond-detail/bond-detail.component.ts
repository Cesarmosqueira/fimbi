import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Bond, ChartData, Issuer, LoginOptional } from '../models/entities-model';
import { BondsService } from '../services/bonds.service';
import { IssuerService } from '../services/issuer.service';

@Component({
  selector: 'app-bond-detail',
  templateUrl: './bond-detail.component.html',
  styleUrls: ['./bond-detail.component.css']
})
export class BondDetailComponent implements OnInit {
  
  error: boolean;
  response :  string;
  submitted: boolean;
  unit_value : number;
  bond_id : number;
  loaded : number;
  cash_flow : any[][]; 
  cash_data : ChartData;
  bond : Bond = new Bond;
  issuer : Issuer = new Issuer;
  constructor(private router : ActivatedRoute, 
              private bondService : BondsService,
              private issuerService : IssuerService,
              private navigator : Router) { }

  formatMoney(n : number) {
    return "US$ " + (Math.round(n * 100) / 100).toLocaleString();
  }

  formatPercentage(n : number) {
    return parseFloat(n.toString()).toFixed(2)+"%"
  }

  goToPage(pageName:string){
    this.navigator.navigate([`${pageName}`]);
  }
  ngOnInit(): void {
    let optional  = this.router.snapshot.paramMap.get('bondid');
    if(optional) {
      this.bond_id = Number(optional);
      this.load_bond();
      this.retrieveCashFlow();
    }

    this.loaded = 0;
    this.error = false;
    this.submitted = false;
    this.response = "";
  }

  capitalization() : string{
    let cap = this.bond.capitalization_rate;
    if (cap == '1') {
      return "Daily";
    }
    else if (cap == '2') {
      return "Monthly";
    }
    else if (cap == '3') {
      return "Yearly";
    } else {
      return cap;
    }
  }

  retrieveCashFlow() {
    this.bondService.getCashFlowById(this.bond_id).subscribe({
      next: (data) => {
        console.log(data);
        this.cash_data = data;
        let values = [];
        for(let i = 0; i < data.data.length; i++) {
          values.push([data.data[i].date.toLocaleString()
          , data.data[i].value])
        }
        this.cash_flow = values;
        console.log(this.cash_flow);
        this.loaded = 1;
      },
      error: (e) => {
        console.error(e);
        this.loaded = -1;
      }
    });

  }

  load_bond() : void {
    this.bondService.getById(this.bond_id).subscribe({
      next: (data) => {
        console.log(data);
        this.bond = data;
        this.load_issuer(this.bond.issuer_identifier)
        this.unit_value = this.bond.nominal_value / this.bond.splits
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

  purchase_bond() : void {
    if (localStorage.getItem("user") ||
        localStorage.getItem("email") && 
        localStorage.getItem("password")) {
      if(confirm("Confirmacion para comprar el bono por " + this.formatMoney(this.unit_value))) {
        console.log("done");
        let login = new LoginOptional();
        login.username = localStorage.getItem("user") ?  localStorage.getItem("user") : "-1";
        login.email = localStorage.getItem("email") ?  localStorage.getItem("email") : "-1";
        login.password = localStorage.getItem("password") ?  localStorage.getItem("password") : "-1";
        console.log(login);
        this.bondService.purchase(login, this.bond_id).subscribe( {
            next: (data) => { 
              console.log(data); 
              this.submitted = true; 
              this.error = false;
              this.response = data.description;
            },
            error: (e) => { 
              console.error(e);
              this.submitted = true; 
              this.error = true;
              this.response = e.error.message;
            }
          }
        )
      } else {
        console.log("aborted");
      }
    } else {
      this.goToPage('login');
    }
      
  }
}
