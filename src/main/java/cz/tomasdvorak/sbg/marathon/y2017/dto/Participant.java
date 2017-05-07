package cz.tomasdvorak.sbg.marathon.y2017.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Participant {
    private String title;
    private String bib;
    private String lastname;
    private String firstname;
    private String iaaf;
    private String club;
    private int birthyear;
    private Gender gender;


    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getBib() {
        return bib;
    }

    public void setBib(final String bib) {
        this.bib = bib;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getIaaf() {
        return iaaf;
    }

    public void setIaaf(final String iaaf) {
        this.iaaf = iaaf;
    }

    public String getClub() {
        return club;
    }

    public void setClub(final String club) {
        this.club = club;
    }

    public int getBirthyear() {
        return birthyear;
    }

    public void setBirthyear(final int birthyear) {
        this.birthyear = birthyear;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(final Gender gender) {
        this.gender = gender;
    }
}
