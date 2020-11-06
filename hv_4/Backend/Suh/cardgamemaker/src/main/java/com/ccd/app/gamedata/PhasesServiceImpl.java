package com.ccd.app.gamedata;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccd.app.model.GameData;
import com.ccd.app.model.Phases;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Services implication in gamedata
 * @author jsuh_mac
 *
 */
@Service
public class PhasesServiceImpl implements PhasesService {
    @Autowired
    private PhasesRepository PhasesRepository;

    @Override
    public void save(Phases Phases) {
        PhasesRepository.save(Phases);
     
    }
}
