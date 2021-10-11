import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {UserModel} from "./user-model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UsersApiService {

  private _apiUrl = 'http://localhost:8080/api/users'

  constructor(private _http: HttpClient) { }

  create(user: UserModel): Observable<any> {
    return this._http.post(this._apiUrl, user, {});
  }

  findAll(): Observable<any> {
    return this._http.get(this._apiUrl, this._getOptions());
  }

  private _getOptions(): any {
    return {
      headers: new HttpHeaders({})
    };
  }
}
