import { Component } from '@angular/core';
import { BookserviceService } from './bookservice.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'lms-angular';

  constructor(private bookService:BookserviceService){}
}
