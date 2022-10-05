import { Injectable } from '@angular/core';
import { HttpClient , HttpHeaders} from '@angular/common/http';
import {Pet} from '../model/pet'
import { Observable } from 'rxjs/internal/Observable';

@Injectable({
  providedIn: 'root'
})
export class PetServiceService {
  findPetById(ownerId: any):Observable<Pet[]> {
    return this.http.get<Pet[]>(this.clientUrl+"/"+ownerId+"/pets");
  }
  private petUrl : string;
  private clientUrl : string;

  constructor(private http : HttpClient) {
    this.petUrl='http://localhost:8880/api/pet';
    this.clientUrl='http://localhost:8880/api/client';
   }
   public findAll() :Observable<Pet[]>{
    return this.http.get<Pet[]>(this.petUrl);
   }
   
   public deletePetById(id:any){
    return this.http.delete<Pet>(this.petUrl+"/"+id);
  }
  public updatePetById( pet:Pet){
    return this.http.put<Pet>(this.petUrl,pet);
  }
  public addPet(pet:Pet){
    return this.http.post<Pet>(this.petUrl,pet);
  }
    
}
