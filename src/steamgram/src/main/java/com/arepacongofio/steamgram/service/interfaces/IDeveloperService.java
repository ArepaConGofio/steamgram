package com.arepacongofio.steamgram.service.interfaces;

import com.arepacongofio.steamgram.entities.Developer;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.service.interfaces.generic.IGenericService;

public interface IDeveloperService extends IGenericService<Developer, Integer> {
    
    Game publishGame(Game game);
}
