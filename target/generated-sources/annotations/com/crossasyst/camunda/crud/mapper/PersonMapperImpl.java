package com.crossasyst.camunda.crud.mapper;

import com.crossasyst.camunda.crud.entity.AddressEntity;
import com.crossasyst.camunda.crud.entity.PersonEntity;
import com.crossasyst.camunda.crud.model.Address;
import com.crossasyst.camunda.crud.model.Person;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-24T11:25:48+0530",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class PersonMapperImpl implements PersonMapper {

    @Override
    public PersonEntity modelToEntity(Person person) {
        if ( person == null ) {
            return null;
        }

        PersonEntity personEntity = new PersonEntity();

        personEntity.setAddressEntity( addressListToAddressEntityList( person.getAddress() ) );
        personEntity.setFirstName( person.getFirstName() );
        personEntity.setLastName( person.getLastName() );

        return personEntity;
    }

    @Override
    public Person entityToModel(PersonEntity personEntity) {
        if ( personEntity == null ) {
            return null;
        }

        Person person = new Person();

        person.setAddress( addressEntityListToAddressList( personEntity.getAddressEntity() ) );
        person.setFirstName( personEntity.getFirstName() );
        person.setLastName( personEntity.getLastName() );

        return person;
    }

    protected AddressEntity addressToAddressEntity(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressEntity addressEntity = new AddressEntity();

        addressEntity.setAddressLineOne( address.getAddressLineOne() );
        addressEntity.setAddressLineTwo( address.getAddressLineTwo() );
        addressEntity.setCity( address.getCity() );
        addressEntity.setState( address.getState() );
        addressEntity.setZipCode( address.getZipCode() );

        return addressEntity;
    }

    protected List<AddressEntity> addressListToAddressEntityList(List<Address> list) {
        if ( list == null ) {
            return null;
        }

        List<AddressEntity> list1 = new ArrayList<AddressEntity>( list.size() );
        for ( Address address : list ) {
            list1.add( addressToAddressEntity( address ) );
        }

        return list1;
    }

    protected Address addressEntityToAddress(AddressEntity addressEntity) {
        if ( addressEntity == null ) {
            return null;
        }

        Address address = new Address();

        address.setAddressLineOne( addressEntity.getAddressLineOne() );
        address.setAddressLineTwo( addressEntity.getAddressLineTwo() );
        address.setCity( addressEntity.getCity() );
        address.setState( addressEntity.getState() );
        address.setZipCode( addressEntity.getZipCode() );

        return address;
    }

    protected List<Address> addressEntityListToAddressList(List<AddressEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Address> list1 = new ArrayList<Address>( list.size() );
        for ( AddressEntity addressEntity : list ) {
            list1.add( addressEntityToAddress( addressEntity ) );
        }

        return list1;
    }
}
