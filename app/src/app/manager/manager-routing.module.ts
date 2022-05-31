import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BondDetailComponent} from './bonds/bond-detail/bond-detail.component';
import {BondIndexComponent} from './bonds/bond-index/bond-index.component';

const routes: Routes = [
    { path: 'bonds/:id', component: BondDetailComponent},
    { path: 'bondindex', component: BondIndexComponent}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManagerRoutingModule { }
