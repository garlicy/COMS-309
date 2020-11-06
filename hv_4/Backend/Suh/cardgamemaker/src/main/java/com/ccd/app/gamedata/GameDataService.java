package com.ccd.app.gamedata;


import java.util.List;

import org.springframework.stereotype.Service;

import com.ccd.app.model.GameData;

/**
 * This indicates what service that GameData has 
 * @author jsuh_mac
 *
 */

@Service
public interface GameDataService {
    void save(GameData GameData);
    
    GameData findBygameName(String gameName);
    
    List<GameData> getGameDataList();


}