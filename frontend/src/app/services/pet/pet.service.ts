import { Injectable } from '@angular/core';
import { Pet } from '../../models/Pet';
import { AxiosService } from '../../axios.service';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class PetService {

  selectedPet : Pet = {
    id:0,
    name: '',
    age: 0,
    weight: 0,
    imageUrl: ''
  };

  

  constructor(private axiosService: AxiosService) {}

  getAllPets(): Observable<Pet[]> {
    return new Observable<Pet[]>(observer => {
      this.axiosService.request('GET', '/pet', {})
        .then(response => {
          observer.next(response.data);
          observer.complete();
        })
        .catch(error => {
          observer.error(error);
          observer.complete();
        });
    });
  }

  createPet(petData: any): Observable<Pet> {
    return new Observable<Pet>(observer => {
      this.axiosService.request('POST', '/pet-create', petData)
        .then(response => {
          observer.next(response.data);
          observer.complete();
        })
        .catch(error => {
          observer.error(error);
          observer.complete();
        });
    });
  }

  deletePet(petData: any): Observable<Pet> {
    return new Observable<Pet>(observer => {
      this.axiosService.request('POST', '/pet-delete', petData)
        .then(response => {
          observer.next(response.data);
          observer.complete();
        })
        .catch(error => {
          observer.error(error);
          observer.complete();
        });
    });
  }

  setPetToEdit(petData: Pet):void{
    this.selectedPet = petData;
  }
  editPet(petData: any): Observable<Pet> {
    return new Observable<Pet>(observer => {
      this.axiosService.request('POST', '/pet-edit', petData)
        .then(response => {
          observer.next(response.data);
          observer.complete();
        })
        .catch(error => {
          observer.error(error);
          observer.complete();
        });
    });
  }

}
