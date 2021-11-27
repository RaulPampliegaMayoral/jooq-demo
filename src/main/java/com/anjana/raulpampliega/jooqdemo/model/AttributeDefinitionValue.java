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

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok
@Data
@NoArgsConstructor
// JavaX
@Entity
@Table(
    name = "attribute_definition_value",
    uniqueConstraints = {
      @UniqueConstraint(columnNames = {"id_attribute_definition_value", "label_select"}),
      @UniqueConstraint(columnNames = {"id_attribute_definition_value", "value"})
    })
public class AttributeDefinitionValue implements Serializable {

  @Id
  @GeneratedValue(
      strategy = GenerationType.SEQUENCE,
      generator = "sc_pk_attribute_definition_value_seq")
  @SequenceGenerator(
      name = "sc_pk_attribute_definition_value_seq",
      sequenceName = "sc_pk_attribute_definition_value",
      allocationSize = 1)
  @Column(name = "id_attribute_definition_value")
  private Integer id;

  @Column(name = "id_attribute_definition")
  private Integer idAttributeDefinition;

  @Column(name = "label_select")
  private String labelSelect;

  @Column(name = "value")
  private String value;
}
