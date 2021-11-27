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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok
@Data
@NoArgsConstructor
// JavaX
@Entity
@Table(name = "attribute_value", indexes = @Index(name = "fn_object_related", columnList = "id_object, object_sub_type"))
public class AttributeValue implements Serializable {

  private static final long serialVersionUID = -1663764697354383441L;

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sc_pk_attribute_value_seq")
  @SequenceGenerator(
      name = "sc_pk_attribute_value_seq",
      sequenceName = "sc_pk_attribute_value",
      allocationSize = 1)
  @Column(name = "id_attribute_value")
  private Integer id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "id_attribute_definition")
  private AttributeDefinition attributeDefinition;

  @Column(name = "value")
  private String value;

  @Column(name = "id_object")
  private Integer idObject;

  @Column(name = "object_sub_type")
  private String objectSubType;

  @Column(name = "id_template_attribute")
  private Integer idTemplateAtribute;

  @Column(name = "i18n", nullable = true, length = 5)
  private String i18n;
}
