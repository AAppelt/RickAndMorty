package com.caramelheaven.rickandmorty.datasourse.entity.character;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Objects;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Character extends RealmObject{

    @PrimaryKey
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("species")
    @Expose
    private String species;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("gender")
    @Expose
    private String gender;

    @SerializedName("location")
    @Expose
    private CharacterLocation location;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("episode")
    @Expose
    private RealmList<String> episode = null;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("created")
    @Expose
    private String created;

    @SerializedName("origin")
    @Expose
    private Origin origin;

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public Character() {
    }

    public Character(Integer id, String name, String status, String species, String type, String gender,
                     CharacterLocation location, String image, RealmList<String> episode, String url, String created, Origin origin) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.species = species;
        this.type = type;
        this.gender = gender;
        this.location = location;
        this.image = image;
        this.episode = episode;
        this.url = url;
        this.created = created;
        this.origin = origin;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public String getSpecies() {
        return species;
    }

    public String getType() {
        return type;
    }

    public String getGender() {
        return gender;
    }

    public String getUrl() {
        return url;
    }

    public String getCreated() {
        return created;
    }

    public CharacterLocation getLocation() {
        return location;
    }

    public void setLocation(CharacterLocation location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getEpisode() {
        return episode;
    }

    public void setEpisode(RealmList<String> episode) {
        this.episode = episode;
    }

    @Override
    public String toString() {
        return "Character{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", species='" + species + '\'' +
                ", type='" + type + '\'' +
                ", gender='" + gender + '\'' +
                ", location=" + location +
                ", image='" + image + '\'' +
                ", episode=" + episode +
                ", url='" + url + '\'' +
                ", created='" + created + '\'' +
                ", origin=" + origin +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return Objects.equals(id, character.id) &&
                Objects.equals(name, character.name) &&
                Objects.equals(status, character.status) &&
                Objects.equals(species, character.species) &&
                Objects.equals(type, character.type) &&
                Objects.equals(gender, character.gender) &&
                Objects.equals(location, character.location) &&
                Objects.equals(image, character.image) &&
                Objects.equals(episode, character.episode) &&
                Objects.equals(url, character.url) &&
                Objects.equals(created, character.created) &&
                Objects.equals(origin, character.origin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, species, type, gender, location, image, episode, url, created, origin);
    }
}