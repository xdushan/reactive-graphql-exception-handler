# reactive-graphql-exception-handler

This library is used to handle genric errors with spring boot graphql project.

## Setup

Maven
```pom.xml
<repositories>
  <repository>
    <id>open-feed</id>
    <url>https://pkgs.dev.azure.com/zyntaxmind/open-libs/_packaging/open-feed/maven/v1</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.zyntaxmind</groupId>
    <artifactId>reactive-graphql-exception-handler</artifactId>
    <version>1.0.0</version>
  </dependency>
</dependencies>
```
Gradle
```build.gradle
repositories {
  maven { url 'https://pkgs.dev.azure.com/zyntaxmind/open-libs/_packaging/open-feed/maven/v1' }
}

dependencies {
  implementation "com.zyntaxmind:reactive-graphql-exception-handler:1.0.0"
}
```
### Implementation

Add the below bean into your graphQL configuration class.

```Java
@Configuration
public class GraphQLConfig {

  ...

  @Bean
  DataFetcherExceptionResolver exceptionResolver() {
    return new ReactiveGraphQLExceptionHandler();
  }

}
```
These exception classes are availabe to use.
- [BadRequestException](https://github.com/xdushan/reactive-graphql-exception-handler/blob/main/src/main/java/com/zyntaxmind/reactive/graphql/exception/BadRequestException.java)
- [NotFoundException](https://github.com/xdushan/reactive-graphql-exception-handler/blob/main/src/main/java/com/zyntaxmind/reactive/graphql/exception/NotFoundException.java)
- [ForbiddenException](https://github.com/xdushan/reactive-graphql-exception-handler/blob/main/src/main/java/com/zyntaxmind/reactive/graphql/exception/ForbiddenException.java)
- [UnAuthorizedException](https://github.com/xdushan/reactive-graphql-exception-handler/blob/main/src/main/java/com/zyntaxmind/reactive/graphql/exception/UnAuthorizedException.java)
- [InternalException](https://github.com/xdushan/reactive-graphql-exception-handler/blob/main/src/main/java/com/zyntaxmind/reactive/graphql/exception/InternalException.java)

### Customize exception

You can create own custom exception extending one of above exceptions as below.

```java
public class UserNotFoundException extends NotFoundException {

  public UserNotFoundException() {
    super();
  }
  
  public UserNotFoundException(String message, ErrorCode code) {
    super(message, code);
  }
  
  public UserNotFoundException(String message, ErrorCode code, Throwable cause) {
    super(message, code, cause);
  }
}
```

Also you can create own custom exception with custom error code as below.

```Java
public enum UserNotFoundError implements ErrorCode {

  USER_NOT_FOUND,
  
  USERNAME_NOT_FOUND,
  
  EMAIL_NOT_FOUND;
  
}
```
```java
public class UserNotFoundException extends NotFoundException {

  public UserNotFoundException() {
    super();
  }
  
  public UserNotFoundException(String message, UserNotFoundError code) {
    super(message, code);
  }
  
  public UserNotFoundException(String message, UserNotFoundError code, Throwable cause) {
    super(message, code, cause);
  }
}
```
