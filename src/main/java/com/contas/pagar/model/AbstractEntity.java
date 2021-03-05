package com.contas.pagar.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class AbstractEntity<I> implements IEntity<I> {

    @NotBlank
    @Size(max = 50)
    @Column(name = "CRIADO_POR", updatable = false)
    private String createdBy;

    @NotBlank
    @Column(name = "CRIADO_EM", updatable = false)
    private LocalDateTime createdIn;

    @Size(max = 50)
    @Column(name = "ALTERADO_POR")
    private String updatedBy;

    @Column(name = "ALTERADO_EM")
    private LocalDateTime updatedIn;

    @Version
    @Column(name = "VERSAO")
    private Integer version;

    public AbstractEntity() {

    }

    public void onSave(final String userName) {
        this.createdBy = userName;
    }
}
