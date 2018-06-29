[![Maven Central](https://img.shields.io/maven-central/v/io.lenar/easy-log.svg)](https://maven-badges.herokuapp.com/maven-central/io.lenar/easy-log)

# Easy Log for non-Spring projects - example

## pom.xml

### Maven dependency

<code>io.lenar:easy-log</code>.

Version: <code>0.9.7</code> or higher

### AspectJ Maven Plugin

```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.codehaus.mojo</groupId>
                <artifactId>aspectj-maven-plugin</artifactId>
                <version>1.11</version>
                <configuration>
                    <complianceLevel>1.8</complianceLevel>
                    <source>1.8</source>
                    <target>1.8</target>
                    <verbose>true</verbose>
                    <Xlint>ignore</Xlint>
                    <encoding>UTF-8</encoding>
                </configuration>
                <executions>
                    <execution>
                        <id>aspectj-compile</id>
                        <goals>
                            <goal>compile</goal>
                            <goal>test-compile</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        ...
        </plugins>
    </build>
``` 

### Extend EasyLoggerNoSpring

```java
@Aspect
public class MyLogger extends EasyLoggerNoSpring {

    @Around(CLASS_LEVEL_LOGIT_POINTCUT)
    public Object classLog(ProceedingJoinPoint jp, LogIt annotation) throws Throwable {
        return logIt(jp, annotation);
    }

    @Around(METHOD_LEVEL_LOGIT_POINTCUT)
    public Object methodLog(ProceedingJoinPoint jp, LogIt annotation) throws Throwable {
        return logIt(jp, annotation);
    }
}
```

Note: In IntelliJ make sure that you use AJC compiler if this wasn't set automatically.

## LogIt annotation

### LogIt annotation without parameters

```java
    @LogIt
    public Universal bigBang(int numberOfStars, int numberOfBlackHoles) {
        blackHoles = IntStream.range(0, numberOfBlackHoles).boxed()
                .map(item -> new BlackHole(randomName("BlackHole-")))
    ...
```

Log ouptut will look like this

```json
13:36:06.021 [main] INFO  UneasyLogger - 
-> public Universal Universal.bigBang(int numberOfStars, int numberOfBlackHoles)
{
  "numberOfStars": 3,
  "numberOfBlackHoles": 3
}

13:36:06.205 [main] INFO  UneasyLogger - 
Execution/Response time:  162ms
<- Universal Universal.bigBang(int numberOfStars, int numberOfBlackHoles)
{
  "stars": [
    {
      "name": "Star-b90637a4-81bb-4c46-9c05-99ecf2dc0502",
      "type": "RED_GIANT",
      "planets": [
        {
          "name": "Planet-c5308178-4ebe-46c6-a02a-f78d489afc99",
          "haveSatellites": true
        },
        {
          "name": "Planet-1d13015a-a552-4492-8f1f-62e63912c1a3",
          "haveSatellites": true
        }
      ]
    },
    {
      "name": "Star-db998dc1-533a-4663-96c5-088e8be1e87f",
      "type": "WHITE_DWARF",
      "planets": []
    },
    {
      "name": "Star-307f313c-8baf-4baa-b3db-1528caf328bd",
      "type": "RED_DWARF",
      "planets": [
        {
          "name": "Planet-2e198389-3ca8-47bf-916d-c4c4359a0b54",
          "haveSatellites": false
        }
      ]
    }
  ],
  "blackHoles": [
    {
      "name": "BlackHole-b1bd222d-a4d3-456d-af87-ac72e4624cbb"
    },
    {
      "name": "BlackHole-4885c353-2aec-4022-98cc-e497cfb4dec6"
    },
    {
      "name": "BlackHole-1697001a-70e2-405e-9082-383b2d33dd80"
    }
  ],
  "dateOfCreation": "Jun 29, 2018 1:36:06 PM"
}
```

### LogIt annotation with parameters - example

```java
    @LogIt(label = "DEBUG BIG BANG ISSUE", level = Level.DEBUG, ignoreParameters = {"numberOfBlackHoles"}, maskFields = {"name"})
    public Universal bigBang(int numberOfStars, int numberOfBlackHoles) {
        blackHoles = IntStream.range(0, numberOfBlackHoles).boxed()
                .map(item -> new BlackHole(randomName("BlackHole-")))
```

Log output

```json
13:43:23.259 [main] DEBUG UneasyLogger -
DEBUG BIG BANG ISSUE
-> public Universal Universal.bigBang(int numberOfStars, int numberOfBlackHoles<NOT_LOGGED>)
{
  "numberOfStars": 3
}

13:43:23.259 [main] DEBUG UneasyLogger -
Execution/Response time:  0ms
DEBUG BIG BANG ISSUE
<- Universal Universal.bigBang(int numberOfStars, int numberOfBlackHoles<NOT_LOGGED>)
{
  "stars": [
    {
      "planets": [],
      "name": "XXXMASKEDXXX",
      "type": "RED_GIANT"
    },
    {
      "planets": [
        {
          "haveSatellites": true,
          "name": "XXXMASKEDXXX"
        }
      ],
      "name": "XXXMASKEDXXX",
      "type": "WHITE_DWARF"
    },
    {
      "planets": [],
      "name": "XXXMASKEDXXX",
      "type": "SUPERGIANT"
    }
  ],
  "blackHoles": [
    {
      "name": "XXXMASKEDXXX"
    },
    {
      "name": "XXXMASKEDXXX"
    },
    {
      "name": "XXXMASKEDXXX"
    }
  ],
  "dateOfCreation": "Jun 29, 2018 1:43:23 PM"
}

```

