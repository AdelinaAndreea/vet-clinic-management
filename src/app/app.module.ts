import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { AppComponent } from './app.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';


import { FormBuilder, FormsModule, NgControl, ReactiveFormsModule } from '@angular/forms';
import { PetListComponent } from './pet-list/pet-list.component';
import { PetDetailsComponent } from './pet-details/pet-details.component';
import { PetCreateComponent } from './pet-create/pet-create.component';
import { ClientDetailsComponent } from './client-details/client-details.component';
import { ClientListComponent } from './client-list/client-list.component';
import { ConsultListComponent } from './consult-list/consult-list.component';
import { LoginComponent } from './login/login.component';
import { RequestInterceptor } from './security/request-interceptor';


const routes: Routes = [
  {
    path: 'pets',
    children: [
      { path: '', component: PetListComponent },
      { path: 'details', component: PetDetailsComponent },
      { path: ':ownerId', component: PetListComponent }
    ]
  },
  { path: 'clients', component: ClientListComponent },
  { path: 'clients/details', component: ClientDetailsComponent },
  { path: 'consults', component: ConsultListComponent },
  { path: 'login', component: LoginComponent }
];

@NgModule({
  declarations: [
    AppComponent,
    PetListComponent,
    PetDetailsComponent,
    PetCreateComponent,
    ClientDetailsComponent,
    ClientListComponent,
    ConsultListComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    FormsModule,
    CommonModule,
    ReactiveFormsModule
  ],
  exports: [RouterModule],
  providers: [
    FormBuilder,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: RequestInterceptor,
      multi: true,
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {

}
