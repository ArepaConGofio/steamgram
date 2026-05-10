package com.arepacongofio.steamgram.service;

import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.entities.Developer;
import com.arepacongofio.steamgram.repository.DeveloperJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;
import com.arepacongofio.steamgram.service.interfaces.IDeveloperService;

@Service
public class DeveloperServiceImpl extends AbstractService<Developer,Integer> implements IDeveloperService {

    DeveloperJpaRepository developerRepository;

    public DeveloperServiceImpl(DeveloperJpaRepository developerRepository) {
        super(developerRepository);
    }

}
