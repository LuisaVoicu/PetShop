import { Component } from '@angular/core';
import { trigger, transition, animate, style } from '@angular/animations';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
  animations: [
    trigger('slideAnimation', [
      transition('void => next', [
        style({ transform: 'translateX(100%)' }),
        animate('0.5s ease-in-out', style({ transform: 'translateX(0)' })),
      ]),
      transition('void => prev', [
        style({ transform: 'translateX(-100%)' }),
        animate('0.5s ease-in-out', style({ transform: 'translateX(0)' })),
      ]),
    ]),
  ],
})
export class HomeComponent {
  imagePaths: string[] = [
    'assets/advertising1.jpg',
    'assets/advertising2.jpg',
    'assets/advertising3.jpg',
    'assets/advertising4.jpg', // Add more image paths as needed
  ];

  currentIndex = 0;

  constructor() {}

  ngOnInit(): void {
    this.showNextSlide();
  }

  showNextSlide(): void {
    this.currentIndex = (this.currentIndex + 1) % this.imagePaths.length;
  }

  showPrevSlide(): void {
    this.currentIndex = (this.currentIndex - 1 + this.imagePaths.length) % this.imagePaths.length;
  }

  goToSlide(index: number): void {
    this.currentIndex = index;
  }
}
