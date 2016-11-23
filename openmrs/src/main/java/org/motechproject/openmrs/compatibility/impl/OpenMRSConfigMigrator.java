package org.motechproject.openmrs.compatibility.impl;

import org.motechproject.openmrs.compatibility.ConfigMigrator;
import org.motechproject.openmrs.config.Config;
import org.motechproject.openmrs.service.OpenMRSConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * This class is responsible for migrating tasks that use the database id as the provider id.
 * Since the database ID is not reliable, and it will not work across different systems (in case of import/export)  we
 * have decided to drop it in favor of using provider names. This migrator will switch the provider ids used in expressions
 * to provider names. Provider names already stored in the current task config are used.
 */
@Component
public class OpenMRSConfigMigrator implements ConfigMigrator {

    @Autowired
    private OpenMRSConfigService configService;

    @Override
    public void migrate(Config config) {
        configService.addConfig(config);
        configService.markConfigAsDefault(config.getName());
    }

}
