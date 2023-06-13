/*******************************************************************************
 * Copyright 2023 the original author.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *******************************************************************************/

package com.zyntaxmind.reactive.graphql.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.execution.ExecutionId;

/**
 * @author dush
 * @date Jun 13, 2023
 *
 */
final public class Extension {

  @JsonInclude(Include.NON_NULL)
  String code;

  @JsonInclude(Include.NON_NULL)
  String executionId;
  
  String timestamp = Instant.now().toString();

  Map<String, Object> additionalExt = new HashMap<>();

  public Extension() {
    super();
  }
  
  public Extension(ErrorCode code, ExecutionId executionId, Instant timestamp) {
    super();
    this.code = code != null ? code.toString() : null;
    this.executionId = executionId != null ? executionId.toString() : null;
  }

  @JsonAnyGetter
  public Map<String, Object> getAdditionalExt() {
    return additionalExt;
  }

  @JsonAnySetter
  public void setAdditionalExt(String name, Object value) {
    additionalExt.put(name, value);
  }

  /**
   * @return the code
   */
  public String getCode() {
    return code;
  }

  /**
   * @param code the code to set
   */
  public void setCode(ErrorCode code) {
    this.code = code != null ? code.toString() : "";
  }

  /**
   * @return the timestamp
   */
  public String getTimestamp() {
    return timestamp;
  }

  /**
   * @param timestamp the timestamp to set
   */
  public void setTimestamp(String timestamp) {
    this.timestamp = timestamp != null ? timestamp : "";
  }

  /**
   * @return the executionId
   */
  public String getExecutionId() {
    return executionId;
  }

  /**
   * @param executionId the executionId to set
   */
  public void setExecutionId(ExecutionId executionId) {
    this.executionId = executionId !=null ? executionId.toString() : null;
  }
  
  public HashMap<String, Object> toMap() {
    return new HashMap<>(new ObjectMapper().convertValue(this, new TypeReference<Map<String, Object>>() {}));
  }
}
