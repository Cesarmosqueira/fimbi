import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';

function titleCaseWord(word: string) {
  if (!word) return word;
  return word[0].toUpperCase() + word.substr(1).toLowerCase();
}

@Component({
  selector: 'app-login-layout',
  templateUrl: './login-layout.component.html',
  styleUrls: ['./login-layout.component.css']
})

export class LoginLayoutComponent implements OnInit {

  public title: string = "";
  constructor(private router : Router) { }

  ngOnInit(): void {
    let page = this.router.url.split('/', 2)[1];
    this.title = titleCaseWord(page);
    console.log(this.title);
  }

}
