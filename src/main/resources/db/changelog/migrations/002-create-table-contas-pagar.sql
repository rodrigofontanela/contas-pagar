-- liquibase formatted sql

-- changeset rodrigo.fontanela:1 context:dev splitStatements:false
create table if not exists contas_pagar.contas_pagar (
	id                int8 not null,
	owner			  varchar(50) not null default current_user,
	nome              varchar(200) not null,
	valor_original    numeric(14,2) null default 0,
	valor_corrigido   numeric(14,2) null default 0,
	data_vencimento   date not null,
	data_pagamento    date null,
	criado_por        varchar(50) not null,
	criado_em         timestamp not null default current_timestamp,
	alterado_por      varchar(50) not null,
	alterado_em       timestamp not null default current_timestamp,
	versao            int not null default 0,
	constraint pk_contas_pagar primary key (id)
) partition by hash (id);

create table contas_pagar.contas_pagar_hpart_0 partition of contas_pagar.contas_pagar for values with (modulus 5, remainder 0);
create table contas_pagar.contas_pagar_hpart_1 partition of contas_pagar.contas_pagar for values with (modulus 5, remainder 1);
create table contas_pagar.contas_pagar_hpart_2 partition of contas_pagar.contas_pagar for values with (modulus 5, remainder 2);
create table contas_pagar.contas_pagar_hpart_3 partition of contas_pagar.contas_pagar for values with (modulus 5, remainder 3);
create table contas_pagar.contas_pagar_hpart_4 partition of contas_pagar.contas_pagar for values with (modulus 5, remainder 4);

create policy contas_pagar_owner_policy on contas_pagar.contas_pagar using (owner = current_user);
--rollback not required

-- changeset rodrigo.fontanela:2 context:dev
alter table contas_pagar.contas_pagar owner to suser_mgm;
--rollback not required

-- changeset rodrigo.fontanela:3 context:dev
grant all on table contas_pagar.contas_pagar to suser_mgm;
--rollback not required

-- changeset rodrigo.fontanela:4 context:dev
create sequence if not exists contas_pagar.seq_contas_pagar
	increment by 1
	minvalue 1
	maxvalue 9223372036854775807
	start 1
	cache 1
	no cycle;
--rollback not required

-- changeset rodrigo.fontanela:5 context:dev
alter sequence contas_pagar.seq_contas_pagar owner to suser_mgm;
--rollback not required

-- changeset rodrigo.fontanela:6 context:dev
grant all on sequence contas_pagar.seq_contas_pagar to suser_mgm;
--rollback not required
