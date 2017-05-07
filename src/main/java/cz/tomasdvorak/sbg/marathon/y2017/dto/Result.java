package cz.tomasdvorak.sbg.marathon.y2017.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Optional;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {
    @JsonProperty("Participants")
    private List<Participant> participants;
    private String bib;
    private String time;
    private int rank;
    private int genderrank;
    private String category;
    private float kmh;
    private String state;
    private long distance_num;
    private String distance_str;
    private boolean has_details;

    public String getBib() {
        return bib;
    }

    public void setBib(final String bib) {
        this.bib = bib;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(final List<Participant> participants) {
        this.participants = participants;
    }

    public String getTime() {
        return time;
    }

    public void setTime(final String time) {
        this.time = time;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(final int rank) {
        this.rank = rank;
    }

    public int getGenderrank() {
        return genderrank;
    }

    public void setGenderrank(final int genderrank) {
        this.genderrank = genderrank;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public float getKmh() {
        return kmh;
    }

    public void setKmh(final float kmh) {
        this.kmh = kmh;
    }

    public String getState() {
        return state;
    }

    public void setState(final String state) {
        this.state = state;
    }

    public long getDistance_num() {
        return distance_num;
    }

    public void setDistance_num(final long distance_num) {
        this.distance_num = distance_num;
    }

    public String getDistance_str() {
        return distance_str;
    }

    public void setDistance_str(final String distance_str) {
        this.distance_str = distance_str;
    }

    public boolean isHas_details() {
        return has_details;
    }

    public void setHas_details(final boolean has_details) {
        this.has_details = has_details;
    }

    public Participant getFirstParticipant() {
        return Optional.ofNullable(participants.get(0)).orElse(null);
    }
}
