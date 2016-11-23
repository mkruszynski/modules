package org.motechproject.openmrs.compatibility;

import org.motechproject.openmrs.config.Config;

/**
 * An interface that can be implemented by classes which want to apply migrations to configs.
 */
public interface ConfigMigrator {

    /**
     * Applies migration to configs.
     * @param config the config to migrate
     */
    void migrate(Config config);
}
