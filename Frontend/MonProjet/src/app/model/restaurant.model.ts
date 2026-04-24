import { Specialite } from "./specialite.model";

export class Restaurant {
    idRestaurant?: number;
    nomRestaurant?: string;
    noteRestaurant?: number;
    dateOuverture?: Date;
    idSpec?: number;   // ← remplace l'objet specialite pour POST/PUT
    nomSpec?: string;  // ← pour affichage
    specialite?: Specialite; // ← gardé pour l'affichage dans la liste
}