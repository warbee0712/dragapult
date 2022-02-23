package dragapult.gradle.log

import org.slf4j.Logger

inline fun Logger.trace(body: () -> String) {
    if (isTraceEnabled) trace(body())
}

inline fun Logger.debug(body: () -> String) {
    if (isDebugEnabled) debug(body())
}

inline fun Logger.info(body: () -> String) {
    if (isInfoEnabled) info(body())
}

inline fun Logger.warn(body: () -> String) {
    if (isWarnEnabled) warn(body())
}

inline fun Logger.error(body: () -> String) {
    if (isErrorEnabled) error(body())
}