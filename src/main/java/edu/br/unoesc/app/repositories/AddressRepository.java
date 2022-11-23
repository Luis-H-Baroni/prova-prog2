package edu.br.unoesc.app.repositories;

import edu.br.unoesc.app.entities.Address;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface AddressRepository extends CrudRepository<Address, String> {
    @Query("from Address address where  address.person.id = :personId and address.id = :id")
    public Address findByAddressId(@Param("personId") Long personId, @Param("id") Long id);
}