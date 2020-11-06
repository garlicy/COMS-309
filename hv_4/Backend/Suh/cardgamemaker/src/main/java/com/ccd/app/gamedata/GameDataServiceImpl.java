package com.ccd.app.gamedata;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccd.app.model.GameData;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Services implication in gamedata
 * @author jsuh_mac
 *
 */
@Service
public class GameDataServiceImpl implements GameDataService {
    @Autowired
    private GameDataRepository GameDataRepository;

    @Override
    public void save(GameData GameData) {
        GameDataRepository.save(GameData);
    }

	@Override
	public GameData findBygameName(String gameName) {
		return GameDataRepository.findBygameName(gameName);
	}


	
	@Override
	public List<GameData> getGameDataList() {
		return GameDataRepository.getGameDataList();
	}
//	
//	@Override
//	public List<GameData> getGameDataList(){
//		return GameDataRepository.findAll();
//	}

    

}
