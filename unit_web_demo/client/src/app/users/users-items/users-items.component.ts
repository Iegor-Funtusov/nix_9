import { Component, OnInit } from '@angular/core';
import {UserModel} from "../user-model";
import {UsersApiService} from "../users-api.service";

@Component({
  selector: 'app-users-items',
  templateUrl: './users-items.component.html',
  styleUrls: ['./users-items.component.scss']
})
export class UsersItemsComponent implements OnInit {

  users: UserModel[] | undefined;

  constructor(private _apiService: UsersApiService) { }

  ngOnInit(): void {
    this._apiService.findAll().subscribe(res => {
      this.users = res;
    })
  }
}
