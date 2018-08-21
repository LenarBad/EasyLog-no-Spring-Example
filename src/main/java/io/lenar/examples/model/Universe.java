package io.lenar.examples.model;

import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import io.lenar.easy.log.annotations.LogIt;
import io.lenar.examples.exceptions.UnreachableEventException;

public class Universe {

    private List<Star> stars;

    private List<BlackHole> blackHoles;

    private Date dateOfCreation;

    public Universe(int numberOfStars, int numberOfBlackHoles) {
        bigBang(numberOfStars, numberOfBlackHoles);
    }

    @LogIt(label = "Method Level", maskFields = {"name"})
    public Universe bigBang(int numberOfStars, int numberOfBlackHoles) {
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

    @LogIt
    private List<Planet> randomPlanets() {
        return IntStream.range(0, (int) (Math.random() * 3)).boxed()
                .map(item -> new Planet(randomName("Planet-"), new Random().nextBoolean()))
                .collect(Collectors.toList());
    }

    @LogIt
    public List<Star> getStarsBeforeBigBang() throws UnreachableEventException {
        throw new UnreachableEventException();
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
