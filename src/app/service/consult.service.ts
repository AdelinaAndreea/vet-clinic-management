import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Consult } from '../model/consult';

@Injectable({
  providedIn: 'root'
})
export class ConsultService {

private consultUrl : string;

  constructor(private http : HttpClient) {
    this.consultUrl='http://localhost:8880/api/consult'
   }

   public findAll() : Observable<Consult[]>{
    return this.http.get<Consult[]>(this.consultUrl);
   }

   public addConsult(consult:Consult){
    return this.http.post<Consult>(this.consultUrl,consult);

   }
}
