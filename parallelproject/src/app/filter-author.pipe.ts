import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'filterAuthor'
})
export class FilterAuthorPipe implements PipeTransform {

  transform(book, searcha: string) {
    if (searcha === undefined) {
      return book;
    } else {
      return book.filter(books => {
        return books.authorName.toLowerCase().includes(searcha.toLowerCase());
      });
    }

  }

}
