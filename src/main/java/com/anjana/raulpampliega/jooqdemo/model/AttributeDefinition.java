/*
 * Copyright (c) 2020 Anjana Data SL
 *
 * Licensed under the Anjana Data License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at contacting email at info@anajandata.com
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.anjana.raulpampliega.jooqdemo.model;

import com.anjana.core.customers.enums.FrontInputType;
import com.anjana.core.customers.enums.ModuleType;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

// Lombok
@Data
@NoArgsConstructor
// JavaX
@Entity
@Table(name = "attribute_definition")
public class AttributeDefinition implements Serializable {

  @Enumerated(EnumType.STRING)
  @Column(name = "module")
  private ModuleType module;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sc_pk_attribute_definition_seq")
  @SequenceGenerator(
      name = "sc_pk_attribute_definition_seq",
      sequenceName = "sc_pk_attribute_definition",
      allocationSize = 1)
  @Column(name = "id_attribute_definition")
  private Integer id;

  @Column(name = "name",unique = true)
  private String name;

  @Column(name = "short_description_translation_key")
  private String shortDescriptionTranslationKey;

  @Column(name = "description")
  private String description;

  @Column(name = "label")
  private String label;

  @Column(name = "label_translation_key")
  private String labelTranslationKey;

  @Column(name = "place_holder")
  private String placeHolder;

  @Column(name = "place_holder_translation_key")
  private String placeHolderTranslationKey;

  @Enumerated(EnumType.STRING)
  @Column(name = "attribute_type")
  private FrontInputType type;

  @Column(name = "start_date")
  private Instant startDate; // Approval date

  @Column(name = "last_modified_date")
  private Instant lastModifiedDate; // last modification, disabling included
  
  @OneToMany
  @LazyCollection(LazyCollectionOption.FALSE)
  @JoinColumn(name = "id_attribute_definition", nullable = false, insertable = false, updatable = false)
  @Fetch(value = FetchMode.SUBSELECT)
  List<AttributeDefinitionValue> attributeDefinitionValues = new ArrayList<>();
}
