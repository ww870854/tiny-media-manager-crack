package org.tinymediamanager.license;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

public class MyLicense implements License {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyLicense.class);

    private static MyLicense instance;

    public static MyLicense getInstance() {
        if (null == instance) {
            instance = new MyLicense();
        }
        return instance;
    }

    @Override
    public void init2121() {
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
        // always enabled.
        return true;
    }

    @Override
    public String getApiKey(TmmFeature tmmFeature) {
        // we don't support any api key in license. just config in settings by your self.
        return "";
    }

    @Override
    public String[] getApiKeys(TmmFeature tmmFeature) {
        return new String[] { getApiKey(tmmFeature) };
    }


}
