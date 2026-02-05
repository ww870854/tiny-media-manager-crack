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
import java.util.Set;

public class LicenseImpl implements License {

    private static final Logger LOGGER = LoggerFactory.getLogger(LicenseImpl.class);

    private static LicenseImpl instance;

    private final Class<?> providerClass;

    // 暂不开放的组件
    private static final Set<String> NOT_OPENED_PROVIDER = Set.of(
            "mpdbtv", // 网站目前处于检修中，暂不颁发api秘钥
            "fernsehserien" // 德国网站，实在找不到接口地址
    );

    // 内置组件密钥
    private static final Map<String, String[]> API_KEYS_MAP = new HashMap<>() {{
        put("imdb", new String[] { "https://www.imdb.com/" });
        put("anidb", new String[]{ "client=anidbscraper&clientver=1&protover=1&" });
        put("ofdb", new String[] { "https://www.ofdb.de/" });
        put("fanarttv", new String[]{ "d2d31f9ecabea050fc7d68aa3146015f" });
        put("hd-trailers", new String[]{ "https://www.hd-trailers.net/movie/" });
        put("opensubtitles", new String[] { "tinyMediaManager v5" });
        put("opensubtitles2", new String[] { "1GwVnk4nICRIDGfXp9FP8ABL3ssFUhx2" });
        put("subdl", new String[]{ "https://api.subdl.com/api/v1/" });
        put("yify", new String[]{ "https://yifysubtitles.ch" });
        put("tvmaze", new String[]{ "https://api.tvmaze.com/" });
        put("trakt", new String[]{ "170d57cca2c98dd7bc983f91c167ab3ff73a29c81666a8e713fed349851f50ee" });
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
    public void init522() {
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
        if (Objects.nonNull(providerClass) && providerClass.isAssignableFrom(tmmFeature.getClass())) {
            try {
                String id = (String) tmmFeature.getClass().getMethod("getId").invoke(tmmFeature);
                if (StringUtils.isBlank(id)) {
                    return false;
                }
                return !NOT_OPENED_PROVIDER.contains(id);
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
                return API_KEYS_MAP.get(id);
            } catch (Exception e) {
                LOGGER.info("NOT FOUND ID OF [" + tmmFeature.getFeatureName() + "]");
                return null;
            }
        }
        return null;
    }
}
