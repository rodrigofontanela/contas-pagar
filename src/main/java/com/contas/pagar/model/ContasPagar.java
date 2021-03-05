package com.contas.pagar.model;

import com.contas.pagar.model.specification.BasicSpecification;
import com.contas.pagar.model.validation.BusinessRuleValidator;
import com.contas.pagar.model.validation.EntityBuilder;
import com.contas.pagar.repository.ContasPagarRepository;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;

import static com.contas.pagar.database.DatabaseSpecifications.DatabaseContasPagar.Schemas;
import static com.contas.pagar.database.DatabaseSpecifications.DatabaseContasPagar.Tables;
import static java.util.Objects.isNull;

@ToString
@Getter
@Entity
@Table(schema = Schemas.ContasPagar.NAME, name = Tables.ContasPagar.TABLE_NAME)
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
@SequenceGenerator(schema = Schemas.ContasPagar.NAME, name = Tables.ContasPagar.SEQUENCE_NAME, sequenceName = Tables.ContasPagar.SEQUENCE_NAME, allocationSize = 1)
public class ContasPagar extends AbstractEntity<Long> {

    @Id
    @EqualsAndHashCode.Include
    @Column(updatable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Tables.ContasPagar.SEQUENCE_NAME)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 200, message = "Valor inválido para o nome. Tamanho máximo deve ser {value}.")
    private String nome;

    @NotNull(message = "O valor original é obrigatório")
    @Column(name = "VALOR_ORIGINAL")
    @Min(value = 0, message = "Valor inválido para o valor original. Deve ser maior ou igual a {value}.")
    private BigDecimal valorOriginal;

    @Column(name = "VALOR_CORRIGIDO")
    private BigDecimal valorCorrigido;

    @NotNull(message = "A data de vencimento é obrigatória")
    @Column(name = "DATA_VENCIMENTO")
    private LocalDate dataVencimento;

    @Column(name = "DATA_PAGAMENTO")
    private LocalDate dataPagamento;

    @Override
    public Long getId() {
        return id;
    }

    public Boolean isVencida() {
        return this.dataVencimento.isBefore(ChronoLocalDate.from(LocalDateTime.now())) && isNull(dataPagamento);
    }

    public static final class Builder extends EntityBuilder<ContasPagar> {

        @Autowired
        private ContasPagarRepository repository;

        private Builder(final ContasPagar entity, final EntityState state) {
            super(entity, state);
        }

        public static Builder create() {
            return new Builder(new ContasPagar(), EntityState.NEW);
        }

        public static Builder from(final ContasPagar entity) {
            return new Builder(entity, EntityState.BUILT);
        }

        public Builder id(final Long id) {
            this.entity.id = id;
            return this;
        }

        public Builder nome(final String nome) {
            this.entity.nome = nome;
            return this;
        }

        public Builder valorOriginal(final BigDecimal valorOriginal) {
            this.entity.valorOriginal = valorOriginal;
            return this;
        }

        public Builder valorCorrigido(final BigDecimal valorCorrigido) {
            this.entity.valorCorrigido = valorCorrigido;
            return this;
        }

        public Builder dataVencimento(final LocalDate dataVencimento) {
            this.entity.dataVencimento = dataVencimento;
            return this;
        }

        public Builder dataPagamento(final LocalDate dataPagamento) {
            this.entity.dataPagamento = dataPagamento;
            return this;
        }
    }
}
