import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { Client } from '../model/client';

@Injectable({
  providedIn: 'root'
})
export class ClientService {
  private clientUrl: string;

  constructor(private http:HttpClient) {
    this.clientUrl='http://localhost:8880/api/client'
   }
   public findAll() :Observable<Client[]>{
    return this.http.get<Client[]>(this.clientUrl);
   }
   public deleteClientById(id:any){
    return this.http.delete<Client>(this.clientUrl+"/"+id);
  }
  public createClient( client:Client){
    return this.http.post<Client>(this.clientUrl,client);
}
  public updateClientById( client:Client){
    return this.http.put<Client>(this.clientUrl,client);
}
}