package org.tinymediamanager.license;

import org.apache.commons.lang3.StringUtils;

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
        if (StringUtils.isNoneBlank(apiKey)) {
            return true;
        }
        return StringUtils.isNoneBlank(getApiKey());
    }

    default String getFeatureName() {
        return this.getClass().getSimpleName();
    }
}
