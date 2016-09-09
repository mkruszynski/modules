#!/usr/bin/env bash

if [ "$TRAVIS_PULL_REQUEST" != "false" ]; then
    #export MOTECH_CONFIG_DIR=~/.motech/config/
    if [ "$DB" = "mysql" ]; then
        echo "USE mysql;\nUPDATE user SET password=PASSWORD('password') WHERE user='root';\nFLUSH PRIVILEGES\n" | mysql -u root
        mvn -Dmysql.password=password -Dmaven.test.failure.ignore=false  clean install -Dmysql.user=root -Dmotech.sql.driver=com.mysql.jdbc.Driver -Dmotech.sql.dbtype=mysql -Dmotech.sql.url=jdbc:mysql://localhost:3306/ clean install -PIT -U
    elif [ "$DB" = "psql" ]; then
        mvn -Dmotech.sql.password=password -Dmotech.sql.user=postgres -Dmaven.test.failure.ignore=false -Dmotech.sql.driver=org.postgresql.Driver -Dmotech.sql.dbtype=psql -Dmotech.sql.url=jdbc:postgresql://localhost:5432/ clean install -PIT -U
    fi
fi
