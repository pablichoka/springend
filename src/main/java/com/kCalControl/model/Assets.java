package com.kCalControl.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Assets {

    private UserDB creationPerson;
    private LocalDateTime creationDate;
    private UserDB modificationPerson;
    private LocalDateTime modificationDate;

    public UserDB getCreationPerson() {
        return creationPerson;
    }

    public void setCreationPerson(UserDB creationPerson) {
        this.creationPerson = creationPerson;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public UserDB getModificationPerson() {
        return modificationPerson;
    }

    public void setModificationPerson(UserDB modificationPerson) {
        this.modificationPerson = modificationPerson;
    }

    public LocalDateTime getModificationDate() {
        return modificationDate;
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = modificationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Assets assets)) return false;
        return Objects.equals(creationPerson, assets.creationPerson) && Objects.equals(creationDate, assets.creationDate) && Objects.equals(modificationPerson, assets.modificationPerson) && Objects.equals(modificationDate, assets.modificationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(creationPerson, creationDate, modificationPerson, modificationDate);
    }

    @Override
    public String toString() {
        return "Assets{" +
                "creationPerson=" + creationPerson.getId() +
                ", creationDate=" + creationDate +
                ", modificationPerson=" + modificationPerson.getId() +
                ", modificationDate=" + modificationDate +
                '}';
    }
}
