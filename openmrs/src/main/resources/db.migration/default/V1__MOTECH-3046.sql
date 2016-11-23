-- Changing bundle name from org.motechproject.openmrs-19 to org.motechproject.openmrs

UPDATE "MOTECH_PLATFORM_SERVER_CONFIG_MODULEPROPERTIESRECORD" set "bundle"='org.motechproject.openmrs' where "bundle"='org.motechproject.openmrs-19';

-- Changing properties file name from openmrs.properties to openmrs-configs.json

UPDATE "MOTECH_PLATFORM_SERVER_CONFIG_MODULEPROPERTIESRECORD" set "filename"='openmrs-configs.json' where "filename"='openmrs.properties';
