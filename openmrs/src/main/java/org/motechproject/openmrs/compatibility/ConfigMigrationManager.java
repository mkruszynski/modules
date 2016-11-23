package org.motechproject.openmrs.compatibility;

import org.motechproject.config.SettingsFacade;
import org.motechproject.openmrs.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * The manager used for applying migrations to configs from version 0.28 to olders. It gets all {@link org.motechproject.openmrs.compatibility.ConfigMigrator} beans from the
 * Spring context and applies them to the config being migrated. It can be called manually, it also triggers during
 * initialization to make sure the configs in the database are up to date.
 */
@Component
public class ConfigMigrationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigMigrationManager.class);

    private static final String OLD_BUNDLE_NAME = "org.motechproject.openmrs-19";
    private static final String OLD_CONFIG_FILENAME = "openmrs.properties";
    private static final String OPENMRS_URL = "openmrs.url";
    private static final String OPENMRS_USER = "openmrs.user";
    private static final String OPENMRS_PASSWORD = "openmrs.password";
    private static final String OPENMRS_MOTECH_ID = "openmrs.motechIdName";
    private static final String OPENMRS_IDENTIFIER_TYPES = "openmrs.identifierTypes";
    private static final String OPENMRS_CONFIG_NAME = "028-config";
    private static final String OPENMRS_CONFIG_VERSION = "1.9";

    @Autowired
    private ConfigMigrator migrator;

    @Autowired
    private SettingsFacade settingsFacade;

    /**
     * Migrates config from MOTECH version 0.28 or earlier.
     */
    @PostConstruct
    public void init() {
        //try {
        List<Resource> configFileNames = new ArrayList<>();
        configFileNames.add(new Resource() {
        });


            Config migratedConfig = new Config();
            /*Properties config = configurationService.getBundleProperties(OLD_BUNDLE_NAME, OLD_CONFIG_FILENAME, createDefaultProperties());
            List<String> patientIdentifierTypes = new ArrayList<>();

            patientIdentifierTypes.add(config.getProperty(OPENMRS_IDENTIFIER_TYPES));

            migratedConfig.setUsername(config.getProperty(OPENMRS_USER));
            migratedConfig.setPassword(config.getProperty(OPENMRS_PASSWORD));
            migratedConfig.setOpenMrsUrl(config.getProperty(OPENMRS_URL));
            migratedConfig.setMotechPatientIdentifierTypeName(config.getProperty(OPENMRS_MOTECH_ID));
            migratedConfig.setPatientIdentifierTypeNames(patientIdentifierTypes);
            migratedConfig.setName(OPENMRS_CONFIG_NAME);
            migratedConfig.setOpenMrsVersion(OPENMRS_CONFIG_VERSION);*/

            migrateConfig(migratedConfig);
        /*} catch (IOException e) {
            LOGGER.warn("Error during OpenMRS config file read: " + e.getMessage());
        }*/
    }

    /**
     * Migrates a single config.
     * @param config the config to migrate
     */
    @Transactional
    public void migrateConfig(Config config) {
        migrator.migrate(config);
    }

    private Properties createDefaultProperties() {
        Properties properties = new Properties();

        properties.put(OPENMRS_URL, "http://localhost:8080/openmrs");
        properties.put(OPENMRS_USER, "admin");
        properties.put(OPENMRS_PASSWORD, "Admin123");
        properties.put(OPENMRS_MOTECH_ID, "MOTECH Id");
        properties.put(OPENMRS_IDENTIFIER_TYPES, "");

        return properties;
    }
}
