package com.anjana.raulpampliega.jooqdemo.model;

import com.anjana.core.customers.enums.State;
import com.anjana.core.exception.AnjanaException;
import com.anjana.core.utils.ARISerializer;
import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
// JavaX
@Entity
@Table(name = "relationship")
public class RelationShip implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sc_pk_relationship_seq")
  @SequenceGenerator(
      name = "sc_pk_relationship_seq",
      sequenceName = "sc_pk_relationship",
      allocationSize = 1)
  @Column(name = "id_relationship")
  private Integer id;

  @Column(name = "name")
  private String name;

  // Id source object
  @Column(name = "id_source")
  private String source;

  @Column(name = "source_type")
  private String sourceType;

  @Enumerated(EnumType.STRING)
  @Column(name = "source_module")
  ModuleType sourceModule;

  // Id destination object
  @Column(name = "id_destination")
  private String destination;

  @Column(name = "destination_type")
  private String destinationType;

  @Enumerated(EnumType.STRING)
  @Column(name = "destination_module")
  ModuleType destinationModule;

  @Column(name = "object_sub_type")
  private String objectSubType;

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

  @Column(name = "form_saved")
  private Boolean formSaved;

  @Enumerated(EnumType.STRING)
  @Column(name = "module")
  private ModuleType module;

  @Column(name = "id_parent")
  private Integer idParent;

  @PrePersist
  @PreUpdate
  void onPersist() {
    this.setLastModifiedDate(Instant.now());
  }

  public Integer getSourceID() {
    if (Objects.isNull(this.source)) {
      return null;
    }

    return ARISerializer.getObjectId(this.source);

  }

  public Object getDestinationID() {
    if (Objects.isNull(this.destination)) {
      return null;
    }
    try {
      return ARISerializer.getObjectId(this.destination);
    } catch (AnjanaException e) {
      // Only for adherence have one element, the username without id
      return this.destination;
    }
  }

  public String getSourceName() {
    if (Objects.isNull(this.source)) {
      return null;
    }

    return ARISerializer.getObjectName(this.source);
  }

  public String getDestinationName() {
    if (Objects.isNull(this.destination)) {
      return null;
    }
    // In adherence relationship only save the name of the user
    try {
      return ARISerializer.getObjectName(this.destination);
    } catch (AnjanaException e) {
      if (Objects.nonNull(this.destination)) {
        return this.destination;
      }
    }

    return null;
  }
}