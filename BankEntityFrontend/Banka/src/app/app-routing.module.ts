import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FormabankeComponent } from './formabanke/formabanke.component';

const routes: Routes = [
  { 
    path: 'banka/card/:transaction',
    component: FormabankeComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
