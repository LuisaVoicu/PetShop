import { Component } from '@angular/core';
import { Pet } from '../../models/Pet';
import { PetService } from '../../services/pet/pet.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pet-edit',
  templateUrl: './pet-edit.component.html',
  styleUrl: './pet-edit.component.css'
})
export class PetEditComponent {
  pet: Pet = {
    id:0,
    name: '',
    age: 0,
    weight: 0,
    imageUrl: ''
  };


  editedPet: Pet ={
    id: 0,
    name: '',
    age: 0,
    weight: 0,
    imageUrl: ''
  }
  constructor(private petService : PetService, private route:ActivatedRoute, private router: Router){}

  ngOnInit(): void {
    // Access the selected pet from the shared service
    this.editedPet = this.petService.selectedPet ;

  }


  edit(pet: Pet): void {
    this.petService.editPet(this.editedPet).subscribe(
      editedPet => {
        console.log('Pet editing successfully:', editedPet)

      },
      error => {
        console.error('Error editing pet:', error);
      }
    );

  }

  editPet(pet: Pet): void {
    // Call createPet method when the button is clicked
    this.edit(pet);
    this.router.navigateByUrl('/pet-delete');

  }

}
