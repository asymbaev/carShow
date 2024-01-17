package com.binary.carShow.service;

import com.binary.carShow.entity.Owner;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OwnerService {
    List<Owner> getOwners();

    Owner getOwnerById(Long id);

    Owner addOwner(Owner owner);
}
