package corp.enterprise.config

import org.flywaydb.core.Flyway
import org.springframework.boot.flyway.autoconfigure.FlywayProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import javax.sql.DataSource

@Configuration
@EnableConfigurationProperties(FlywayProperties::class)
class FlywayConfig {

    @Bean(initMethod = "migrate")
    fun flyway(dataSource: DataSource, properties: FlywayProperties): Flyway {
        return Flyway.configure()
            .dataSource(dataSource)
            .locations(*properties.locations.toTypedArray())
            .baselineOnMigrate(properties.isBaselineOnMigrate)
            .baselineVersion(properties.baselineVersion)
            .load()
    }
}