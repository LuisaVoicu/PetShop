import { Component } from '@angular/core';
import { Pet } from '../../models/Pet';
import { PetService } from '../../services/pet/pet.service';
import { ActivatedRoute } from '@angular/router';
import { User } from '../../models/User';
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


  loggedUser: User = {
    id:0,
    firstName: '',
    lastName: '',
    username: '',
    email_address: '',
    imageurl: '',
    loginTime: new Date()
  };

  constructor(private petService : PetService, private route:ActivatedRoute){

  }

  ngOnInit(): void {

    this.route.params.subscribe(params => {
      this.loggedUser = params['loggedUser'];
      console.log('Usernameu vietii:', this.loggedUser.username);
      
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
