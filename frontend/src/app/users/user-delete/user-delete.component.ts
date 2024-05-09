import { Component } from '@angular/core';
import { User } from '../../models/User';
import { UserService } from '../../services/user/user.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { MatSnackBar, MatSnackBarConfig } from '@angular/material/snack-bar';

@Component({
  selector: 'app-user-delete',
  templateUrl: './user-delete.component.html',
  styleUrl: './user-delete.component.css'
})
export class UserDeleteComponent {
  users: User[] = [];

  constructor(private snackBar: MatSnackBar, private userService : UserService, private route:ActivatedRoute, private router:Router){

  }

  ngOnInit(): void {
    this.fetchUsers();
  }

  fetchUsers(): void{
    this.userService.getAllUsers().subscribe(
      users => {
        this.users = users;
      },
      error => {
        console.error('Error fetching users:', error);
      }
    );
  }

  deleteUser(user: User): void {

    this.userService.deleteUser(user).subscribe(
      () => {
        console.log('User deleted successfully');
        this.openSnackBar('User deleted successfully', 'Close');
        this.fetchUsers();
      },
      error => {
        console.error('Error deleting user:', error);
      }
    );
  }

  editUser(user: User):void{
    this.userService.setUserToEdit(user);
    this.router.navigate(['/user-edit', user]);
  }

  openSnackBar(message: string, action: string): void {
    const config = new MatSnackBarConfig();
    config.duration = 3000;
    config.verticalPosition = 'top';
    this.snackBar.open(message, action, config);
  }
}
