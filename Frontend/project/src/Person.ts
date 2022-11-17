export interface Person {
  idPessoa?: number;
  nomePessoa: string,
  cpfPessoa: string,
  dataNascimentoPessoa: Date;
  contacts: [
    {
    contactName: string,
    email: string,
    phone: string
  }
]



}
