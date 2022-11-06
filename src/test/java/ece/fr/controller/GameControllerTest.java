package ece.fr.controller;

import ece.fr.manager.LastAliveEvaluator;
import ece.fr.model.ship.GameState;
import ece.fr.view.ViewCommandLineInterface;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
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