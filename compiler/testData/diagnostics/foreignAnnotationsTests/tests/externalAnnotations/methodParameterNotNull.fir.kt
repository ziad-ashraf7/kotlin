// FILE: ClassWithExternalAnnotatedMembers.java
import org.jetbrains.annotations.NotNull;

public class ClassWithExternalAnnotatedMembers {
    public void method(String s) {
    }

    public void method(@NotNull Integer i) {
    }
}

// FILE: usage.kt
fun test() {
    val instance = ClassWithExternalAnnotatedMembers()
    val i: Int? = null
    instance.<!NONE_APPLICABLE!>method<!>(i)

    val s: String? = null
    instance.method(s)

    val b: Boolean? = null
    instance.<!NONE_APPLICABLE!>method<!>(b)

    instance.method(null)
}

// FILE: annotations.xml
<root>
    <item name='ClassWithExternalAnnotatedMembers void method(java.lang.String) 0'>
        <annotation name='org.jetbrains.annotations.NotNull'/>
    </item>
</root>