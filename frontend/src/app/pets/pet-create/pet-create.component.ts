import { Component } from '@angular/core';
import { Pet } from '../../models/Pet';
import { PetService } from '../../services/pet/pet.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pet-create',
  templateUrl: './pet-create.component.html',
  styleUrl: './pet-create.component.css'
})
export class PetCreateComponent {
  pets: Pet[] = [];
  newPet: Pet = {
    id:0,
    name: '',
    age: 0,
    weight: 0,
    imageUrl: ''
  };

  constructor(private petService : PetService, private route:ActivatedRoute, private router: Router){

  }

  fetchPets(): void{
    this.petService.getAllPets().subscribe(
      pets => {
        this.pets = pets;
      },
      error => {
        console.error('Error fetching pets:', error);
      }
    );
  }


  create(): void {
    this.petService.createPet(this.newPet).subscribe(
      createdPet => {
        console.log('Pet created successfully:', createdPet);
        this.newPet = {
          id:0,
          name: '',
          age: 0,
          weight: 0,
          imageUrl: ''
        };

      },
      error => {
        console.error('Error creating pet:', error);
      }
    );

  }

  createPet(): void {
    // Call createPet method when the button is clicked
    this.create();
    this.router.navigateByUrl('/pet');

  }
}
