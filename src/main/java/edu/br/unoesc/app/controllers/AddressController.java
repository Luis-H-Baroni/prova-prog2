package edu.br.unoesc.app.controllers;

import edu.br.unoesc.app.dtos.AddressDTO;

import edu.br.unoesc.app.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/{personId}/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/")
    public ResponseEntity getAllPersonAddresses(@PathVariable Long personId) {
        try {
            List<AddressDTO> address = addressService.getAllPersonAddresses(personId);
            return ResponseEntity.ok(address);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/{addressId}")
    public ResponseEntity getAddressById(@PathVariable Long personId, @PathVariable Long addressId) {
        try {
            AddressDTO addressDTO = addressService.getAddressById(personId, addressId);
            return ResponseEntity.ok(addressDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/")
    public ResponseEntity createAddress(@PathVariable Long personId, @RequestBody AddressDTO newAddressDTO) {
        try {
            newAddressDTO = addressService.createAddress(personId, newAddressDTO);
            return ResponseEntity.ok(newAddressDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PatchMapping("/")
    public ResponseEntity updateAddress(@PathVariable Long personId, @RequestBody AddressDTO updatedAddressDTO) {
        try {
            updatedAddressDTO = addressService.updateAddress(personId, updatedAddressDTO);
            return ResponseEntity.ok(updatedAddressDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity deleteAddressById(@PathVariable Long personId, @PathVariable Long addressId) {
        try {
            addressService.deleteAddressById(personId, addressId);
            return ResponseEntity.ok("Address deleted!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}