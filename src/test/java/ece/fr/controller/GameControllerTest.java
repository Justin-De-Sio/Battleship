package ece.fr.controller;

import ece.fr.controller.manager.LastAliveEvaluator;
import ece.fr.view.ViewCommandLineInterface;
import org.junit.jupiter.api.BeforeEach;

import java.io.StringReader;

class GameControllerTest {
    GameController gameController;
    StringReader sr = new StringReader("1");

    @BeforeEach
    void setUp() {
        ViewCommandLineInterface view = new ViewCommandLineInterface();
        GameEvaluator gameEvaluator = new LastAliveEvaluator();
        gameController = new GameController(view, gameEvaluator);


    }



}