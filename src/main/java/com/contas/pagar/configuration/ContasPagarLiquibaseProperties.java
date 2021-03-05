package com.contas.pagar.configuration;

import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import static com.contas.pagar.database.DatabaseSpecifications.DatabaseContasPagar.*;

@Primary
@Configuration
public class ContasPagarLiquibaseProperties extends LiquibaseProperties {

    public ContasPagarLiquibaseProperties() {
        setDriverClassName(DRIVE_CLASS_NAME);
        setUrl(CONNECTION_URL);
        setUser(Roles.Liquibase.NAME);
        setPassword(Roles.Liquibase.SECRET);
        setDefaultSchema(Schemas.ContasPagar.NAME);
        setLiquibaseSchema(Schemas.Migrations.NAME);
        setTestRollbackOnUpdate(false);
    }
}
