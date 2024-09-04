package fr.karam.data.type.local;

import fr.karam.data.type.DataCredentials;

import java.time.Duration;

public class LocalCredentials extends DataCredentials {

    private Duration duration;


    public LocalCredentials(Duration duration) {
        this.duration = duration;
    }

    public Duration getDuration() {
        return duration;
    }

    public LocalCredentials setDuration(Duration duration) {
        this.duration = duration;
        return this;
    }
}
