package edu.br.unoesc.app.dtos;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    private Long id;

    private String zipCode;

    private String publicPlace;

    private String number;

    private String complement;

}