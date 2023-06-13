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

/**
 * @author dush
 *
 * 
 * <br/><br/>
 * This interface provide to extend custom error code enum which required to define in custom exception classes.
 * <br/><br/>
 * enum example.
 * <pre class="code">
 *  public enum UserNotFoundError implements {@link ErrorCode} {
 *    USER_NOT_FOUND,
 *    USERNAME_NOT_FOUND,
 *    EMAIL_NOT_FOUND;
 *  }
 * </pre>
 * 
 * custom exception example.
 * 
 *<pre class="code"> 
 *  public class UserNotFoundException extends {@link NotFoundException} {
 *
 *      public UserNotFoundException() {
 *          super();
 *      }
 *   
 *      public UserNotFoundException(String message, <b>UserNotFoundError</b> code) {
 *          super(message, code);
 *      }
 *   
 *      public UserNotFoundException(String message, <b>UserNotFoundError</b> code, Throwable cause) {
 *          super(message, code, cause);
 *      }
 *  }
 * </pre>
 *
 */
public interface ErrorCode {
  
}
