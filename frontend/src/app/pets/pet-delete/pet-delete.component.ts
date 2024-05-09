import { Component } from '@angular/core';
import { Pet } from '../../models/Pet';
import { PetService } from '../../services/pet/pet.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';

@Component({
  selector: 'app-pet-delete',
  templateUrl: './pet-delete.component.html',
  styleUrls: ['./pet-delete.component.css']
})
export class PetDeleteComponent {
  pets: Pet[] = [];

  constructor(private snackBar: MatSnackBar, private petService: PetService, private route: ActivatedRoute, private router: Router) {}

  ngOnInit(): void {
    this.fetchPets();
  }

  fetchPets(): void {
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
        this.openSnackBar('Pet deleted successfully', 'Close');
        this.fetchPets();
      },
      error => {
        console.error('Error deleting pet:', error);
      }
    );
  }

  editPet(pet: Pet): void {
    this.petService.setPetToEdit(pet);
    this.router.navigate(['/pet-edit', pet]);
  }

  openSnackBar(message: string, action: string): void {
    const config = new MatSnackBarConfig();
    config.duration = 3000;
    config.verticalPosition = 'top';
    this.snackBar.open(message, action, config);
  }
}
