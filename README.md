# JaCoCo Kotlin Companion Object Inconsistency Demo

This project demonstrates inconsistencies in the [JaCoCo](https://www.jacoco.org/jacoco/) code coverage library when working with Kotlin `Companion` objects.

## Project Details

- **Languages:** Kotlin, Java
- **Build Tool:** Maven

## Purpose

The goal is to showcase how JaCoCo may report incorrect or unexpected code coverage results for code inside Kotlin `Companion` objects. This can help developers understand and reproduce the issue for further analysis or reporting.

## How to Use

1. Clone this repository.
2. Build the project using Maven:
3. Review the generated JaCoCo coverage report in `target/site/jacoco/index.html`.
4. Observe the coverage results for classes with `Companion` objects.

## Example

The project contains sample Kotlin classes with `Companion` objects and corresponding tests. Compare the coverage results for regular class members and those inside `Companion` objects.

## License

MIT