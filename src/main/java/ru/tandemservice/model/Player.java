package ru.tandemservice.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Player {
    private final String nickname;
    private final String email;
    private LocalDateTime lastActivityDateTime;
    private final LocalDateTime registrationDate;

    public Player(String nickname, String email) {
        this.nickname = nickname;
        this.email = email;

        this.registrationDate = LocalDateTime.now();
        this.lastActivityDateTime = LocalDateTime.now();
    }

    public LocalDateTime getLastActivityDateTime() {
        return lastActivityDateTime;
    }

    public void setLastActivityDateTime() {
        this.lastActivityDateTime = LocalDateTime.now();
    }

    public String getEmail() {
        return email;
    }

    public String getNickname() {
        return nickname;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    @Override
    public String toString() {
        return getNickname()
                + "(Последняя активность: "
                + getLastActivityDateTime().format(DateTimeFormatter.ofPattern("HH:mm:ss yyyy-MM-dd")) + ")";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return Objects.equals(getNickname(), player.getNickname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNickname());
    }
}
