package corp.enterprise.exception

import org.springframework.stereotype.Component
import org.springframework.dao.DataAccessException
import org.springframework.graphql.execution.ErrorType
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.web.method.annotation.HandlerMethodValidationException

import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment


@Component
class GraphQlExceptionResolver : DataFetcherExceptionResolverAdapter() {

    override fun resolveToSingleError(ex: Throwable, env: DataFetchingEnvironment): GraphQLError? {
        val builder = GraphqlErrorBuilder.newError()
            .path(env.executionStepInfo.path)
            .location(env.field.sourceLocation)

        return when (ex) {
            is DataAccessException -> {
                builder.errorType(ErrorType.INTERNAL_ERROR)
                    .message("Database Error: ${ex.mostSpecificCause.message}")
                    .build()
            }

            is NoSuchElementException -> {
                builder.errorType(ErrorType.NOT_FOUND)
                    .message(ex.message ?: "Resource not found")
                    .build()
            }

            is HandlerMethodValidationException -> {
                val validationErrors = ex.allErrors.joinToString(", ") { it.defaultMessage ?: "Invalid value" }
                builder.errorType(ErrorType.BAD_REQUEST)
                    .message("Validation failed: $validationErrors")
                    .build()
            }

            is IllegalArgumentException -> {
                builder.errorType(ErrorType.BAD_REQUEST)
                    .message("Invalid arguments: ${ex.message}")
                    .build()
            }

            is RuntimeException -> {
                val message = ex.message ?: "Unknown error"

                if (message.contains("not found", ignoreCase = true)) {
                    builder.errorType(ErrorType.NOT_FOUND)
                        .message(message)
                        .build()
                } else if (message.contains("Invalid sex", ignoreCase = true)) {
                    builder.errorType(ErrorType.BAD_REQUEST)
                        .message(message)
                        .build()
                } else {
                    builder.errorType(ErrorType.INTERNAL_ERROR)
                        .message("Business logic error: $message")
                        .build()
                }
            }

            else -> {
                builder.errorType(ErrorType.INTERNAL_ERROR)
                    .message("Internal Server Error: ${ex.message}")
                    .build()
            }
        }
    }
}