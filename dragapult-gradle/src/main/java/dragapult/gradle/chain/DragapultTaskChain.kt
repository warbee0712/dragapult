package dragapult.gradle.chain

import org.gradle.api.Project

interface DragapultTaskChain {

    fun register(project: Project)

}