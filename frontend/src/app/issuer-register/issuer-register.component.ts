import {Component, OnInit} from '@angular/core';
import {IssuerReq} from '../models/entities-model';
import {IssuerService} from '../services/issuer.service';

@Component({
  selector: 'app-issuer-register',
  templateUrl: './issuer-register.component.html',
  styleUrls: ['./issuer-register.component.css']
})
export class IssuerRegisterComponent implements OnInit {

  issuer = new IssuerReq();

  error: boolean;
  error_msg :  string;
  submitted: boolean;
  constructor(private issuerService : IssuerService) { }

  ngOnInit(): void {
    this.error = false;
    this.submitted = false;
    this.error_msg = "";
  }

  onRegister() {
    console.log(this.issuer);
    this.issuerService.register(this.issuer).subscribe({
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
      }
    })

  }

}
