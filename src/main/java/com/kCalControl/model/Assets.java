package com.kCalControl.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Embeddable
public class Assets {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creation_person")
    private UserDB creationPerson;
    @Column(name = "creation_date")
    private LocalDateTime creationDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "modification_person")
    private UserDB modificationPerson;
    @Column(name = "modification_date")
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
