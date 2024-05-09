// 

import { Component } from '@angular/core';
import { Pet } from '../../models/Pet';
import { PetService } from '../../services/pet/pet.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';

@Component({
  selector: 'app-pet-edit',
  templateUrl: './pet-edit.component.html',
  styleUrls: ['./pet-edit.component.css']
})
export class PetEditComponent {
  pet: Pet = {
    id: 0,
    name: '',
    age: 0,
    weight: 0,
    imageurl: ''
  };

  editedPet: Pet = {
    id: 0,
    name: '',
    age: 0,
    weight: 0,
    imageurl: ''
  };

  constructor(private snackBar: MatSnackBar, private petService: PetService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    // Access the selected pet from the shared service
    this.editedPet = this.petService.selectedPet;
  }

  edit(): void {
    this.petService.editPet(this.editedPet).subscribe(
      editedPet => {
        console.log('Pet editing successfully:', editedPet);
        this.openSnackBar('Pet edited successfully', 'Close');
        this.router.navigateByUrl('/pet');
      },
      error => {
        console.error('Error editing pet:', error);
        this.openSnackBar('Error editing pet. Please try again later.', 'Close');
      }
    );
  }

  openSnackBar(message: string, action: string): void {
    const config = new MatSnackBarConfig();
    config.duration = 3000;
    config.verticalPosition = 'top';
    this.snackBar.open(message, action, config);
  }
}