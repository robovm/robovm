# RoboVM Weaver

This is a Maven plugin that helps with the creation of boilerplate code. Right now, it generates property setters

## Usage

### Java code

First annotate your properties with the new annotation `@IBOutlet`:

```java
public class MyClass {
  @IBOutlet
  private UIWindow prop1;
  
  @IBOutlet("otherProp")
  private UIWindow prop2;
}
```

If you don't specify a value (like in the case of `prop1`) the generated setter will be (following the example) `setProp1:`.
If you specify a value (like we did with `prop2`), the generated setter will be (following the example) `setOtherProp:`.

### Maven

Finally, add this plugin to your dependencies:

```xml
<build>
  <plugins>
    <plugin>
      <groupId>org.robovm</groupId>
      <artifactId>weaver-maven-plugin</artifactId>
      <version>0.1</version>
      <executions>
        <execution>
          <phase>process-classes</phase>
            <goals>
              <goal>process-classes</goal>       <!-- use this goal to weave all your main classes -->
            </goals>
          </execution>
        </executions>
    </plugin>
  </plugins>
</build>
```

## Feedback and help

... is totally welcome! Specially if your Maven fu is stronger than mine, maybe you can help with a better way of doing things, so please fork and do pull requests, or voice your concerns in the issue area!