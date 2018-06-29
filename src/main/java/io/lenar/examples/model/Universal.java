package io.lenar.examples.model;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.lenar.easy.log.annotations.LogIt;

public class Universal {

    private List<Star> stars;

    private List<BlackHole> blackHoles;

    private Date dateOfCreation;

    public Universal(int numberOfStars, int numberOfBlackHoles) {
        bigBang(numberOfStars, numberOfBlackHoles);
    }

    @LogIt
    public Universal bigBang(int numberOfStars, int numberOfBlackHoles) {
        blackHoles = IntStream.range(0, numberOfBlackHoles).boxed()
                .map(item -> new BlackHole(randomName("BlackHole-")))
                .collect(Collectors.toList());

        stars = IntStream.range(0, numberOfStars).boxed()
                .map(item -> new Star(randomName("Star-"), StarType.getRandom(), randomPlanets()))
                .collect(Collectors.toList());

        dateOfCreation = new Date();

        return this;
    }

    private String randomName(String prefix) {
        return prefix + UUID.randomUUID().toString();
    }

    private List<Planet> randomPlanets() {
        return IntStream.range(0, (int) (Math.random() * 3)).boxed()
                .map(item -> new Planet(randomName("Planet-"), new Random().nextBoolean()))
                .collect(Collectors.toList());
    }

    public String toBeLogged(String param) {
        return param + " :: " + param;
    }

    @LogIt
    public String anotherMethodToBeLogged(String param) {
        return param + " :!!!: " + param;
    }

    public List<Star> getStars() {
        return stars;
    }

    public void setStars(List<Star> stars) {
        this.stars = stars;
    }

    public List<BlackHole> getBlackHoles() {
        return blackHoles;
    }

    public void setBlackHoles(List<BlackHole> blackHoles) {
        this.blackHoles = blackHoles;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
