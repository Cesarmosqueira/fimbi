import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LayoutComponent} from './layout/layout.component';

const routes: Routes = [
	{
		path:'',
    component: LayoutComponent,
    children: [
      { path: '',
		    loadChildren:()=>import('./manager/manager.module').then((m)=>m.ManagerModule)
      }
    ]
	},

];

@NgModule({
  imports: [
	  RouterModule.forRoot(routes),
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
