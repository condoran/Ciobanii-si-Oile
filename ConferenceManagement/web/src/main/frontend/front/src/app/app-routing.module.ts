import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginPageComponent} from './login-page/login-page.component';
import {RegisterPageComponent} from './register-page/register-page.component';
import {MainPageComponent} from './main-page/main-page.component';
import {ConferenceListComponent} from './conference-list/conference-list.component';
import {ConferenceComponent} from './conference/conference.component';


const routes: Routes = [
  {path: 'login-page', component: LoginPageComponent},
  {path: 'main-page', component: MainPageComponent},
  {path: 'register-page', component: RegisterPageComponent},
  {path: 'conference-list-page', component: ConferenceListComponent},
  {path: 'conference-page/:id', component: ConferenceComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
