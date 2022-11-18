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
  // Variaveis
  getAllPersonsResult: Person[] = [] ;
  sub!: Subscription;
  errorMessage : any;
  sucessResponse: any;
  value= 0;
  popUp = false;
  showUpdateForm = true;
  idContact? = 0;
  arr?: any;

  // Forms
  Form: FormGroup;
  updateForm: FormGroup;


  constructor(
    private personService: PersonService,
    private fb: FormBuilder) {
      this.Form = this.fb.group({
        name: [""],
        cpf: [""],
        birthDate: [""],
        contacts: this.fb.array([])
      })

      this.updateForm = this.fb.group({
        name: [""],
        cpf: [""],
        birthDate: [""],
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

  // Atalho para retorno de contatos no Form
  get aliasesArrayControl(){

    return <FormArray> this.Form.controls['contacts']

  }


    // Atalho para retorno de contatos no updateForm
  get aliasesArrayControlUpdate(){
    return <FormArray> this.updateForm.controls['contacts']
  }

  setContacts(){
    let control = <FormArray> this.Form.controls['contacts'];
    // this.data.contacts.forEach(x => {
    //   control.push(this.fb.group({
    //     contactName:[""],
    //     email: [""],
    //     phone:[""]
    //   }))
    // })
  }


  // Adiciona nova contato
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

  // Deleta contato pelo index
  deleteContact(index: any){
    let control = <FormArray> this.Form.controls['contacts'];
    control.removeAt(index);

  }

  // Deleta contato atribuido ao update
  deleteContactFromUpdate(index: any){
    let control = <FormArray> this.updateForm.controls['contacts'];
    control.removeAt(index);
  }

  sendForm(){
   this.personService.save(this.Form.value)
   window.location.reload();

  }

  deleteRow(personID?: number){
    this.personService.deletePersonById(personID)
   window.location.reload();


  }

  updateRow(id?: number, arr?: Array<Person>){

    this.showUpdateForm = !this.showUpdateForm;
    this.idContact = id;

    let arrFiltered = arr?.find(item  => item.id == id);

    this.updateForm = this.fb.group({
      name: arrFiltered?.name,
      cpf: arrFiltered?.cpf,
      birthDate: arrFiltered?.birthDate,
      contacts: this.fb.array([])

    })
    this.setUpdate(id, arr);
  }

  setUpdate(id?: number, arr?: Array<Person>){
    let control = <FormArray> this.updateForm.controls['contacts'];
    var arrFiltered = arr?.find(item  => item.id == id);

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
   window.location.reload();


  }

  sendUpdate(){

      this.sub = this.personService.update(this.updateForm.value, this.idContact)
      .subscribe((response) => {
        alert("Enviado corretamente!")
        window.location.reload();
      },

      (error) => {

        this.errorMessage = error;
        alert(this.errorMessage)
      })

  }

}
