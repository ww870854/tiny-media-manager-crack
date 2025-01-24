package org.tinymediamanager.license;

public interface TmmFeature {

    default boolean isFeatureEnabled() {
        return License.getInstance().isFeatureEnabled(this);
    }

    default String getApiKey() {
        return License.getInstance().getApiKey(this);
    }

    default String[] getApiKeys() {
        return License.getInstance().getApiKeys(this);
    }

    default boolean isApiKeyAvailable(String apiKey) {
        // fix: some scrapers like FanartTv call this method with null value.
        return License.getInstance().isValidLicense();
    }

    default String getFeatureName() {
        return this.getClass().getSimpleName();
    }
}
