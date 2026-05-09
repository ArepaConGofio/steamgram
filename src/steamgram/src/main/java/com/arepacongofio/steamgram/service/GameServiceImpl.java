package com.arepacongofio.steamgram.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.domain.requests.SaveGameRequest;
import com.arepacongofio.steamgram.entities.Developer;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.entities.Review;
import com.arepacongofio.steamgram.repository.GameJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;
import com.arepacongofio.steamgram.service.interfaces.IDeveloperService;
import com.arepacongofio.steamgram.service.interfaces.IGameService;

import jakarta.annotation.PostConstruct;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.api.igdb.apicalypse.APICalypse;
import com.api.igdb.request.IGDBWrapper;
import com.api.igdb.request.TwitchAuthenticator;
import com.api.igdb.utils.TwitchToken;
import com.api.igdb.request.ProtoRequestKt;
import com.api.igdb.utils.ImageBuilderKt;
import com.api.igdb.utils.ImageSize;
import com.api.igdb.utils.ImageType;
import com.api.igdb.exceptions.RequestException;

@Service
public class GameServiceImpl extends AbstractService<Game, Integer> implements IGameService {

    GameJpaRepository gameRepository;
    IDeveloperService devsService;

    @Value("${twitch.client-id}")
    private String twitchClientId;

    @Value("${twitch.client-secret}")
    private String twitchClientSecret;

    public GameServiceImpl(GameJpaRepository gameRepository, IDeveloperService developerService) {
        super(gameRepository);
        this.devsService = developerService;
    }

    @PostConstruct
    private void loadSeedData() {
        Developer dev1 = devsService.save(new Developer("Coldblood"));
        Developer dev2 = devsService.save(new Developer("Arrowhead Games"));
        Developer dev3 = devsService.save(new Developer("Playground Games"));
        Developer dev4 = devsService.save(new Developer("Trepang Studios"));

        save(new Game(339608, "Neverway",
                "After quitting her dead-end job, Fiona starts over on a farm and becomes the immortal herald of a dead god. Make friends, fight through horrors and pay your debt in this nightmarish life sim RPG.",
                "https://images.igdb.com/igdb/image/upload/t_cover_big/co9nlq.webp", dev1,
                List.of("RPG", "Indie", "Adventure"),
                List.of("https://images.igdb.com/igdb/image/upload/t_720p/scw8ep.webp",
                        "https://images.igdb.com/igdb/image/upload/t_720p/scw8eq.webp",
                        "https://images.igdb.com/igdb/image/upload/t_720p/scw8eu.webp",
                        "https://images.igdb.com/igdb/image/upload/t_720p/scw8en.webp"),
                List.of("Windows", "Linux", "Nintendo Switch")));
        save(new Game(250616, "Helldivers 2",
                "The Galaxy’s Last Line of Offence. Enlist in the Helldivers and join the fight for freedom across a hostile galaxy in a fast, frantic, and ferocious third-person shooter.",
                "https://images.igdb.com/igdb/image/upload/t_cover_big/coabbf.webp", dev2,
                List.of("Shooter", "Tactical"),
                List.of("https://images.igdb.com/igdb/image/upload/t_720p/scn92b.webp",
                        "https://images.igdb.com/igdb/image/upload/t_720p/scn92e.webp",
                        "https://images.igdb.com/igdb/image/upload/t_720p/scmngn.webp"),
                List.of("Windows", "Playstation 5", "Xbox Series X|S")));
        save(new Game(339608, "Forza Horizon 5",
                "Your Ultimate Horizon Adventure awaits! Explore the vibrant and ever-evolving open-world landscapes of Mexico with limitless, fun driving action in hundreds of the world’s greatest cars.",
                "https://images.igdb.com/igdb/image/upload/t_cover_big/co3ofx.webp", dev3, List.of("Racing"),
                List.of("https://images.igdb.com/igdb/image/upload/t_720p/scahhj.webp"),
                List.of("Windows", "Xbox Series X|S", "Xbox One", "Playstation 5")));
        save(new Game(126212, "Trepang2",
                "Become the ultimate badass in Trepang2: A gory, action-packed FPS set in the near future. Unleash all hell on your enemies, dodge bullets and leave a trail of destruction in this hardcore and frenetic shooter.",
                "https://images.igdb.com/igdb/image/upload/t_cover_big/coar0k.webp", dev4, List.of("Shooter", "Indie"),
                List.of(),
                List.of("Windows", "Playstation 5", "Xbox Series X|S")));
    }

    @Override
    public List<Game> findIgdbGamesByTitle(Pageable pageable, String title) {
        return gameRepository.findByTitleIgnoreCaseContaining(pageable, title);
    }

    @Override
    public Game getGameByIgdbId(String igdbId) {
        Integer id = null;
        try {
            id = Integer.parseInt(igdbId);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid IGDB ID format");
        }

        // Check local database first
        Optional<Game> localGame = gameRepository.findByIdIgdb(id);
        if (localGame.isPresent()) {
            return localGame.get();
        }

        // Authenticate with Twitch
        try {
            TwitchAuthenticator tAuth = TwitchAuthenticator.INSTANCE;
            TwitchToken token = tAuth.requestTwitchToken(twitchClientId, twitchClientSecret);
            
            IGDBWrapper wrapper = IGDBWrapper.INSTANCE;
            wrapper.setCredentials(twitchClientId, token.getAccess_token());

            APICalypse apicalypse = new APICalypse()
                    .fields("id, name, summary, cover.image_id, genres.name, screenshots.image_id, platforms.name, involved_companies.developer, involved_companies.company.name")
                    .where("id = " + id);

            List<proto.Game> games = ProtoRequestKt.games(wrapper, apicalypse);
            if (games.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found in IGDB");
            }

            proto.Game igdbGame = games.get(0);
            
            String coverUrl = igdbGame.hasCover() 
                    ? ImageBuilderKt.imageBuilder(igdbGame.getCover().getImageId(), ImageSize.COVER_BIG, ImageType.WEBP) 
                    : null;
                    
            List<String> genres = igdbGame.getGenresList().stream()
                    .map(g -> g.getName())
                    .collect(Collectors.toList());
                    
            List<String> screenshots = igdbGame.getScreenshotsList().stream()
                    .map(s -> ImageBuilderKt.imageBuilder(s.getImageId(), ImageSize.SCREENSHOT_HUGE, ImageType.WEBP))
                    .collect(Collectors.toList());
                    
            List<String> platforms = igdbGame.getPlatformsList().stream()
                    .map(p -> p.getName())
                    .collect(Collectors.toList());

            Developer developer = null;
            for (proto.InvolvedCompany ic : igdbGame.getInvolvedCompaniesList()) {
                if (ic.getDeveloper()) {
                    String devName = ic.getCompany().getName();
                    developer = devsService.findAll(Pageable.unpaged()).stream()
                            .filter(d -> d.getName() != null && d.getName().equals(devName))
                            .findFirst()
                            .orElse(null);
                    if (developer == null) {
                        developer = devsService.save(new Developer(devName));
                    }
                    break;
                }
            }

            Game newGame = new Game(
                    (int) igdbGame.getId(), 
                    igdbGame.getName(), 
                    igdbGame.getSummary(), 
                    coverUrl, 
                    developer, 
                    genres, 
                    screenshots, 
                    platforms
            );

            return save(newGame);

        } catch (RequestException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error communicating with IGDB API");
        }
    }

    @Override
    public List<Post> getGamePosts(Pageable pageable, Integer id) {
        Game game = gameRepository.findById(id).orElse(null);
        if (game != null) {
            return gameRepository.getGamePosts(pageable, game);
        }
        return List.of();
    }

    @Override
    public List<Review> getGameReviews(Pageable pageable, Integer id) {
        Game game = gameRepository.findById(id).orElse(null);
        if (game != null) {
            return gameRepository.getGameReviews(pageable, game);
        }
        return List.of();
    }

    @Override
    public Game saveGameIntoProfile(SaveGameRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveGameIntoProfile'");
    }

}
