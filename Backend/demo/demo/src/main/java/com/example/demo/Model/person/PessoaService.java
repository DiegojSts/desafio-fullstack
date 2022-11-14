//package com.example.demo.Model.person;
//
//import com.example.demo.Errors.Errors;
//import com.example.demo.validadores.Utils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.time.LocalDate;
//import java.util.List;
//import java.util.Objects;
//import java.util.Optional;
//
//@Service
//public class PessoaService {
//private final PessoaRepository _pessoaRepository;
//private boolean exists = false;
//
//
//    @Autowired
//    public PessoaService(PessoaRepository pessoaRepository){
//        this._pessoaRepository = pessoaRepository;
//    }
//    public List<Pessoa> getPessoas() {
////
//        return _pessoaRepository.findAll();
//    }
//
//    public Pessoa adicionarPessoa(Pessoa pessoa) {
//        boolean isValid;
//
//        isValid = Utils
//                .executeCheck(pessoa.getCpfPessoa(),
//                        pessoa.getDataNascimentoPessoa().toString(), "yyyy-MM-dd");
//        if(!isValid){
//            throw new IllegalStateException("");
//        }
//
//        return _pessoaRepository.save(pessoa);
//
//    }
//
//    public Optional<Pessoa> getPessoaById(int pessoaId){
//        return _pessoaRepository.findById(pessoaId);
//    }
//
//    public void deletePessoaById(Integer pessoaId) {
//        this.exists = _pessoaRepository.existsById(pessoaId);
//
//        if(!this.exists){
//            Errors.findByIdError(pessoaId);
//        }
//
//        this._pessoaRepository.deleteById(pessoaId);
//
//    }
//
//    @Transactional
//    public void updatePerson(Integer pessoaId,
//                             String nomePessoa,
//                             String cpfPessoa,
//                             String dataNascimentoPessoa) {
//        this.exists = _pessoaRepository.existsById(pessoaId);
//
//
//        if(!this.exists){
//            Errors.findByIdError(pessoaId);
//        }
//
//        Pessoa pessoa = _pessoaRepository.findById(pessoaId)
//                .orElseThrow(() -> new IllegalStateException("Pessoa de id" + pessoaId + "não existe!"));
//
//        if (nomePessoa != null &&
//                nomePessoa.length() > 0 &&
//                !Objects.equals(pessoa.getNomePessoa(), nomePessoa)
//        ) {
//            pessoa.setNomePessoa(nomePessoa);
//        }
//
//        if(Utils.isCPF(cpfPessoa) &&
//                !Objects.equals(pessoa.getCpfPessoa(), cpfPessoa)){
//            pessoa.setCpfPessoa(cpfPessoa);
//        }
//
//        if (!Utils.isDateFuture(dataNascimentoPessoa, "yyyy-MM-dd") &&
//            !Objects.equals(pessoa.getDataNascimentoPessoa(), dataNascimentoPessoa)
//        ){
//            pessoa.setDataNascimentoPessoa(LocalDate.parse(dataNascimentoPessoa.toString()));
//        }
//    }
//}
