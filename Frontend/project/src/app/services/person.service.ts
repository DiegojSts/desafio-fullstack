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
      tap(data => JSON.stringify(data)),
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

  deletePersonById(personID?: number): Subscription {
    return this.http.delete<Person>(`${this.BASE_URL}/delete/${personID}`)
    .subscribe((res) => {
      console.log(res)
    })

  }

  save(person: Person): Observable<Person> {

    return this.http.post<Person>(`${this.BASE_URL}/save`, person)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    )


  }

  update(person: Person, id?: number): Observable<Person> {
    return this.http.put<Person>(`${this.BASE_URL}/${id}`, person)
    .pipe(
      tap(console.log),
      catchError(this.handleError)
    )
  }


  private handleError(error: HttpErrorResponse): Observable<never> {
    return  throwError(`Ocorreu um erro! Codigo do erro: ${error.status}`);
  }
}
