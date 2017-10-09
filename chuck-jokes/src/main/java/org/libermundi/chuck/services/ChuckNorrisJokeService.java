package org.libermundi.chuck.services;

import guru.springframework.norris.chuck.ChuckNorrisQuotes;
import org.springframework.stereotype.Service;

@Service
public class ChuckNorrisJokeService implements JokeService {
    private ChuckNorrisQuotes chuckNorrisQuotes;

    public ChuckNorrisJokeService() {
        this.chuckNorrisQuotes = new ChuckNorrisQuotes();
    }

    @Override
    public String getRandomQuote() {
        return this.chuckNorrisQuotes.getRandomQuote();
    }
}
