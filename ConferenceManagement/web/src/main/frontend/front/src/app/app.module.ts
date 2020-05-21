import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MenuComponent } from './menu/menu.component';
import { MainPageComponent } from './main-page/main-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { RegisterPageComponent } from './register-page/register-page.component';
import {UserService} from './shared/user.service';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import { ConferenceComponent } from './conference/conference.component';
import { ConferenceListComponent } from './conference-list/conference-list.component';
import {ConferenceService} from './shared/conference.service';
import {CookieService} from "ngx-cookie-service";

@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    MainPageComponent,
    LoginPageComponent,
    RegisterPageComponent,
    ConferenceComponent,
    ConferenceListComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [UserService, ConferenceService, CookieService],
  bootstrap: [AppComponent]
})
export class AppModule { }
