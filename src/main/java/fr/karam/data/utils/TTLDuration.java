package fr.karam.data.utils;

import java.util.concurrent.TimeUnit;

public record TTLDuration(long ttlLong, TimeUnit ttlUnit) {

}
