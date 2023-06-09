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
package com.zyntaxmind.reactive.graphql.exception.handler;

import java.util.Collections;
import java.util.List;
import java.util.Map;
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

  /***
   * {@inheritDoc}
   */
  @Override
  public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {
    final boolean isBaseException = exception instanceof GraphQLBaseException;
    
    return Mono.just(
        Collections.singletonList(
            toGraphQLError(
                environment,
                exception.getMessage(), 
                isBaseException ? ((GraphQLBaseException) exception).getExtensions() : Collections.emptyMap(),
                isBaseException ? ((GraphQLBaseException) exception).getErrorType() : ErrorType.INTERNAL_ERROR
          )));
  }

  /***
   * This method is used to build {@link GraphQLError} with below parameters.
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
}
