import { Observable, Subscription } from 'rxjs';
import { PersonService } from './../services/person.service';
import { Component, OnInit } from '@angular/core';
import { Person } from 'src/Person';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {
  getAllPersonsResult: Person[] = [] ;
  sub!: Subscription;
  errorMessage = '';
  value= 0;

  Form: FormGroup;

  data: Person = {
    nomePessoa: "Alex",
    cpfPessoa: "60998142050",
    dataNascimentoPessoa: new Date,
    contacts:[
      {
        contactName:"Contato do Alex",
        email:"contatoDoAlex@gmail.com",
        phone:"99999999999"
      }
    ]
  }

  constructor(
    private personService: PersonService,
    private fb: FormBuilder) {
      this.Form = this.fb.group({
        nomePessoa: ["ALEX", [Validators.required]],
        cpfPessoa: ["60998142050", [Validators.required]],
        dataNascimentoPessoa: ["", [Validators.required]],
        contacts: this.fb.array([])
      })

      this.setContacts()
    }


  ngOnInit(): void {
    this.sub = this.personService.getAll().subscribe({
      next: person => {
        console.log(person)
        this.getAllPersonsResult = person
      },
      error: err => this.errorMessage = err
    })

  }

  get aliasesArrayControl(){
    return <FormArray> this.Form.controls['contacts']
  }

  setContacts(){
    let control = <FormArray> this.Form.controls['contacts'];
    this.data.contacts.forEach(x => {
      control.push(this.fb.group({
        contactName:[""],
        email: [""],
        phone:[""]
      }))
    })
  }

  addNewContact(){
    let control = <FormArray> this.Form.controls['contacts'];
    control.push(
      this.fb.group({
        contactName: [""],
        email: [""],
        phone: [""]
      })
    )
  }

  deleteContact(index: any){
    let control = <FormArray> this.Form.controls['contacts'];
    control.removeAt(index);

  }

  enviarFormulario(){

   this.personService.save(this.Form.value)

  }




}
