package edu.br.unoesc.app.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "person_addresses")
public class Address extends AbstractEntity {

    private String zipCode;

    private String publicPlace;

    private String number;

    private String complement;

    @ManyToOne
    @JoinColumn(name = "personId", nullable = false)
    private Person person;

}