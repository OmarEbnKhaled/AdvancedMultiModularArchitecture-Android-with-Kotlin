# detekt

## Metrics

* 1 number of properties

* 3 number of functions

* 2 number of classes

* 1 number of packages

* 2 number of kt files

## Complexity Report

* 33 lines of code (loc)

* 18 source lines of code (sloc)

* 9 logical lines of code (lloc)

* 6 comment lines of code (cloc)

* 3 cyclomatic complexity (mcc)

* 0 cognitive complexity

* 1 number of total code smells

* 33% comment source ratio

* 333 mcc per 1,000 lloc

* 111 code smells per 1,000 lloc

## Findings (1)

### naming, FunctionMaxLength (1)

Function names should not be longer than the maximum set in the project configuration.

[Documentation](https://detekt.dev/docs/rules/naming#functionmaxlength)

* src/main/java/com/minafarid/login/Test.kt:13:7
```
Function names should be at most 30 characters long.
```
```kotlin
10     print(counter)
11   }
12 
13   fun loginLoginLoginLoginLoginLoginLoginLoginLoginLoginLoginLoginLoginLogi() {
!!       ^ error
14   }
15 }
16 

```

generated with [detekt version 1.23.3](https://detekt.dev/) on 2026-04-25 14:01:17 UTC
