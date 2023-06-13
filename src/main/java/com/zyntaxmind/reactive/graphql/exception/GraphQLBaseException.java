/*******************************************************************************
 *    Copyright 2023  the original author.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *******************************************************************************/
/**
 * 
 */
package com.zyntaxmind.reactive.graphql.exception;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

/**
 * @author dush
 *
 */
public class GraphQLBaseException extends RuntimeException implements GraphQLError {

  private static final long serialVersionUID = 8310307192390228711L;

  private Extension extension;
  
  private ErrorClassification errorType;

  public GraphQLBaseException() {
    
  }
  
  public GraphQLBaseException(ErrorClassification errorType) {
    this.errorType = errorType;
  }

  public GraphQLBaseException(String message, ErrorCode code, ErrorClassification errorType) {
    super(message);
    this.extension = new Extension(code, Instant.now().toString());
    this.errorType = errorType;
  }

  public GraphQLBaseException(String message, ErrorCode code, ErrorClassification errorType, Throwable cause) {
    super(message, cause);
    this.extension = new Extension(code, Instant.now().toString());
    this.errorType = errorType;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public List<SourceLocation> getLocations() {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public ErrorClassification getErrorType() {
    return errorType;
  }
  
  /**
   * {@inheritDoc}
   */
  @Override
  public Map<String, Object> getExtensions() {    
    return new HashMap<>(new ObjectMapper().convertValue(extension, new TypeReference<Map<String, Object>>() {}));
  }
  
  /**
   * @return the extension
   */
  public Extension extension() {
    return extension;
  }
}
