package com.ccd.app.gamedata;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ccd.app.model.GameData;
import com.ccd.app.model.Phases;

/**
 * This indicates what service that GameData has 
 * @author jsuh_mac
 *
 */

@Service
public interface PhasesService {
    void save(Phases Phases);
}