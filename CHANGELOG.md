# Change Log
All notable changes to this project will be documented in this file. This project
adheres to [Semantic Versioning](http://semver.org/). The change log file consists
of sections listing each version and the date they were released along with what
was added, changed, deprecated, removed, fix and security fixes.

- Added - Lists new features
- Changed - Lists changes in existing functionality
- Deprecated -  Lists once-stable features that will be removed in upcoming releases
- Removed - Lists deprecated features removed in this release
- Fixed - Lists any bug fixes
- Security - Lists security fixes to security vulnerabilities

## [Unreleased]

## [0.9.5] - 2017-04-21
### Added
- Added snake yaml external module

## [0.9.4] - 2017-03-20
### Fixed
- Issue where log4j MDC was being used instead of the slf4j MDC

## [0.9.3] - 2017-03-18
### Changes
- Insured that @Discoverable annotation is discarded by the compiler
- Changed checkstyle file name to testify_checkstyle.xml
- Updated checkstyle configuration to fix formatting issue with right curly bracket

## [0.9.2] - 2017-03-13
### Added
- CONTRIBUTING.md and RELEASING.md documentation files

### Changed
- Shaded test-logger module to avoid conflicts

## [0.9.1] - 2017-03-05
### Added
- External shaded libraries
- Useful tools (service descriptor generator and test logging tools)

### Changed
- Moved styles to coding-conventions module
- Moved maven resource shade transformer implementation to its own module (shade-plugin-extention)

## [0.9.0] - 2017-02-16
### Added
- Initial commit.
- Custom checkstyle configuration
- Java header style configuration
- License text file used to check file header against
- Maven shade ResourceTransformer implementation that addresses [MSHADE-182](https://issues.apache.org/jira/browse/MSHADE-182)

