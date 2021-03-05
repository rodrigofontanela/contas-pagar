-- liquibase formatted sql

-- changeset rodrigo.fontanela:1 context:dev splitStatements:false
do
$$
begin
   	grant select, insert, update, delete on all tables
   	   in schema contas_pagar to cp_system;

    alter default privileges
       in schema contas_pagar grant select, insert, update, delete
       on tables to cp_system;

    grant usage on all sequences
       in schema contas_pagar to cp_system;

    alter default privileges
       in schema contas_pagar grant usage
       on sequences to cp_system;
end
$$;
--rollback not required

