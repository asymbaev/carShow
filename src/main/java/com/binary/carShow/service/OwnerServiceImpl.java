package com.binary.carShow.service;

import com.binary.carShow.entity.Car;
import com.binary.carShow.entity.Owner;
import com.binary.carShow.repository.OwnerRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerServiceImpl implements OwnerService{

    private final OwnerRepository ownerRepository;

    public OwnerServiceImpl(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @Override
    public List<Owner> getOwners() {
        return ownerRepository.findAll();
    }

    @Override
    public Owner getOwnerById(Long id) {

        Optional<Owner> optionalOwner = ownerRepository.findById(id);
         if (optionalOwner.isPresent()) {
             return optionalOwner.get();
         } else {
             throw new EntityNotFoundException("Owner with id " + id + " not found");
         }

//        Optional<Car> optionalCar = carRepository.findById(id);
//
//        if (optionalCar.isPresent()) {
//            return optionalCar.get();
//        } else {
//            throw new EntityNotFoundException("Car with id " + id + " not found");
//        }
//    }


    }

    @Override
    public Owner addOwner(Owner owner) {
        return ownerRepository.save(owner);
    }
}
