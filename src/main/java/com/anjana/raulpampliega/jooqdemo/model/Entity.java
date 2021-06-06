package com.anjana.raulpampliega.jooqdemo.model;

import com.anjana.core.enums.State;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Lombok
@Getter
@Setter
@NoArgsConstructor
// JavaX
@javax.persistence.Entity
@Table(name = "entity")
public class Entity {

  @Enumerated(EnumType.STRING)
  @Column(name = "module")
  private ModuleType module;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sc_pk_entity_seq")
  @SequenceGenerator(name = "sc_pk_entity_seq", sequenceName = "sc_pk_entity", allocationSize = 1)
  @Column(name = "id_entity")
  private Integer id;

  @Column(name = "name")
  private String name;

  @Column(name = "organizational_unit")
  private String organizationalUnit;

  @Column(name = "start_date")
  private Instant startDate; // Approval date

  @Column(name = "last_modified_date")
  private Instant lastModifiedDate; // last modification, disabling included

  @Column(name = "validity_date")
  private Instant validityDate; // approval or change state date (last date of validation workflow)

  @Column(name = "create_user")
  private String creator;

  @Enumerated(EnumType.STRING)
  @Column(name = "state")
  private State state;

  @Column(name = "id_parent")
  private Integer idParent;

  @Column(name = "object_sub_type")
  private String objectSubType;

  @Column(name = "version")
  private Integer version;

  @Column(name = "form_saved")
  private Boolean formSaved = false;

  @PrePersist
  @PreUpdate
  void onPersist() {
    this.setLastModifiedDate(Instant.now());
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Entity)) {
      return false;
    }
    Entity entity = (Entity) o;
    return getId().equals(entity.getId()) &&
        getName().equals(entity.getName()) &&
        getObjectSubType().equals(entity.getObjectSubType());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getName(), getObjectSubType());
  }
}
