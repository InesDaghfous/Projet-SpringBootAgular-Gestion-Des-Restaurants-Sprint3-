import { Specialite } from './specialite.model';
export class SpecialiteWrapper{
    _embedded!: { specialites: Specialite[]};
}