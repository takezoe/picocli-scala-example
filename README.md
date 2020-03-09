Example of CLI tool with Scala, Picocli and GraalVM
========

## Install GraalVM

1. Download GraalVM Community Edition from https://github.com/graalvm/graalvm-ce-builds/releases.
2. Add `bin` directory to `PATH`
3. Install `native-image` command
  ```
  $ gu install native-image
  ```

## Build

1. Build the project
  ```
  $ sbt graalvm-native-image:packageBin
  ```
2. Run the command
  ```
  $ ./target/graalvm-native-image/picocli-scala-example
  Missing required parameter: <file>
  Usage: checksum [-hV] [-a=<algorithm>] <file>
  Prints the checksum (MD5 by default) of a file to STDOUT.
        <file>      The file whose checksum to calculate.
    -a, --algorithm=<algorithm>
                    MD5, SHA-1, SHA-256, ...
    -h, --help      Show this help message and exit.
    -V, --version   Print version information and exit.
  ```