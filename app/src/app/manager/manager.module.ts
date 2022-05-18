import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ManagerRoutingModule } from './manager-routing.module';
import { BondDetailComponent } from './bonds/bond-detail/bond-detail.component';


@NgModule({
  declarations: [
    BondDetailComponent,
  ],
  imports: [
    CommonModule,
    ManagerRoutingModule,
  ]
})
export class ManagerModule { }
