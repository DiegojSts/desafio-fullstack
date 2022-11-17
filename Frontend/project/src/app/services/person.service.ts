import { Person } from './../../Person';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError, Subscription } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PersonService {
  private readonly BASE_URL = "http://localhost:8080/api/pessoa";

  constructor(private http: HttpClient) { }

  getAll(): Observable<Person[]> {
    return this.http.get<Person[]>(`${this.BASE_URL}`)
    .pipe(
      tap(data => console.log(JSON.stringify(data))),
      catchError(this.handleError)
    )

  }

  getPersonById(personID: number): Observable<Person> {
    return this.http.get<Person>(`${this.BASE_URL}/${personID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    )

  }

  deletePersonById(personID: number): Observable<Person> {
    return this.http.delete<Person>(`${this.BASE_URL}/${personID}`)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    )

  }

  save(person: Person): Subscription {

    return this.http.post<Person>(`${this.BASE_URL}/save`, person)
    .subscribe((res) => {
      console.log(res)
    })


  }



  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error);
    return  throwError(`Ocorreu um erro! Codigo do erro: ${error.status}`);
  }
}
