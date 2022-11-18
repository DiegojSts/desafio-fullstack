import { elementAt, Observable, Subscription } from 'rxjs';
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
  popUp = false;
  showUpdateForm = true;
  idContact? = 0;
  arr?: any;

  Form: FormGroup;
  updateForm: FormGroup;

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
        nomePessoa: [""],
        cpfPessoa: [""],
        dataNascimentoPessoa: [""],
        contacts: this.fb.array([])
      })

      this.updateForm = this.fb.group({
        nomePessoa: [""],
        cpfPessoa: [""],
        dataNascimentoPessoa: [""],
        contacts: this.fb.array([])
      })

      this.setContacts()


    }


  ngOnInit(): void {
    this.sub = this.personService.getAll().subscribe({
      next: person => {
        this.getAllPersonsResult = person
      },
      error: err => this.errorMessage = err
    })



  }

  get aliasesArrayControl(){

    return <FormArray> this.Form.controls['contacts']

  }

  get aliasesArrayControlUpdate(){
    return <FormArray> this.updateForm.controls['contacts']
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

  deleteContactFromUpdate(index: any){
    let control = <FormArray> this.updateForm.controls['contacts'];
    control.removeAt(index);
  }

  sendForm(){

   this.personService.save(this.Form.value)
   window.location.reload


  }

  deleteRow(personID?: number){
    this.personService.deletePersonById(personID)
    window.location.reload

  }

  updateRow(id?: number, arr?: Array<Person>){

    this.showUpdateForm = !this.showUpdateForm;
    this.idContact = id;

    let arrFiltered = arr?.find(item  => item.idPessoa == id);

    this.updateForm = this.fb.group({
      nomePessoa: arrFiltered?.nomePessoa,
      cpfPessoa: arrFiltered?.cpfPessoa,
      dataNascimentoPessoa: arrFiltered?.dataNascimentoPessoa,
      contacts: this.fb.array([])

    })
    this.setUpdate(id, arr);
  }

  setUpdate(id?: number, arr?: Array<Person>){
    let control = <FormArray> this.updateForm.controls['contacts'];
    var arrFiltered = arr?.find(item  => item.idPessoa == id);

    arrFiltered?.contacts.forEach(contact => {

      control.push(
        this.fb.group({
          contactId: contact.contactId,
          contactName: contact.contactName,
          email: contact.email,
          phone: contact.phone
        })
      )
    })

  }

  sendUpdate(){
    window.location.reload
    this.personService.update(this.updateForm.value, this.idContact)
  }
}
