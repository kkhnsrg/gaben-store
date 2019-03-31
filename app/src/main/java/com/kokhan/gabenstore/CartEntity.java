package com.kokhan.gabenstore;

import java.util.ArrayList;
import java.util.List;

public final class CartEntity {
    private final List<Game> gameList;

    private static final CartEntity ourInstance = new CartEntity();

    public static CartEntity getInstance() {
        return ourInstance;
    }

    private CartEntity() {
        gameList = new ArrayList<>();
    }

    public List<Game> getGameList() {
        return gameList;
    }
}
