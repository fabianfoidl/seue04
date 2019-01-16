@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  ue04 startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and UE04_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\ue04-0.0.1-SNAPSHOT.jar;%APP_HOME%\lib\webapp-runner-8.5.11.3.jar;%APP_HOME%\lib\spring-boot-starter-web-2.1.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-data-jpa-2.1.1.RELEASE.jar;%APP_HOME%\lib\mysql-connector-java-8.0.13.jar;%APP_HOME%\lib\spring-boot-starter-thymeleaf-2.1.1.RELEASE.jar;%APP_HOME%\lib\bootstrap-4.2.1.jar;%APP_HOME%\lib\jquery-3.3.1.jar;%APP_HOME%\lib\spring-boot-devtools-2.1.1.RELEASE.jar;%APP_HOME%\lib\springfox-swagger-ui-2.9.2.jar;%APP_HOME%\lib\springfox-swagger2-2.9.2.jar;%APP_HOME%\lib\spring-security-web-5.1.3.RELEASE.jar;%APP_HOME%\lib\spring-security-config-5.1.3.RELEASE.jar;%APP_HOME%\lib\spring-security-core-5.1.3.RELEASE.jar;%APP_HOME%\lib\postgresql-9.4-1206-jdbc42.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-2.1.1.RELEASE.jar;%APP_HOME%\lib\tomcat-embed-jasper-8.5.11.jar;%APP_HOME%\lib\tomcat-embed-websocket-9.0.13.jar;%APP_HOME%\lib\tomcat-embed-core-9.0.13.jar;%APP_HOME%\lib\tomcat-embed-logging-juli-8.5.2.jar;%APP_HOME%\lib\tomcat-jasper-8.5.11.jar;%APP_HOME%\lib\tomcat-jasper-el-8.5.11.jar;%APP_HOME%\lib\tomcat-jsp-api-8.5.11.jar;%APP_HOME%\lib\jcommander-1.48.jar;%APP_HOME%\lib\commons-io-2.5.jar;%APP_HOME%\lib\spring-boot-starter-json-2.1.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-aop-2.1.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-jdbc-2.1.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-2.1.1.RELEASE.jar;%APP_HOME%\lib\hibernate-validator-6.0.13.Final.jar;%APP_HOME%\lib\spring-webmvc-5.1.3.RELEASE.jar;%APP_HOME%\lib\spring-web-5.1.4.RELEASE.jar;%APP_HOME%\lib\javax.transaction-api-1.3.jar;%APP_HOME%\lib\jaxb-api-2.3.1.jar;%APP_HOME%\lib\hibernate-core-5.3.7.Final.jar;%APP_HOME%\lib\spring-data-jpa-2.1.3.RELEASE.jar;%APP_HOME%\lib\spring-aspects-5.1.3.RELEASE.jar;%APP_HOME%\lib\thymeleaf-spring5-3.0.11.RELEASE.jar;%APP_HOME%\lib\thymeleaf-extras-java8time-3.0.2.RELEASE.jar;%APP_HOME%\lib\popper.js-1.14.3.jar;%APP_HOME%\lib\spring-boot-autoconfigure-2.1.1.RELEASE.jar;%APP_HOME%\lib\spring-boot-2.1.1.RELEASE.jar;%APP_HOME%\lib\springfox-swagger-common-2.9.2.jar;%APP_HOME%\lib\springfox-spring-web-2.9.2.jar;%APP_HOME%\lib\swagger-models-1.5.20.jar;%APP_HOME%\lib\swagger-annotations-1.5.20.jar;%APP_HOME%\lib\springfox-schema-2.9.2.jar;%APP_HOME%\lib\springfox-spi-2.9.2.jar;%APP_HOME%\lib\springfox-core-2.9.2.jar;%APP_HOME%\lib\guava-20.0.jar;%APP_HOME%\lib\classmate-1.4.0.jar;%APP_HOME%\lib\spring-plugin-metadata-1.2.0.RELEASE.jar;%APP_HOME%\lib\spring-plugin-core-1.2.0.RELEASE.jar;%APP_HOME%\lib\HikariCP-3.2.0.jar;%APP_HOME%\lib\spring-data-commons-2.1.3.RELEASE.jar;%APP_HOME%\lib\thymeleaf-3.0.11.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-logging-2.1.1.RELEASE.jar;%APP_HOME%\lib\logback-classic-1.2.3.jar;%APP_HOME%\lib\log4j-to-slf4j-2.11.1.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.25.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\mapstruct-1.2.0.Final.jar;%APP_HOME%\lib\spring-context-5.1.4.RELEASE.jar;%APP_HOME%\lib\spring-aop-5.1.4.RELEASE.jar;%APP_HOME%\lib\spring-orm-5.1.3.RELEASE.jar;%APP_HOME%\lib\spring-jdbc-5.1.3.RELEASE.jar;%APP_HOME%\lib\spring-tx-5.1.3.RELEASE.jar;%APP_HOME%\lib\spring-beans-5.1.4.RELEASE.jar;%APP_HOME%\lib\spring-expression-5.1.4.RELEASE.jar;%APP_HOME%\lib\spring-core-5.1.4.RELEASE.jar;%APP_HOME%\lib\javax.annotation-api-1.3.2.jar;%APP_HOME%\lib\tomcat-embed-el-9.0.13.jar;%APP_HOME%\lib\ecj-4.5.1.jar;%APP_HOME%\lib\tomcat-util-scan-8.5.11.jar;%APP_HOME%\lib\tomcat-api-8.5.11.jar;%APP_HOME%\lib\tomcat-servlet-api-8.5.11.jar;%APP_HOME%\lib\tomcat-util-8.5.11.jar;%APP_HOME%\lib\tomcat-juli-8.5.11.jar;%APP_HOME%\lib\tomcat-el-api-8.5.11.jar;%APP_HOME%\lib\snakeyaml-1.23.jar;%APP_HOME%\lib\jackson-datatype-jdk8-2.9.7.jar;%APP_HOME%\lib\jackson-datatype-jsr310-2.9.7.jar;%APP_HOME%\lib\jackson-module-parameter-names-2.9.7.jar;%APP_HOME%\lib\jackson-databind-2.9.7.jar;%APP_HOME%\lib\validation-api-2.0.1.Final.jar;%APP_HOME%\lib\hibernate-commons-annotations-5.0.4.Final.jar;%APP_HOME%\lib\jboss-logging-3.3.2.Final.jar;%APP_HOME%\lib\aspectjweaver-1.9.2.jar;%APP_HOME%\lib\javax.activation-api-1.2.0.jar;%APP_HOME%\lib\javax.persistence-api-2.2.jar;%APP_HOME%\lib\ognl-3.1.12.jar;%APP_HOME%\lib\javassist-3.23.1-GA.jar;%APP_HOME%\lib\byte-buddy-1.8.17.jar;%APP_HOME%\lib\antlr-2.7.7.jar;%APP_HOME%\lib\jandex-2.0.5.Final.jar;%APP_HOME%\lib\dom4j-2.1.1.jar;%APP_HOME%\lib\jackson-annotations-2.9.5.jar;%APP_HOME%\lib\spring-jcl-5.1.4.RELEASE.jar;%APP_HOME%\lib\tomcat-annotations-api-9.0.13.jar;%APP_HOME%\lib\jackson-core-2.9.7.jar;%APP_HOME%\lib\attoparser-2.0.5.RELEASE.jar;%APP_HOME%\lib\unbescape-1.1.6.RELEASE.jar;%APP_HOME%\lib\logback-core-1.2.3.jar;%APP_HOME%\lib\log4j-api-2.11.1.jar

@rem Execute ue04
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %UE04_OPTS%  -classpath "%CLASSPATH%" com.se.ue04.Ue04Application %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable UE04_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%UE04_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
