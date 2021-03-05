package com.contas.pagar.database;

public class DatabaseSpecifications {
    public static class DatabaseContasPagar {
        public static final String CONNECTION_URL = "jdbc:postgresql://localhost:5432/contas_pagar";
        public static final String DRIVE_CLASS_NAME = "org.postgresql.Driver";

        public static class Schemas {
            public static class ContasPagar {
                public static final String NAME = "contas_pagar";
            }

            public static class Migrations {
                public static final String NAME = "migrations";
            }
        }

        public static class Roles {
            public static class System {
                public static final String NAME = "cp_system";
                //Apenas informativo; correto é não estar aqui
                public static final String SECRET = "cp_system";
            }

            public static class Liquibase {
                public static final String NAME = "liquibase";
                //Apenas informativo; correto é não estar aqui
                public static final String SECRET = "liquibase";
            }
        }

        public static class Tables {
            //Apenas informativo
            public static final String DEFAULT_PARTITION_STRATEGY = "HASH";
            public static final Boolean ROW_LEVEL_SECURITY = true;
            public static final Boolean ALLOW_BITMAP_INDEXES = true;

            public static class ContasPagar {
                public static final String TABLE_NAME = "contas_pagar";
                public static final String SEQUENCE_NAME = "seq_contas_pagar";
                public static final String VIEW_NAME = "vw_contas_pagar";
            }
        }
    }
}
