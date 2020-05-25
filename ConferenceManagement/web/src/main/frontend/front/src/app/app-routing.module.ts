import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginPageComponent} from './login-page/login-page.component';
import {RegisterPageComponent} from './register-page/register-page.component';
import {MainPageComponent} from './main-page/main-page.component';
import {ConferenceListComponent} from './conference-list/conference-list.component';
import {ConferenceComponent} from './conference/conference.component';
import {ProposalListComponent} from "./proposal-list/proposal-list.component";
import {ProposalNewComponent} from "./proposal-new/proposal-new.component";
import {ConferenceNewComponent} from "./conference-new/conference-new.component";
import {ProposalUpdateComponent} from "./proposal-update/proposal-update.component";


const routes: Routes = [
  {path: 'login-page', component: LoginPageComponent},
  {path: '', component: MainPageComponent},
  {path: 'register-page', component: RegisterPageComponent},
  {path: 'conference-list-page', component: ConferenceListComponent},
  {path: 'conference/:conferenceID', component: ConferenceComponent},
  {path: 'conference/:conferenceID/proposals', component:ProposalListComponent},
  {path: 'conference/:conferenceID/newProposal', component:ProposalNewComponent},
  {path: 'conference-new', component:ConferenceNewComponent},
  {path: 'conference/:conferenceID/proposals/:proposalID', component: ProposalUpdateComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
