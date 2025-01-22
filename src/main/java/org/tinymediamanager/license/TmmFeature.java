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
        return null != apiKey && apiKey.trim().length() > 0;
    }

    default String getFeatureName() {
        return this.getClass().getSimpleName();
    }
}
