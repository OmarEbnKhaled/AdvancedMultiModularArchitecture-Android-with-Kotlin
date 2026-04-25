# detekt

## Metrics

* 12 number of properties

* 6 number of functions

* 2 number of classes

* 2 number of packages

* 5 number of kt files

## Complexity Report

* 203 lines of code (loc)

* 163 source lines of code (sloc)

* 101 logical lines of code (lloc)

* 33 comment lines of code (cloc)

* 11 cyclomatic complexity (mcc)

* 5 cognitive complexity

* 2 number of total code smells

* 20% comment source ratio

* 108 mcc per 1,000 lloc

* 19 code smells per 1,000 lloc

## Findings (2)

### naming, FunctionMaxLength (2)

Function names should not be longer than the maximum set in the project configuration.

[Documentation](https://detekt.dev/docs/rules/naming#functionmaxlength)

* src/main/java/com/minafarid/advancedmultimodulararchitecture/MainActivity.kt:41:7
```
Function names should be at most 30 characters long.
```
```kotlin
38     print(counter)
39   }
40 
41   fun mainMainMainMainMainMainMainMainMainMainMainMainMainMainMainMain() {
!!       ^ error
42   }
43 }
44 

```

* src/main/java/com/minafarid/advancedmultimodulararchitecture/ui/theme/Theme.kt:37:5
```
Function names should be at most 30 characters long.
```
```kotlin
34 
35 @Composable
36 @Suppress("FunctionNaming")
37 fun AdvancedMultiModularArchitectureTheme(
!!     ^ error
38   darkTheme: Boolean = isSystemInDarkTheme(),
39   // Dynamic color is available on Android 12+
40   dynamicColor: Boolean = true,

```

generated with [detekt version 1.23.3](https://detekt.dev/) on 2026-04-25 14:01:19 UTC
