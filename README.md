# Choco Solver kotlin extensions

[![Build Status](https://travis-ci.org/ideaplugins/choco-ktx.svg?branch=master)](https://travis-ci.org/ideaplugins/choco-ktx)
[![Bintray](https://img.shields.io/bintray/v/agomez/maven/choco-ktx.svg)](https://bintray.com/agomez/maven/choco-ktx)
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
    val z = intVar("Z", 5..10)
    ((x + y) lt 5).post()
    ((2 * x - y) le 50).post()
    (((x * 2 - y) le 50) or (x - y ge 0)).post()
    scalar { (-2 * x - y + z) ge 0 }
    maximize(x)
    solver.setSearch(Search.inputOrderLBSearch(x, y))
    solver.limitTime(Duration.ofMinutes(2))
    solver.solveAll()
    solver.printStatistics()
}
```

### N queens problem

```kotlin
model("N queens") {
    val n = 8
    val q = intVarArray("q", n, 0 until n)
    allDifferent(*q).post()
    (0 until n - 1).forEach { i ->
        (i + 1 until n).forEach { j ->
            (q[i] ne (q[j] - (j - i))).post()
            (q[i] ne (q[j] + (j - i))).post()
        }
    }
    solver.setSearch(Search.inputOrderLBSearch(*q))
    solver.showSolutions()
    solver.solveAll()
}
```

### Magic square problem

```kotlin
model("magic square") {
    val n = 3
    val d = n * (n * n + 1) / 2
    val m = intVarMatrix("X", n, n, 1, n * n)
    allDifferent(m.flatMap { it.asIterable() }).post()
    m.forEach {
        sum { it eq d }.post()
    }
    (0 until n).forEach { j ->
        sum { (0 until n).map { i -> m[i][j] } eq d }.post()
    }
    sum { (0 until n).map { i -> m[i][i] } eq d }.post()
    sum { (0 until n).map { i -> m[i][n - i - 1] } eq d }.post()
    solver.showSolutions()
    solver.solveAll()
}
```

### Send more money problem

```kotlin
model("send+more=money") {
    val s = digitNonZero("s")
    val e = digit("e")
    val n = digit("n")
    val d = digit("d")
    val m = digitNonZero("m")
    val o = digit("o")
    val r = digit("r")
    val y = digit("y")
    allDifferent(s, e, n, d, m, o, r, y).post()
    scalar { (1000 * s + 100 * e + 10 * n + d + 1000 * m + 100 * o + 10 * r + e - 10000 * m - 1000 * o - 100 * n - 10 * e - y) eq 0 }.post()
    solver.showSolutions()
    solver.solveAll()
}
```

### Send more money problem (another approach)

```kotlin
model("send+more=money") {
    val s = digitNonZero("s")
    val e = digit("e")
    val n = digit("n")
    val d = digit("d")
    val m = digitNonZero("m")
    val o = digit("o")
    val r = digit("r")
    val y = digit("y")
    val x = boolVarArray("X", 3)
    allDifferent(s, e, n, d, m, o, r, y).post()
    ((d + e) eq (y + (x[0] * 10))).post()
    ((x[0] + n + r) eq (e + (x[1] * 10))).post()
    ((x[1] + e + o) eq (n + (x[2] * 10))).post()
    ((x[2] + s + m) eq (o + (m * 10))).post()
    solver.setSearch(Search.inputOrderLBSearch(s, e, n, d, m, o, r, y))
    solver.showSolutions()
    solver.solveAll()
}
```

### Magic sequence

```kotlin
model("magic sequence") {
    val n = 10
    val x = intVarArray("X", n, 0 until n)
    globalCardinality(x, 0 until n, x).post()
    solver.showSolutions()
    solver.solveAll()
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
