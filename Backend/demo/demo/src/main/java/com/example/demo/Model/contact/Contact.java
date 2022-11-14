package com.example.demo.Model.contact;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "contact")
public class Contact {
    @Id
    @SequenceGenerator(name = "contact_sequence", sequenceName = "contact_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contact_sequence")
    private int contactId;
    @NotBlank(message = "Nome obrigatório")
    private String contactName;
    @NotBlank(message = "Email obrigatório")
    private String email;
    @NotBlank(message = "Telefone obrigatório")
    private String phone;

    public Contact() {
    }

    public Contact(String contactName, String email, String phone) {
        this.contactName = contactName;
        this.email = email;
        this.phone = phone;
    }

    public int getContactId() {
        return contactId;
    }

    public void setContactId(int contactId) {
        this.contactId = contactId;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "contactId=" + contactId +
                ", contactName='" + contactName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
