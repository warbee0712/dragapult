package dragapult.core.tooling

import java.util.*
import javax.management.ServiceNotFoundException

inline fun <reified Service> loadService(): Service {
    val javaClass = Service::class.java
    val loader = ServiceLoader.load(javaClass)
    return loader.firstOrNull()
        ?: throw ServiceNotFoundException("Couldn't find service with type ${javaClass.simpleName}")
}

inline fun <reified Service> loadServices(): Iterable<Service> {
    val javaClass = Service::class.java
    return ServiceLoader.load(javaClass)
}