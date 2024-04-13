import { Component } from '@angular/core';
import { Pet } from '../../models/Pet';
import { PetService } from '../../services/pet/pet.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pet',
  templateUrl: './pet.component.html',
  styleUrl: './pet.component.css'
})
export class PetComponent {
  pets: Pet[] = [];
  newPet: Pet = {
    id:0,
    name: '',
    age: 0,
    weight: 0,
    imageurl: ''
  };
  username: string = '';

  constructor(private petService : PetService, private route:ActivatedRoute){

  }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.username = params['username'];
      console.log('Usernameu vietii:', this.username);
    });
  

    this.petService.getAllPets().subscribe(
      pets => {
         this.pets = pets;
      },
      error => {
        console.error('Error fetching pets:', error);
      }
    );
  }

}
