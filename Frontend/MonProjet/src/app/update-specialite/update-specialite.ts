import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Specialite } from '../model/specialite.model';

@Component({
  selector: 'app-update-specialite',
  imports: [CommonModule, FormsModule],
  templateUrl: './update-specialite.html',
})
export class UpdateSpecialiteComponent implements OnInit {

  @Input()
  specialite!: Specialite;

  @Input()
  ajout!: boolean;

  @Output()
  specialiteUpdated = new EventEmitter<Specialite>();

  ngOnInit(): void {
    console.log('ngOnInit du composant UpdateSpecialite :', this.specialite);
  }

  saveSpecialite() {
    this.specialiteUpdated.emit(this.specialite);
  }
}
