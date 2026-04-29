import { Specialite } from "./specialite.model";
import { Image } from "./image.model";

export class Restaurant {
    idRestaurant?: number;
    nomRestaurant?: string;
    noteRestaurant?: number;
    dateOuverture?: Date;
    idSpec?: number;   
    nomSpec?: string;  
    specialite?: Specialite;
    images?: Image[];
    imageStr?: string;
}