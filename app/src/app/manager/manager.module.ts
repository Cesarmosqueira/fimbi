import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ManagerRoutingModule } from './manager-routing.module';
import { BondDetailComponent } from './bonds/bond-detail/bond-detail.component';
import { BondIndexComponent } from './bonds/bond-index/bond-index.component';
import {RouterModule} from '@angular/router';


@NgModule({
  declarations: [
    BondDetailComponent,
    BondIndexComponent,
  ],
  imports: [
    CommonModule,
    ManagerRoutingModule,
    RouterModule
  ]
})
export class ManagerModule { }
