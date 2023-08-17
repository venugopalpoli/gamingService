package com.fsbtech.gamingservice;

import com.fsbtech.gamingservice.data.Game;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GamingServiceApplicationTests {

    @LocalServerPort
    private int port;

    private String baseUrl="http://localhost:";

    private static RestTemplate restTemplate;

    @BeforeAll
    public static void init() {
        restTemplate = new RestTemplate();
    }

    @BeforeEach
    public void setUp(){
        baseUrl=baseUrl.concat(port + "").concat("/games");
    }

    @Test
    public void testGamingServiceForHappyPath(){
        // adding one game
        Game game = new Game();
        game.setName("Golf");
        game.setActive(Boolean.FALSE);

        // POST operation to add/create a game
        Game actualGameForPost = restTemplate.postForObject(baseUrl .concat("/add"),game,Game.class);
        assertNotNull(actualGameForPost);
        assertNotNull(actualGameForPost.getId());

        assertEquals(LocalDate.now(),actualGameForPost.getCreationDate());
        assertEquals("Golf", actualGameForPost.getName());
        assertEquals(Boolean.FALSE, actualGameForPost.getActive());

        //GET Operation to get the game just created
        Game actualGetGame = restTemplate.getForObject(baseUrl.concat("/get/").concat(actualGameForPost.getId()), Game.class);
        assertNotNull(actualGetGame);

        assertEquals(actualGameForPost.getId(),actualGetGame.getId());
        assertEquals(actualGameForPost.getCreationDate(),actualGetGame.getCreationDate());
        assertEquals(actualGameForPost.getName(), actualGetGame.getName());
        assertEquals(actualGameForPost.getActive(), actualGetGame.getActive());

        //PUT Operation to update fields of the Game
        game = new Game();
        game.setName("Badminton");
        game.setActive(Boolean.TRUE);
        restTemplate.put(baseUrl.concat("/update/").concat(actualGameForPost.getId()), game);

        //GET Operation to get updated game
        Game actualUpdatedGame = restTemplate.getForObject(baseUrl.concat("/get/").concat(actualGameForPost.getId()), Game.class);
        assertNotNull(actualUpdatedGame);

        assertEquals(actualGetGame.getId(),actualUpdatedGame.getId());
        assertEquals(actualGetGame.getCreationDate(),actualUpdatedGame.getCreationDate());
        // Here checking updated fields
        assertEquals("Badminton", actualUpdatedGame.getName());
        assertEquals(Boolean.TRUE, actualUpdatedGame.getActive());


        // POST operation to add/create another game
        // adding another game
        game = new Game();
        game.setName("Cricket");
        game.setActive(Boolean.TRUE);

        actualGameForPost = restTemplate.postForObject(baseUrl .concat("/add"),game,Game.class);
        assertNotNull(actualGameForPost);
        assertNotNull(actualGameForPost.getId());

        assertEquals(LocalDate.now(),actualGameForPost.getCreationDate());
        assertEquals("Cricket", actualGameForPost.getName());
        assertEquals(Boolean.TRUE, actualGameForPost.getActive());

        //GET Operation to get all the game just created
        List<Game> allGamesList = restTemplate.getForObject(baseUrl.concat("/get/all"), List.class);
        assertNotNull(allGamesList);
        // here checking for two games
        assertEquals(2, allGamesList.size());

        //Delete operation to remove game with the id
        restTemplate.delete(baseUrl.concat("/delete/").concat(actualGameForPost.getId()));

        //GET Operation to get all the games
        allGamesList = restTemplate.getForObject(baseUrl.concat("/get/all"), List.class);
        assertNotNull(allGamesList);
        //here checking for one game as another was removed by the Delete operation
        assertEquals(1, allGamesList.size());
    }

    @Test
    public void testGamingServiceForUnHappyPath(){

        // GET operation with INVALID id
        try {
            restTemplate.getForObject(baseUrl.concat("/get/").concat("testget1234"), Game.class);
        }catch (Exception e) {
            assertTrue(e.getMessage().contains("testget1234 is not exist"));
            assertTrue(e.getMessage().contains("NOT_FOUND_ERROR"));
        }

        // PUT operation with INVALID id
        try {
            Game game = new Game();
            game.setName("Cricket");
            game.setActive(Boolean.TRUE);
            restTemplate.put(baseUrl.concat("/update/").concat("testupdate1234"), game);
        }catch (Exception e) {
            assertTrue(e.getMessage().contains("testupdate1234 is not exist"));
            assertTrue(e.getMessage().contains("NOT_FOUND_ERROR"));
        }

        // DELETE operation with INVALID id
        try {
            restTemplate.delete(baseUrl.concat("/delete/").concat("testdelete1234"));
        }catch (Exception e) {
            assertTrue(e.getMessage().contains("testdelete1234 is not exist"));
            assertTrue(e.getMessage().contains("NOT_FOUND_ERROR"));
        }

    }

}
