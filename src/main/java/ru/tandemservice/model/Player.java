package ru.tandemservice.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Player {
    private final String nickname;
    private final String name;
    private final String surname;
    private LocalDateTime lastActivityDateTime;
    private final LocalDateTime registrationDate;

    public Player(String nickname, String name, String surname) {
        this.nickname = nickname;
        this.name = name;
        this.surname = surname;
        this.registrationDate = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDateTime getLastActivityDateTime() {
        return lastActivityDateTime;
    }

    public void setLastActivityDateTime(LocalDateTime lastActivityDateTime) {
        this.lastActivityDateTime = lastActivityDateTime;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String toString() {
        return getName() + " "
                + getSurname() + ": "
                + "(Последняя активность: "
                + getLastActivityDateTime().format(DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd")) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(getNickname(), player.getNickname())
                && Objects.equals(getName(), player.getName())
                && Objects.equals(getSurname(), player.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNickname(), getName(), getSurname());
    }
}
