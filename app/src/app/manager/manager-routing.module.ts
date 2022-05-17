import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BondDetailComponent} from './bonds/bond-detail/bond-detail.component';

const routes: Routes = [
    { path: 'bonds/:id', component: BondDetailComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManagerRoutingModule { }
