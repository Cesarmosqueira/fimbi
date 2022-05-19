import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginLayoutComponent} from './auth/login-layout/login-layout.component';
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
      },
    ]
	},
  { path: '', 
    component: LoginLayoutComponent,
    loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule)
  }

];

@NgModule({
  imports: [
	  RouterModule.forRoot(routes),
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
