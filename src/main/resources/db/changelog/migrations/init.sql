do $$
	begin
		set search_path = 'contas_pagar';
	
		--Extensão para pesquisas sem acentuação textual
		create extension if not exists unaccent 
	    schema pg_catalog;
	
	    --Extensão para tornar o 'case' irrelevante nas pesquisas textuais
		create extension if not exists citext 
	    schema pg_catalog;

	    --Schema do domínio do cliente 
		create schema if not exists contas_pagar authorization suser_mgm;
		
		--User da aplicação (permissão DML apenas, que será concedido posteriormente ao rodar o liquibase na aplicação)
		if not exists (select true from pg_catalog.pg_roles where rolname = 'cp_system') then
			create role cp_system with
				nosuperuser
				nocreatedb
				nocreaterole
				password 'cp_system'
				inherit
					login
					noreplication
					nobypassrls;
		end if;
	
		--Autorizações
		grant connect on database postgres 
	       to cp_system;
		
		grant usage on schema contas_pagar 
	       to cp_system;
	
	    --Schema de migração
		create schema if not exists migrations authorization suser_mgm;

		--User de migração (permissões DML e DDL apenas)
		if not exists (select true from pg_catalog.pg_roles where rolname = 'liquibase') then 
			create role liquibase with 
				nosuperuser
				nocreatedb
				nocreaterole
				password 'liquibase'
				inherit
					login
					noreplication
					nobypassrls;
		end if;

		--Autorizações
	    grant connect on database postgres 
	       to liquibase;
    	
	 	grant usage, create on schema contas_pagar
	       to liquibase;
	
	 	grant select, insert, update, delete on all tables 
	       in schema contas_pagar
	       to liquibase;
	
	    --Defaults alterados para não necessitar conceder privilégios sempre que criar uma tabela/view
		alter default privileges in schema contas_pagar grant select, insert, update, delete
	       on tables 
	       to liquibase;
	
		grant usage on all sequences 
	       in schema contas_pagar
	       to liquibase;
	
	    --Defaults alterados para não necessitar conceder privilégios sempre que criar sequences
		alter default privileges 
	       in schema contas_pagar grant usage
	       on sequences to liquibase;

	    grant suser_mgm to liquibase;
	end 
$$;
