# Choco Solver kotlin extensions

[![Build Status](https://travis-ci.org/ideaplugins/choco-ktx.svg?branch=master)](https://travis-ci.org/ideaplugins/choco-ktx)
[![Bintray](https://img.shields.io/bintray/v/agomez/maven/choco-ktx.svg?maxAge=2592000)](https://bintray.com/agomez/maven/choco-ktx)
[![Download](https://api.bintray.com/packages/agomez/maven/choco-ktx/images/download.svg)](https://bintray.com/agomez/maven/choco-ktx/_latestVersion)
[![Coverage Status](https://coveralls.io/repos/github/ideaplugins/choco-ktx/badge.svg?branch=master)](https://coveralls.io/github/ideaplugins/choco-ktx?branch=master)
[![Apache 2.0](https://img.shields.io/github/license/ideaplugins/choco-ktx.svg)](http://www.apache.org/licenses/LICENSE-2.0)

## Usage

### Maven

Add the repository

```xml
<repository>
    <url>https://dl.bintray.com/agomez/maven</url>
</repository>
```

Add the dependency

```xml
<dependency>
  <groupId>ar.com.agomez</groupId>
  <artifactId>choco-ktx</artifactId>
  <version>x.y.z</version>
</dependency>
```

### Gradle (groovy version)

Add the repository

```groovy
maven {
    url = 'https://dl.bintray.com/agomez/maven'
}
```

Add the dependency

```groovy
implementation 'ar.com.agomez:choco-ktx:x.y.z'
```

### Gradle (kotlin version)

Add the repository

```kotlin
maven("https://dl.bintray.com/agomez/maven")
```

Add the dependency

```kotlin
implementation("ar.com.agomez:choco-ktx:x.y.z")
```

## Example

### Model extensions

```kotlin
model("my first problem") {
    val x = intVar("X", 0..5)
    val y = intVar("Y", 0..5)
    ((x + y) lt 5).post()
    ((x * 2 - y) le 50).post()
    (((x * 2 - y) le 50) or (x - y ge 0)).post()
    scalar(listOf(2 to x, -1 to y), Operator.GT, 4)
    maximize(x)
    solver.setSearch(Search.inputOrderLBSearch(x, y))
    solver.limitTime(Duration.ofMinutes(2))
    solver.solveAll()
    solver.printStatistics()
}
```

For more examples check the API documentation and the tests.

## License

Choco Solver kotlin extensions is released under the [Apache 2.0 license](LICENSE).

```
Copyright 2018-present by the authors.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at following link.

     http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
