package corp.enterprise.controller

import org.springframework.stereotype.Controller
import org.springframework.graphql.data.method.annotation.QueryMapping
import corp.enterprise.repository.ProjectRepository
import corp.enterprise.repository.WorkerRepository
import corp.enterprise.model.SystemStats

@Controller
class StatsController(
    private val workerRepository: WorkerRepository,
    private val projectRepository: ProjectRepository
) {
    @QueryMapping
    fun systemStats(): SystemStats {
        return SystemStats(
            totalWorkers = workerRepository.countAllWorkers(),
            totalProjects = projectRepository.countAllProjects(),
            averageProjectImportance = projectRepository.getAverageImportance() ?: 0.0
        )
    }
}