import { Component } from '@angular/core';
import { Pet } from '../../models/Pet';
import { PetService } from '../../services/pet/pet.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pet-delete',
  templateUrl: './pet-delete.component.html',
  styleUrl: './pet-delete.component.css'
})
export class PetDeleteComponent {
  pets: Pet[] = [];

  constructor(private petService : PetService, private route:ActivatedRoute, private router:Router){

  }

  ngOnInit(): void {
    this.fetchPets();
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

  deletePet(pet: Pet): void {

    this.petService.deletePet(pet).subscribe(
      () => {
        console.log('Pet deleted successfully');
        this.fetchPets();
      },
      error => {
        console.error('Error deleting pet:', error);
      }
    );
  }

  editPet(pet: Pet):void{
    this.petService.setPetToEdit(pet);
    this.router.navigate(['/pet-edit', pet]);
  }
}
