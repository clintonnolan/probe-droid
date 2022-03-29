## Probe Droid Project

This is an example project involving probe droids moving around on a map.

### Building
To build, simply use:
```
mvn clean verify
```

This will build the project and run the test suite.

In particular, probe-droid-core/src/test/java/IntegrationTest.java shows how this project is used and puts it through its paces.

### Running
I was intending on having a CLI version, but ran out of time. It needs just a bit more work, since it's supposed to produce a fat JAR with both artifacts (which I'm used to Spring helping with).

The integration tests are how it's currently run.