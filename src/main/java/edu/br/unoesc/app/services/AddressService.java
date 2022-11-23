package edu.br.unoesc.app.services;

import edu.br.unoesc.app.dtos.AddressDTO;
import edu.br.unoesc.app.dtos.PersonDTO;
import edu.br.unoesc.app.entities.Address;
import edu.br.unoesc.app.entities.Person;
import edu.br.unoesc.app.repositories.AddressRepository;
import edu.br.unoesc.app.repositories.PersonRepository;

import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    AddressRepository addressRepository;

    public List<AddressDTO> getAllPersonAddresses(Long personId) {
        Person person = personRepository.findById(personId);
        if (person == null)
            throw new RuntimeException("Person not found");
        List<Address> addresses = person.getAddresses();
        List<AddressDTO> addressListDTO = new ArrayList<AddressDTO>();
        addresses.forEach(address -> {
            AddressDTO addressDTO = new AddressDTO(address.getId(),
                    address.getZipCode(),
                    address.getPublicPlace(),
                    address.getNumber(),
                    address.getComplement());
            addressListDTO.add(addressDTO);
        });
        return addressListDTO;
    }

    public AddressDTO getAddressById(Long personId, Long addressId) {
        Address address = addressRepository.findByAddressId(personId, addressId);
        if (address == null)
            throw new RuntimeException("Address not found");

        AddressDTO addressDTO = new AddressDTO(address.getId(),
                address.getZipCode(),
                address.getPublicPlace(),
                address.getNumber(),
                address.getComplement());

        return addressDTO;
    }

    public AddressDTO createAddress(Long personId, AddressDTO newAddressDTO) {
        Address address = new Address();
        address.setZipCode(newAddressDTO.getZipCode());
        address.setPublicPlace(newAddressDTO.getPublicPlace());
        address.setNumber(newAddressDTO.getNumber());
        address.setComplement(newAddressDTO.getComplement());

        Person person = personRepository.findById(personId);
        address.setPerson(person);
        address = addressRepository.save(address);
        newAddressDTO.setId(address.getId());
        return newAddressDTO;
    }

    public AddressDTO updateAddress(Long personId, AddressDTO updatedAddressDTO) {

        try {
            Address address = addressRepository.findByAddressId(personId, updatedAddressDTO.getId());
            address.setZipCode(updatedAddressDTO.getZipCode());
            address.setPublicPlace(updatedAddressDTO.getPublicPlace());
            address.setNumber(updatedAddressDTO.getNumber());
            address.setComplement(updatedAddressDTO.getComplement());
            address.setUpdatedAt(LocalDateTime.now());

            addressRepository.save(address);
            return updatedAddressDTO;
        } catch (Exception e) {
            throw new RuntimeException("Address cannot be changed", e);
        }
    }

    public void deleteAddressById(Long personId, Long addressId) {
        Address address = addressRepository.findByAddressId(personId, addressId);

        if (address == null) {
            throw new RuntimeException("Address not found");
        }
        addressRepository.delete(address);
    }
}