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

package com.zyntaxmind.reactive.graphql.exception.handler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.graphql.execution.ErrorType;
import com.zyntaxmind.reactive.graphql.exception.GraphQLBaseException;
import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.schema.DataFetchingEnvironment;
import reactor.core.publisher.Mono;

/**
 * @author dush
 *
 */
public class ReactiveGraphQLExceptionHandler implements DataFetcherExceptionResolver {

  
  private static final Logger log = LoggerFactory.getLogger(ReactiveGraphQLExceptionHandler.class);
  
  private static final String GRAPHQL_BASE_EXCEPTION_LOG = "Exception: message='{}', errorCode='{}', classification='{}', path='{}'";
  
  private static final String GRAPHQL_INTERNAL_EXCEPTION_LOG = "Error: executionId='{}', message='{}', classification='{}', path='{}'";
  
  private static final String GRAPHQL_INTERNAL_EXCEPTION_MSG = "The server encountered an internal error or misconfiguration and was unable to complete the request.";
  
  /***
   * {@inheritDoc}
   */
  @Override
  public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
    if(exception instanceof GraphQLBaseException) {
      logResolvedError(exception, environment);
      return Mono.just(
          Collections.singletonList(
              toGraphQLError(
                  environment, 
                  exception.getMessage(), 
                  ((GraphQLBaseException) exception).getExtensions(), 
                  ((GraphQLBaseException) exception).getErrorType())));
    }
    
    logUnresolvedError(exception, environment);
    return Mono.just(
        Collections.singletonList(
            toGraphQLError(
                environment, 
                GRAPHQL_INTERNAL_EXCEPTION_MSG, 
                Collections.singletonMap("executionId", environment.getExecutionId().toString()), 
                ErrorType.INTERNAL_ERROR)));
  }

  /***
   * This method is used to build {@link GraphQLError} object with below parameters.
   * 
   * @param environment
   * @param message
   * @param extenstions
   * @param errorType
   * 
   * @return GraphQLError object
   */
  private GraphQLError toGraphQLError(DataFetchingEnvironment environment, String message, Map<String, Object> extenstions, ErrorClassification errorType) {
    return GraphqlErrorBuilder.newError(environment)
      .message(message)
      .extensions(extenstions)
      .errorType(errorType)
      .build();
  }

  /**
   * log exceptions that resolved to {@link GraphQLBaseException}.
   * 
   * @param exception
   * @param environment
   */
  private void logUnresolvedError(Throwable exception, DataFetchingEnvironment environment) {
    log.error(
        GRAPHQL_INTERNAL_EXCEPTION_LOG, 
        environment.getExecutionId(),
        exception.getLocalizedMessage(),
        ErrorType.INTERNAL_ERROR,
        environment.getExecutionStepInfo().getPath()
        );
  }

  /**
   * log unresolved exceptions.
   * 
   * @param exception
   * @param environment
   */
  private void logResolvedError(Throwable exception, DataFetchingEnvironment environment) {
    log.debug(
        GRAPHQL_BASE_EXCEPTION_LOG, 
        exception.getMessage(),
        ((GraphQLBaseException) exception).extension().code(),
        ((GraphQLBaseException) exception).getErrorType(),
        environment.getExecutionStepInfo().getPath()
        );
  }
}
