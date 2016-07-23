# SimpleString
A simple String manipulation library without any dependencies.


# 简介
 这是一个适配JDK1.5以上的字符串封装的组件。

---
# 示例

## appendString

```
import static SimpleString.appendString;

appendString("mu","sen","boy");

//result=>"musenboy"
```

<!--more-->

## appendArray

```
import static SimpleString.appendArray;

appendArray("mu",new String[]{"sen", "boy"});

//result=>"musenboy"
```
## charAtIndex

```
import static SimpleString.charAtIndex;

charAtIndex("musenboy", 6);

//result=>"o"
```

## stringToArray

```
import static SimpleString.stringToArray;

stringToArray("musenboy");

//result=>["m","u","s","e","n","b","o","y"]
```

## collapseWhitespace

```
import static SimpleString.collapseWhitespace;

collapseWhitespace("mu  sen   boy");

//result=>"mu sen boy"
```

## contains

```
import static SimpleString.contains;

contains("musenboy","musen");
contains("Musenboy","musen",false);

//result=>true
//result=>true
```

## containsAll

```
import static SimpleString.containsAll;

containsAll("musenboy",new String[]{"mu","sen","boy"});

//result=>true
```

## containsAny

```
import static SimpleString.containsAny;

containsAny("musenboy",new String[]{"MU","BOY","title"}, false);

//result=>true
```

# countSubstr

```
import static SimpleString.countSubstr;

countSubstr("musenBOYmusenBOY", "boy", false);

//result=>2
```

## endsWith

```
import static SimpleString.endsWith;

endsWith("musenBoy", "boy", false);

//result=>true
```

## ensureLeft

```
import static SimpleString.ensureLeft;

ensureLeft("musenboy","musen");

ensureLeft("boy","musen");

ensureLeft("musenboy","MUSen",false);

//result=>"musenboy"
//result=>"musenboy"
//result=>"musenboy"
```

## ensureRight

```
import static SimpleString.ensureRight;

ensureRight("musenboy","boy");

ensureRight("musen","boy");

ensureRight("musenboy","Boy",false);

//result=>"musenboy"
//result=>"musenboy"
//result=>"musenboy"
```

## firstChars

```
import static SimpleString.firstChars;

firstChars("musenboy",5);

//result=>musen
```

## headChar

```
import static SimpleString.headChar;

headChar("abcd");

//result=>"a"
```

## unequal

```
import static SimpleString.unequal;

unequal("musenboy","sen");

//result=>true
```

## insertStringAtIndex

```
import static SimpleString.insertStringAtIndex;

insertStringAtIndex("muboy","sen",2);

//result=>"musenboy"
```

## isUpperCase

```
import static SimpleString.isUpperCase;

isUpperCase("ABCDe");

//result=>false
```

## isLowerCase

```
import static SimpleString.isLowerCase;

isLowerCase("abcdE");

//result=>false
```

## lastChars

```
import static SimpleString.lastChars;

lastChars("senboy", 3);

//result=>"boy"
```
## lastIndeOf

```
import static SimpleString.lastIndexOf;

lastIndexOf("muboy","sen");

lastIndexOf("muboy","boy");

//result=>-1
//result=>2
```

## leftTrim

```
import static SimpleString.leftTrim;

leftTrim("   abcd");

//result=>"abcd"
```

## rightTrim

```
import static SimpleString.rightTrim;

rightTrim("abcd    ");

//result=>"abcd"
```

## prepend

```
import static SimpleString.prepend;

prepend("musenboy", "so", "great");

//result=>"sogreatmusenboy"
```

## prependArray

```
import static SimpleString.prependArray;

prependArray("c",new String[]{"a","b"});

//result=>"abc"
```

## removeLeft

```
import static SimpleString.removeLeft;

removeLeft("today","to");

//result=>"day"
```
## removeRight

```
import static SimpleString.removeRight;

removeRight("today","day");

//result=>"to"
```

## removeNonWords

```
import static SimpleString.removeNonWords;

removeNonWords("today is 真的 hot");

//result=>"todayishot"
```

## removeSpaces

```
import static SimpleString.removeSpaces;

removeSpaces(" mu sen boy ");

//result=>"musenboy"
```

## replaceString

```
import static SimpleString.replaceString;

replaceString("goodnews", "news","boy",false);

//result=>"goodboy"
```

## reverse

```
import static SimpleString.reverse;

reverse("abcde");

//result=>"edcba"
```

## words

```
import static SimpleString.words

words("my name is mu sen boy");

//result=>["my","name","is","mu","sen","boy"]
```
