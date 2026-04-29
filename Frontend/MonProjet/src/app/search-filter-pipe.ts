import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'searchFilter',
  standalone: true
})
export class SearchFilterPipe implements PipeTransform {

  transform(list: any[], filterText: string): any {
    if (!filterText) return list;  // champ vide → afficher tout
    return list ? list.filter(item =>
      item.nomRestaurant && item.nomRestaurant.toLowerCase().includes(filterText.toLowerCase())
    ) : [];
  }

}