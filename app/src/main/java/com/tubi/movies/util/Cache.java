package com.tubi.movies.util;

import androidx.annotation.VisibleForTesting;

import com.tubi.movies.model.MovieItem;

public class Cache {

    private static Cache instance;
    @VisibleForTesting
    protected LRUCache<String, MovieItem> lru;

    private Cache() {
        lru = new LRUCache<>(5);
    }

    public static Cache getInstance() {

        if (instance == null) {
            instance = new Cache();
        }
        return instance;
    }

    public LRUCache<String, MovieItem> getLru() {
        return lru;
    }
}
