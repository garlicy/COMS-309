package com.ccd.app.gamedata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ccd.app.model.GameData;
import java.util.List;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

/**
 * Game Data Repository extends CrudRepository
 * @author jsuh_mac
 *
 */

@Repository
public interface GameDataRepository extends JpaRepository<GameData, Integer> {
	GameData findBygameName(String gameName);

	List<GameData> getGameDataList();

}
 