#!/usr/bin/env bash

rm -r ~/.m2/repository/org/motechproject/*
if [ "$TRAVIS_PULL_REQUEST" != "false" ]; then
    if [ "$DB" = "psql" ]; then
        mvn -Dmotech.sql.password=password -Dmotech.sql.user=postgres -Dmaven.test.failure.ignore=false -Dmotech.sql.driver=org.postgresql.Driver -Dmotech.sql.dbtype=psql -Dmotech.sql.url=jdbc:postgresql://localhost:5432/ clean install -PIT -U -B
    else
        mvn clean install -PIT -U -B
    fi
elif [ "$TRAVIS_BRANCH" = "master" ] && [ "$DB" = "mysql" ]; then

    mvn clean install -PIT -U

    if [ "$?" -ne 0 ]; then
        exit 1
    fi

    mvn clean deploy --settings deploy-settings.xml -Dmaven.test.skip=true -U
fi