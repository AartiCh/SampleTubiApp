package com.tubi.movies;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.tubi.movies.model.MovieItem;
import com.tubi.movies.util.Cache;
import com.tubi.movies.util.LRUCache;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class LruCacheTest {

    MovieItem movieItem1;
    MovieItem movieItem2;
    MovieItem movieItem3;
    MovieItem movieItem4;
    MovieItem movieItem5;
    MovieItem movieItem6;
    LRUCache<String, MovieItem> cache;

    @Before
    public void initialSet() {

        cache = Cache.getInstance().getLru();
        movieItem1 = new MovieItem();
        movieItem1.setImage("www.google.com");
        movieItem1.setId("1");
        movieItem1.setTitle("Movie1");

        movieItem2 = new MovieItem();
        movieItem2.setImage("www.google.com");
        movieItem2.setId("2");
        movieItem2.setTitle("Movie2");

        movieItem3 = new MovieItem();
        movieItem3.setImage("www.google.com");
        movieItem3.setId("3");
        movieItem3.setTitle("Movie3");

        movieItem4 = new MovieItem();
        movieItem4.setImage("www.google.com");
        movieItem4.setId("4");
        movieItem4.setTitle("Movie4");

        movieItem5 = new MovieItem();
        movieItem5.setImage("www.google.com");
        movieItem5.setId("5");
        movieItem5.setTitle("Movie5");

        movieItem6 = new MovieItem();
        movieItem6.setImage("www.google.com");
        movieItem6.setId("5");
        movieItem6.setTitle("Movie6");
    }

    @Test
    public void testLRUCacheGetMethod() {
        cache.add(movieItem1.getTitle(), movieItem1);
        assertEquals(cache.get(movieItem1.getTitle()), movieItem1);
    }

    @Test
    public void testLRUCacheAddMethod() {
        cache.add(movieItem2.getTitle(), movieItem2);
        assertEquals(cache.get(movieItem2.getTitle()), movieItem2);
    }

    @Test
    public void testLRUCachePutMethodMaxCapacity() {
        cache.add(movieItem1.getTitle(), movieItem1);
        cache.add(movieItem2.getTitle(), movieItem2);
        cache.add(movieItem3.getTitle(), movieItem3);
        cache.add(movieItem4.getTitle(), movieItem4);
        cache.add(movieItem5.getTitle(), movieItem5);
        assertEquals(cache.size, 5);
        cache.add(movieItem5.getTitle(), movieItem5);
        cache.add(movieItem5.getTitle(), movieItem5);
        cache.add(movieItem6.getTitle(), movieItem6);
        assertNull(cache.get(movieItem1.getTitle()));

    }
}
