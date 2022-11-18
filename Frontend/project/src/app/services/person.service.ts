import { Person } from './../../Person';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, tap, throwError, Subscription } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class PersonService {
  private readonly BASE_URL = 'http://localhost:8080/api/person';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Person[]> {
    return this.http.get<Person[]>(`${this.BASE_URL}`).pipe(
      tap((data) => JSON.stringify(data)),
      catchError(this.handleError)
    );
  }

  getPersonById(personID: number): Observable<Person> {
    return this.http
      .get<Person>(`${this.BASE_URL}/${personID}`)
      .pipe(tap(console.log), catchError(this.handleError));
  }

  deletePersonById(personID?: number): Subscription {
    return this.http
      .delete<Person>(`${this.BASE_URL}/delete/${personID}`)
      .subscribe((res) => {
        console.log(res);
      });
  }

  // save(person: Person): Subscription {

  //   return this.http.post<Person>(`${this.BASE_URL}/save`, person)
  //   .subscribe((res) => {
  //     console.log(res)
  //   })
  // }

  save(person: Person): Observable<Person> {
    return this.http.post<Person>(`${this.BASE_URL}/save`, person).pipe(
      tap((data) => JSON.stringify(data)),
      catchError(this.handleError)
    );
  }

  update(person: Person, id?: number): Observable<Person> {
    return this.http.put<Person>(`${this.BASE_URL}/${id}`, person)
      .pipe(
        catchError((err) => {
          console.log("CatcheRROR", err)
          return this.handleError(err)
        }))
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.log(error)
   return throwError(`Ocorreu um erro STATUS: ${error.status}, verifique se campos estão corretamente atribuidos!. Campos não podem ser vazios ou nulos!`)

  }
}
