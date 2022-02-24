# Dragapult

Dragapult is a versatile tool that allows consuming and generating i18n (internationalization) files.

Currently supported platforms:

- Apple
- Android
- Web (through JSON)

Currently supported source files:

- CSV
- JSON

## Usage

### Utility

The utility may or may not (in the future) require path registered JDK on your device. The target Java version
is **`11`**.

> Please note that the utility requires you to set paths in a way that allows for running the example below. If you
> don't know how to set paths for JVM application refer to this [readme](readme-paths.md)

```bash
dragapult-app --help
dragapult-app consume --help
dragapult-app generate --help
```

### Gradle Plugin

Root `build.gradle` file:

```groovy
buildscript {
    repositories {
        mavenCentral() // mavenLocal() if built locally
    }
    dependencies {
        classpath "io.github.diareuse:dragapult-gradle:1.+"
    }
}
```

Module `build.gradle` file:

```groovy
dragapult {
    archive {
        url = "" // optional
        downloadDir = project.file("path/to/dir") // optional
    }
    app {
        file = project.file("path/to/file")
        type = "csv"
        output = project.file("path/to/dir") // optional
    }
}
```

## Building from Source

### Utility

After executing the below command, you should see `dragapult-app/distributions/dragapult-app.zip`

```bash
./gradlew assembleOutputs
```

### Gradle Plugin

Giving the Gradle Plugin a spin is a little bit cumbersome and requires a little bit more steps. You should start by
building the plugin:

```bash
./gradlew :dragapult-gradle:assemble
```

After that you should be ready to distribute the plugin to your local maven repository:

```bash
./gradlew :dragapult-gradle:publishToMavenLocal
```

Once uploaded to your local repository, you can add this to your project - as defined above - with the `mavenLocal()`
repository.

## Sponsors

### [SKOUMAL s.r.o.](https://www.skoumal.com/)