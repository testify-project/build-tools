# Build Tools
[![Build Status](https://travis-ci.org/testify-project/build-tools.svg?branch=develop)](https://travis-ci.org/testify-project/build-tools)
[![Github Releases](https://img.shields.io/github/downloads/testify-project/build-tools/latest/total.svg)]()
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.testifyproject.build-tools/parent/badge.svg?style=flat)](https://maven-badges.herokuapp.com/maven-central/org.testifyproject.build-tools)
[![License](https://img.shields.io/github/license/testify-project/build-tools.svg)](LICENSE)

## Overview
A collection of tools and configuration files for managing builds.

## Learning
- Testify documentation is available [here][docs].
- Take a look at [Test Driven Development][tdd-presentation] presentation.
- Examples code can be found [here][examples].
- Read [Java for Small Team][java-for-small-team] eBook.

## Issue Tracking
Report issues via the [Github Issues][github-issues]. Think you've found a bug?
Please consider submitting a reproduction project via the a new [Github Issue][github-issues-new].

## Building from Source
Testify uses a Maven-based build system. To build from source follow the bellow instructions:

### Prerequisites
- [Git 1.9.1](https://git-scm.com/downloads) or above
- [JDK 8](https://docs.oracle.com/javase/8/docs/technotes/guides/install/install_overview.html) (be sure to set `JAVA_HOME`)
- [Maven 3.0.5](https://maven.apache.org/download.cgi) or above

### Check out sources
```
$ git clone git@github.com:testify-project/build-tools.git
```

or

```
$ git clone https://github.com/testify-project/build-tools.git
```

### Install all Testify jars into your local Maven cache
```
$ mvn install -Dmaven.test.skip
```

### Compile and test and build all jars
```
$ mvn clean install
```

## Staying in Touch
Hit us up on [Gitter][gitter].

## License
The Testify is released under [Apache Software License, Version 2.0](LICENSE).

Enjoy and keep on Testifying!

[github-issues]: https://github.com/testify-project/build-tools/issues
[github-issues-new]: https://github.com/testify-project/build-tools/issues/new
[gitter]: https://gitter.im/testify-project/Lobby