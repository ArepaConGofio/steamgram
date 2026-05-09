package com.arepacongofio.steamgram.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.arepacongofio.steamgram.domain.requests.SaveGameRequest;
import com.arepacongofio.steamgram.entities.Developer;
import com.arepacongofio.steamgram.entities.Game;
import com.arepacongofio.steamgram.entities.Post;
import com.arepacongofio.steamgram.entities.Review;
import com.arepacongofio.steamgram.repository.GameJpaRepository;
import com.arepacongofio.steamgram.repository.PostJpaRepository;
import com.arepacongofio.steamgram.repository.ReviewJpaRepository;
import com.arepacongofio.steamgram.service.abst.AbstractService;
import com.arepacongofio.steamgram.service.interfaces.IDeveloperService;
import com.arepacongofio.steamgram.service.interfaces.IGameService;

import jakarta.annotation.PostConstruct;

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

    private final Logger logger = LoggerFactory.getLogger(GameServiceImpl.class);

    private GameJpaRepository gameRepository;
    private PostJpaRepository postRepository;
    private ReviewJpaRepository reviewRepository;
    private IDeveloperService devsService;
    private TwitchToken twitchToken;

    @Value("${twitch.client-id}")
    private String twitchClientId;

    @Value("${twitch.client-secret}")
    private String twitchClientSecret;

    @Autowired
    public GameServiceImpl(GameJpaRepository gameRepository, IDeveloperService developerService,
            ReviewJpaRepository reviewJpaRepository, PostJpaRepository postJpaRepository) {
        super(gameRepository);
        this.gameRepository = gameRepository;
        this.devsService = developerService;
        this.postRepository = postJpaRepository;
        this.reviewRepository = reviewJpaRepository;
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
        save(new Game(141503, "Forza Horizon 5",
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

    @PostConstruct
    private void fetchTwitchAuthentication() {
        TwitchAuthenticator tAuth = TwitchAuthenticator.INSTANCE;
        twitchToken = tAuth.requestTwitchToken(twitchClientId, twitchClientSecret);
    }

    private IGDBWrapper getIgdbWrapper() {
        IGDBWrapper wrapper = IGDBWrapper.INSTANCE;
        wrapper.setCredentials(twitchClientId, twitchToken.getAccess_token());
        return wrapper;
    }

    private Developer extractDeveloperFromIgdbGame(proto.Game igdbGame, boolean save) {
        Developer developer = null;
        for (proto.InvolvedCompany ic : igdbGame.getInvolvedCompaniesList()) {
            if (ic.getDeveloper()) {
                String devName = ic.getCompany().getName();
                developer = devsService.findAll(Pageable.unpaged()).stream()
                        .filter(d -> d.getName() != null && d.getName().equals(devName))
                        .findFirst()
                        .orElse(null);
                if (developer == null && save) {
                    developer = devsService.save(new Developer(devName));
                } else {
                    developer = new Developer(devName);
                }
                break;
            }
        }
        return developer;
    }

    private String extractCoverUrlFromIgdbGame(proto.Game igdbGame) {
        return igdbGame.hasCover()
                ? ImageBuilderKt.imageBuilder(igdbGame.getCover().getImageId(), ImageSize.COVER_BIG, ImageType.WEBP)
                : null;
    }

    public List<Game> findIgdbGamesByTitle(String title) {
        ArrayList<Game> results = new ArrayList<>();
        APICalypse apicalypse = new APICalypse().search(title)
        .fields("game.id,game.name,game.involved_companies.developer,game.involved_companies.company.name,game.cover.image_id")
        .where("game != null & game.version_parent = null & game.game_type.type = \"Main Game\" & game.involved_companies != null" );
        try {
            List<proto.Search> searchResults = ProtoRequestKt.search(getIgdbWrapper(), apicalypse);
            for (proto.Search search : searchResults) {
                proto.Game igdbGame = search.getGame();
                String coverUrl = extractCoverUrlFromIgdbGame(igdbGame);
                Developer developer = extractDeveloperFromIgdbGame(igdbGame, false);
                Game result = new Game((int) igdbGame.getId(), igdbGame.getName(), coverUrl, developer);
                results.add(result);
            }
        } catch (RequestException ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Something happend fetching data on IGDB API");
        }
        return results;
    }

    public Game getGameByIgdbId(String igdbId) {
        Integer id = null;
        try {
            id = Integer.parseInt(igdbId);
        } catch (NumberFormatException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid IGDB ID format");
        }
        Game localGame = gameRepository.findByIdIgdb(id).orElse(null);
        if (localGame != null) {
            return localGame;
        }
        APICalypse apicalypse = new APICalypse().fields("*,genres.*,platforms.*,involved_companies.developer,involved_companies.company.name,cover.image_id,screenshots.*").where("id = " + id);
        try {
            List<proto.Game> games = ProtoRequestKt.games(getIgdbWrapper(), apicalypse);
            if (games.isEmpty()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Game not found in IGDB");
            }
            proto.Game igdbGame = games.get(0);
            String coverUrl = extractCoverUrlFromIgdbGame(igdbGame);
            List<String> genres = igdbGame.getGenresList().stream().map(g -> g.getName()).toList();
            List<String> screenshots = igdbGame.getScreenshotsList().stream()
                    .map(s -> ImageBuilderKt.imageBuilder(s.getImageId(), ImageSize.SCREENSHOT_HUGE, ImageType.WEBP))
                    .toList();
            List<String> platforms = igdbGame.getPlatformsList().stream().map(p -> p.getName()).toList();
            Developer developer = extractDeveloperFromIgdbGame(igdbGame, true);

            Game newGame = new Game(
                    (int) igdbGame.getId(),
                    igdbGame.getName(),
                    igdbGame.getSummary(),
                    coverUrl,
                    developer,
                    genres,
                    screenshots,
                    platforms);

            return save(newGame);

        } catch (RequestException e) {
            logger.info("ERROR");
            logger.info(String.valueOf(e.getStatusCode()));
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Something happend fetching data on IGDB API");
        }
    }

    public List<Post> getGamePosts(Pageable pageable, Integer id) {
        Game game = findById(id);
        if (game == null) {
            return List.of();
        }
        return postRepository.findByGame(pageable, game);
    }

    public List<Review> getGameReviews(Pageable pageable, Integer id) {
        Game game = findById(id);
        if (game == null) {
            return List.of();
        }
        return reviewRepository.findByGame(pageable, game);
    }

    public Game saveGameIntoProfile(SaveGameRequest request) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'saveGameIntoProfile'");
    }

}
