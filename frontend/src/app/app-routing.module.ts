import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {BondIndexComponent} from './bond-index/bond-index.component';
import {HomeComponent} from './home/home.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'bondindex', component: BondIndexComponent },
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
