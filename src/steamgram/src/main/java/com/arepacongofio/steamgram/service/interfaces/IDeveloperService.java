package com.arepacongofio.steamgram.service.interfaces;

import com.arepacongofio.steamgram.models.Developer;
import com.arepacongofio.steamgram.models.Game;

public interface IDeveloperService extends IService<Developer, Integer> {
    
    Game publishGame(Game game);
}
