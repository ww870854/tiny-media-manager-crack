package org.tinymediamanager.license;

import org.tinymediamanager.license.impl.LicenseImpl;

import java.time.LocalDate;

public interface License {
    static License getInstance() {
        return LicenseImpl.getInstance();
    }

    void init2123();

    void setLicenseCode(String licenseCode);

    String getLicenseCode();

    boolean isValidLicense();

    LocalDate validUntil();

    String dat();

    String sig();

    String ref();

    void addEventListener(LicenseEventListener licenseEventListener);

    boolean isFeatureEnabled(TmmFeature tmmFeature);

    String getApiKey(TmmFeature tmmFeature);

    String[] getApiKeys(TmmFeature tmmFeature);

}
