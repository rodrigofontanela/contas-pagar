-- liquibase formatted sql

-- changeset rodrigo.fontanela:1 context:dev
create table if not exists contas_pagar.contas_pagar (
	id                int8 not null,
	nome              varchar(200) not null,
	valor_original    numeric(14,2) null default 0,
	valor_corrigido   numeric(14,2) null default 0,
	data_vencimento   date not null,
	data_pagamento    date null
);
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
