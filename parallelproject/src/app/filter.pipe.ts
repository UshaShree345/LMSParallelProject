import { PipeTransform, Pipe } from '@angular/core';

@Pipe({
  name: 'filter'
})

export class FilterPipe implements PipeTransform {

  transform(book, search: string) {
    if (search === undefined) {
      return book;
    } else {
      return book.filter(books => {
        return books.bookName.toLowerCase().includes(search.toLowerCase()) ||
          books.authorName.toLowerCase().includes(search.toLowerCase()) ||
          books.bookCategory.toLowerCase().includes(search.toLowerCase());
      });
    }
  }
}
