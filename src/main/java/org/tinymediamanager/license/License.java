package org.tinymediamanager.license;

import java.time.LocalDate;

public interface License {
    static License getInstance() {
        return MyLicense.getInstance();
    }

    void init2122();

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
