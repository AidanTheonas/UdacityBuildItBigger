package com.servicebomba.jokesjavalibrary;

import java.util.Random;

public class TellJoke {
    String[] jokes;
    Random randomJoke;

    public TellJoke(){
        jokes = new String[5];
    jokes[0] = "What's the Object-Oriented way to become wealthy? \"Inheritance\"";
        jokes[1] = "Why do Java developers wear glasses? \"Because they can't C#\"";
        jokes[2] = "What is a programmer's favorite hangout place? \"Foo Bar\"";
        jokes[3] = "A foo walks into a bar takes a look around and says \"Hello World!\"";
        jokes[4] = "\"Programmer\" A machine that turns coffee into code.";
        randomJoke = new Random();
    }

    public String[] getAllJokes() {
        return jokes;
    }

    public String getRandomJoke() {
        return jokes[randomJoke.nextInt(jokes.length)];
    }
}
