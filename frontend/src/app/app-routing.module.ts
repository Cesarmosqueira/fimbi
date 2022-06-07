import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BondDetailComponent} from './bond-detail/bond-detail.component';
import {BondIndexComponent} from './bond-index/bond-index.component';
import {HomeComponent} from './home/home.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'bondindex', component: BondIndexComponent },
  { path: 'bond/:bondid', component: BondDetailComponent },
  { path: 'issuer/:issuerid', component: BondDetailComponent },
  {
    path: '',
    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
