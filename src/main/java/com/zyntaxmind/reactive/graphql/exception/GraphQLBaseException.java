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
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

/**
 * @author dush
 *
 */
public class GraphQLBaseException  extends RuntimeException implements GraphQLError {

  private static final long serialVersionUID = 8310307192390228711L;

  private Map<String, Object> extensions;
  
  private ErrorClassification errorType;

  public GraphQLBaseException() {
    
  }
  
  public GraphQLBaseException(ErrorClassification errorType) {
    this.errorType = errorType;
  }

  public GraphQLBaseException(String message, ErrorCode code, ErrorClassification errorType) {
    super(message);
    this.toExtensions(code);
    this.errorType = errorType;
  }

  public GraphQLBaseException(String message, ErrorCode code, Throwable cause, ErrorClassification errorType) {
    super(message, cause);
    this.toExtensions(code);
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
   * @return the extensions
   */
  public Map<String, Object> getExtensions() {
    return extensions;
  }

  /***
   * add 'code' and 'timestamp' to extentions response.
   * 
   * @param code
   * @return the extensions
   */
  private Map<String, Object> toExtensions(ErrorCode code){
    extensions  = new HashMap<>();
    extensions.put("code", code);
    extensions.put("timestamp", Instant.now());
    return extensions;
  }
  
}
