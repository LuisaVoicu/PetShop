import { Component } from '@angular/core';
import { Pet } from '../../models/Pet';
import { PetService } from '../../services/pet/pet.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';

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
    imageurl: ''
  };

  constructor(private snackBar: MatSnackBar, private petService : PetService, private route:ActivatedRoute, private router: Router){

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


  createPet(): void {
    this.create();
  }
  

  private create(): void {
  
    this.petService.createPet(this.newPet).subscribe(
      createdPet => {
        console.log("Pet created successfully:", createdPet);
        this.openSnackBar('Pet created successfully', 'Close');
        this.router.navigateByUrl('/pet');
      },
      error => {
        console.error('Error creating pet:', error);
        this.openSnackBar('Error creating pet. Please check your input and try again.', 'Close');
      }
    );
  }
  
  

openSnackBar(message: string, action: string): void {
  const config = new MatSnackBarConfig();
  config.duration = 3000;
  config.panelClass = ['snackbar-overlay'];
  this.snackBar.open(message, action, config);
}

}
