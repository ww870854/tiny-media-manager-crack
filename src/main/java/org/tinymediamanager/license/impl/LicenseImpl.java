package org.tinymediamanager.license.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tinymediamanager.license.License;
import org.tinymediamanager.license.LicenseEventListener;
import org.tinymediamanager.license.TmmFeature;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LicenseImpl implements License {

    private static final Logger LOGGER = LoggerFactory.getLogger(LicenseImpl.class);

    private static LicenseImpl instance;

    private final Class<?> providerClass;

    private static final Map<String, String[]> apiKeysMap = new HashMap<>() {{
        put("tmdb", new String[]{ "2b9acf560289dea4a21bf163668a3054" });
        put("imdb", new String[] { "https://www.imdb.com/" });
        put("omdbapi", new String[]{ });
        put("trakt", new String[]{ });
        put("moviemeter", new String[]{ "892f3086adc27cb003dca9da9c6d023a" });
        put("tvdb", new String[]{ });
        put("anidb", new String[]{ "client=anidbscraper&clientver=1&protover=1&" });
        put("ofdb", new String[] { "https://www.ofdb.de/" });
        // TODO mpdbtv apiKey，收费，暂不开启
        // put("mpdbtv", null);
        put("kodi", new String[]{ });
        put("universal_movie", new String[]{ });
        put("fanarttv", new String[]{ "d2d31f9ecabea050fc7d68aa3146015f" });
        put("ffmpeg", new String[]{ });
        put("hd-trailers", new String[]{ "https://www.hd-trailers.net/movie/" });
        put("opensubtitles2", new String[] { "1GwVnk4nICRIDGfXp9FP8ABL3ssFUhx2" });
        put("tvmaze", new String[]{ "https://api.tvmaze.com/" });
        put("universal_tvshow", new String[]{ });
        put("mdblist", new String[] { });
    }};

    private LicenseImpl() {
        Class<?> providerClass;
        try {
            providerClass = Class.forName("org.tinymediamanager.scraper.interfaces.IMediaProvider");
        } catch (Exception e) {
            providerClass = null;
            LOGGER.warn("NOT FOUND INTERFACE: IMediaProvider[org.tinymediamanager.scraper.interfaces.IMediaProvider]");
        }
        this.providerClass = providerClass;
    }

    public synchronized static LicenseImpl getInstance() {
        if (null == instance) {
            instance = new LicenseImpl();
        }
        return instance;
    }

    @Override
    public void init2123() {
        LOGGER.info("JUST FOR STUDYING!!!");
    }

    @Override
    public void setLicenseCode(String licenseCode) {
        LOGGER.info("WE DO NOT NEED LICENSE ANYMORE!!!");
    }

    @Override
    public String getLicenseCode() {
        return "tmm pro cracked.";
    }

    @Override
    public boolean isValidLicense() {
        // always valid.
        return true;
    }

    @Override
    public LocalDate validUntil() {
        return LocalDate.of(9999, 12, 31);
    }

    @Override
    public String dat() {
        return "cracked";
    }

    @Override
    public String sig() {
        return "for studying";
    }

    @Override
    public String ref() {
        return "@ranger";
    }

    @Override
    public void addEventListener(LicenseEventListener licenseEventListener) {
        LOGGER.info("WE DO NOT NEED LICENSE LISTENER ANYMORE!!!");
    }

    @Override
    public boolean isFeatureEnabled(TmmFeature tmmFeature) {
        if (providerClass.isAssignableFrom(tmmFeature.getClass())) {
            try {
                String id = (String) tmmFeature.getClass().getMethod("getId").invoke(tmmFeature);
                if (StringUtils.isBlank(id)) {
                    return false;
                }
                return apiKeysMap.containsKey(id);
            } catch (Exception e) {
                LOGGER.info("NOT FOUND ID OF [" + tmmFeature.getFeatureName() + "]");
                return false;
            }
        }
        return true;
    }

    @Override
    public String getApiKey(TmmFeature tmmFeature) {
        String[] apiKeys = getApiKeys(tmmFeature);
        if (Objects.nonNull(apiKeys) && apiKeys.length > 0) {
            return apiKeys[0];
        }
        return StringUtils.EMPTY;
    }

    @Override
    public String[] getApiKeys(TmmFeature tmmFeature) {
        if (providerClass.isAssignableFrom(tmmFeature.getClass())) {
            try {
                String id = (String) tmmFeature.getClass().getMethod("getId").invoke(tmmFeature);
                if (StringUtils.isBlank(id)) {
                    return null;
                }
                return apiKeysMap.get(id);
            } catch (Exception e) {
                LOGGER.info("NOT FOUND ID OF [" + tmmFeature.getFeatureName() + "]");
                return null;
            }
        }
        return null;
    }
}
