export interface Person {
  idPessoa?: number;
  nomePessoa?: string,
  cpfPessoa?: string,
  dataNascimentoPessoa?: Date;
  contacts: [
    {
    contactId?: number,
    contactName: string,
    email: string,
    phone: string
  }
]

}

