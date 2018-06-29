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
                    <complianceLevel>${java.source-target.version}</complianceLevel>
                    <source>${java.source-target.version}</source>
                    <target>${java.source-target.version}</target>
                    <verbose>true</verbose>
                    <Xlint>ignore</Xlint>
                    <encoding>${project.build.sourceEncoding}</encoding>
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
import io.lenar.easy.log.EasyLoggerNoSpring;
import io.lenar.easy.log.annotations.LogIt;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

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