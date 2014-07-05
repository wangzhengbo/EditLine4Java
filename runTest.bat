@echo off

set EDITLINE4JAVA_VERSION=0.1

set CLASSPATH=.\lib\commons-io-2.2.jar
set CLASSPATH=%CLASSPATH%;.\lib\hamcrest-core-1.3.jar
set CLASSPATH=%CLASSPATH%;.\lib\jna-3.5.2.jar
set CLASSPATH=%CLASSPATH%;.\lib\junit-4.11.jar
set CLASSPATH=%CLASSPATH%;.\lib\slf4j-api-1.7.7.jar
set CLASSPATH=%CLASSPATH%;.\lib\slf4j-simple-1.7.7.jar
set CLASSPATH=%CLASSPATH%;.\lib\TCC4Java-0.3.jar
set CLASSPATH=%CLASSPATH%;.\dist\%EDITLINE4JAVA_VERSION%\EditLine4Java-%EDITLINE4JAVA_VERSION%.jar
set CLASSPATH=%CLASSPATH%;.\dist\%EDITLINE4JAVA_VERSION%\EditLine4Java-%EDITLINE4JAVA_VERSION%-lib.jar
set CLASSPATH=%CLASSPATH%;.\dist\%EDITLINE4JAVA_VERSION%\EditLine4Java-%EDITLINE4JAVA_VERSION%-test.jar

set TESTS=AllTests
IF NOT "%1"=="" (
	set TESTS="%1"
)
java -cp %CLASSPATH% -Dfile.encoding=UTF-8 -DEditLineImpl=cn.com.editline.EditLineImpl org.junit.runner.JUnitCore cn.com.editline.%TESTS%
java -cp %CLASSPATH% -Dfile.encoding=UTF-8 -DEditLineImpl=cn.com.editline.EditLineImpl2 org.junit.runner.JUnitCore cn.com.editline.%TESTS%
pause
