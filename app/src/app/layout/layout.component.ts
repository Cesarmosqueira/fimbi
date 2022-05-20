import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

function titleCaseWord(word: string) {
  if (!word) return word;
  return word[0].toUpperCase() + word.substr(1).toLowerCase();
}

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {

  public title: string = "";
  constructor(private router : Router) { }

  ngOnInit(): void {
    let page = this.router.url.split('/', 2)[1];
    if (page) {
      this.title = titleCaseWord(page);
    } else {
      this.title = "Home";
    }
  } 
}
