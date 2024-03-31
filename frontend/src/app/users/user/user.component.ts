import { Component } from '@angular/core';
import { User } from '../../models/User';
import { UserService } from '../../services/user/user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrl: './user.component.css'
})
export class UserComponent {
  users: User[] = [];
  newUser: User = {
    id:0,
    firstName: '',
    lastName: '',
    username: '',
    emailAddress: '',
    imageUrl: ''
  };

  constructor(private userService : UserService, private route:ActivatedRoute){

  }

  ngOnInit(): void {
    this.userService.getAllUsers().subscribe(
      users => {
         this.users = users;
      },
      error => {
        console.error('Error fetching users:', error);
      }
    );
  }
}
