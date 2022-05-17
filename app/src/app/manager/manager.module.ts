import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ManagerRoutingModule } from './manager-routing.module';
import { BondDetailComponent } from './bonds/bond-detail/bond-detail.component';
import { BondListComponent } from './bonds/bond-list/bond-list.component';


@NgModule({
  declarations: [
    BondDetailComponent,
    BondListComponent,
  ],
  imports: [
    CommonModule,
    ManagerRoutingModule,
  ]
})
export class ManagerModule { }
