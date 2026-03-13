package corp.enterprise

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class EnterpriseApplication

fun main(args: Array<String>) {
    runApplication<EnterpriseApplication>(*args)
}
