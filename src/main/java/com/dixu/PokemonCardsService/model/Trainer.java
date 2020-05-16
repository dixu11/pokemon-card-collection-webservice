package com.dixu.PokemonCardsService.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Trainer {

    public enum Sex {
        FEMALE, MALE;
    }

    @Id
    private String mail;
    private String name;
    private Sex sex;
    private String type;
    @ManyToMany
    private List<Card> cards = new ArrayList<>();
    private int coinsCount = 99999;

    public Trainer(String mail,String name, Sex sex, String type) {
        this.mail = mail;
        this.name = name;
        this.sex = sex;
        this.type = type;
    }

    public void addCoins(int count) {
        coinsCount += count;
    }

    public void takeCoins(int count) {
        coinsCount -= count;
    }

    public void addCards(List<Card> cards) {
        this.cards.addAll(cards);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trainer trainer = (Trainer) o;
        return mail.equals(trainer.mail);
    }

    @Override
    public int hashCode() {
        return Objects.hash(mail);
    }
}
