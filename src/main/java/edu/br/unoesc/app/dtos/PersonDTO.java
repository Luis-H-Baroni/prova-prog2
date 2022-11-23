package edu.br.unoesc.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PersonDTO {

    private Long id;

    private String name;

    private String surname;

    private String CPF;

    private String RG;

}
