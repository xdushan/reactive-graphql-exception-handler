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

import org.springframework.graphql.execution.ErrorType;

/**
 * @author dush
 *
 */
public class NotFoundException extends GraphQLBaseException {
  
  private static final long serialVersionUID = 2118348206384734584L;

  public NotFoundException() {
    super(ErrorType.NOT_FOUND);
  }
  
  public NotFoundException(String message, ErrorCode code) {
    super(message, code, ErrorType.NOT_FOUND);
  }

  public NotFoundException(String message, ErrorCode code, Throwable cause) {
    super(message, code, cause, ErrorType.NOT_FOUND);
  }

}
