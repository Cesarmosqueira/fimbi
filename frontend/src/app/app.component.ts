import { Component } from '@angular/core';
import {Router} from '@angular/router';

function titleCaseWord(word: string) {
  if (!word) return word;
  return word[0].toUpperCase() + word.substr(1).toLowerCase();
}

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})

export class AppComponent {
  title = 'frontend';
  navtitle = '';

  constructor(private router : Router) { }

  goToPage(pageName:string){
    this.router.navigate([`${pageName}`]);
  }

  ngOnInit(): void {
    let page = this.router.url.split('/', 2)[1];
    if (page) {
      this.navtitle = titleCaseWord(page);
    } else {
      this.navtitle = "Home";
    }
  }
}
