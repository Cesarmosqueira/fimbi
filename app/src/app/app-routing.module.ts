import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from './home/home.component';
import {LayoutComponent} from './layout/layout.component';

const routes: Routes = [
	{
		path:'',
    component: LayoutComponent,
    children: [
      {
        path: '', 
        component: HomeComponent
      },
      { path: '', 
        loadChildren: () => import('./manager/manager.module').then(m => m.ManagerModule)
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
