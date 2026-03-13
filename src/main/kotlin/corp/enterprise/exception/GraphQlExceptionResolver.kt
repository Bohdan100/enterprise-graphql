package corp.enterprise.exception

import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import graphql.schema.DataFetchingEnvironment
import org.springframework.dao.DataAccessException
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.graphql.execution.ErrorType
import org.springframework.stereotype.Component

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
            else -> {
                builder.errorType(ErrorType.INTERNAL_ERROR)
                    .message("Internal Server Error: ${ex.message}")
                    .build()
            }
        }
    }
}