## mybatis generator

#### 代码地址

https://github.com/Xueruiquan/mybatis-generator

#### 简介

- Mybatis是现在比较常用的持久层框架。用起来比较简单。MyBatis有两种实现方法，分别为基于注解和基于映射文件。当需要操作的实体类较多时，逐个编写基于注解或基于映射文件的CURD耗时长且容易出错，使用MyBatis Generator可以保证CRUD的正确性，以及节省大量的时间。逆向是代表了从数据库到java代码。

- 准备：

  1. **依赖**

  - mybatis-generator-core.jar
  - mysql-connector-java.jar
  - mybatis-generator-maven-plugin

  2. **generatorConfig.xml配置**

  - 配置数据库连接地址及账号密码
  - 生成的Model类存放位置
  - 生成的映射文件存放位置
  - 指定要生成的表

  3. **解决方案**

  - insert(), 新增。
  - deleteByPrimaryKey(), 根据主键删除。
  - updateByPrimaryKeySelective(),根据主键更新，对字段进行判断（如果为null忽略更新）。
  - updateByPrimaryKey(), 根据主键更新，对注入的字段全部更新。
  - selectByPrimaryKey(), 根据主键查询。

#### pom.xml文件配置

```xml
<!-- 添加Mybatis-Generator插件 -->
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
        </plugin>
        <!-- Mybatis-Generator 插件 -->
        <plugin>
            <groupId>org.mybatis.generator</groupId>
            <artifactId>mybatis-generator-maven-plugin</artifactId>
            <version>1.3.2</version>
            <executions>
                <execution>
                    <id>mybatis-generator</id>
                    <phase>deploy</phase>
                    <goals>
                        <goal>generate</goal>
                    </goals>
                </execution>
            </executions>
            <configuration>
                <!-- Mybatis-Generator 工具配置文件的位置 -->
                <configurationFile>src/main/resources/mybatis-generator/generatorConfig.xml</configurationFile>
                <verbose>true</verbose>
                <overwrite>true</overwrite>
            </configuration>
            <dependencies>
                <dependency>
                    <groupId>mysql</groupId>
                    <artifactId>mysql-connector-java</artifactId>
                    <version>5.1.46</version>
                </dependency>
                <dependency>
                    <groupId>org.mybatis.generator</groupId>
                    <artifactId>mybatis-generator-core</artifactId>
                    <version>1.3.2</version>
                </dependency>
            </dependencies>
        </plugin>

    </plugins>
</build>
```

#### generatorConfig.xml文件配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">
<!-- 配置生成器 -->
<generatorConfiguration>
    <!--执行generator插件生成文件的命令： call mvn mybatis-generator:generate -e -->
    <!-- 引入配置文件 -->
    <properties resource="application.properties"/>
    <!--classPathEntry:数据库的JDBC驱动,换成你自己的驱动位置 可选 -->
    <!--<classPathEntry location="D:generator_mybatismysql-connector-java-5.1.24-bin.jar" /> -->

    <!-- 一个数据库一个context -->
    <!--defaultModelType="flat" 大数据字段，不分表 -->
    <context id="MysqlTables" targetRuntime="MyBatis3Simple" defaultModelType="flat">
        <!-- 自动识别数据库关键字，默认false，如果设置为true，根据SqlReservedWords中定义的关键字列表；
        一般保留默认值，遇到数据库关键字（Java关键字），使用columnOverride覆盖 -->
        <property name="autoDelimitKeywords" value="true" />
        <!-- 生成的Java文件的编码 -->
        <property name="javaFileEncoding" value="utf-8" />
        <!-- beginningDelimiter和endingDelimiter：指明数据库的用于标记数据库对象名的符号，比如ORACLE就是双引号，MYSQL默认是`反引号； -->
        <property name="beginningDelimiter" value="`" />
        <property name="endingDelimiter" value="`" />

        <!-- 格式化java代码 -->
        <property name="javaFormatter" value="org.mybatis.generator.api.dom.DefaultJavaFormatter"/>
        <!-- 格式化XML代码 -->
        <property name="xmlFormatter" value="org.mybatis.generator.api.dom.DefaultXmlFormatter"/>
        <plugin type="org.mybatis.generator.plugins.SerializablePlugin" />

        <plugin type="org.mybatis.generator.plugins.ToStringPlugin" />

        <!-- 注释 -->
        <commentGenerator >
            <property name="suppressAllComments" value="false"/><!-- 是否取消注释 -->
            <property name="suppressDate" value="true" /> <!-- 是否生成注释代时间戳-->
        </commentGenerator>

        <!-- jdbc连接 -->
        <jdbcConnection driverClass="${spring.datasource.driver-class-name}" connectionURL="${spring.datasource.url}" userId="${spring.datasource.username}" password="${spring.datasource.password}" />
        <!-- 类型转换 -->
        <javaTypeResolver>
            <!-- 是否使用bigDecimal， false可自动转化以下类型（Long, Integer, Short, etc.） -->
            <property name="forceBigDecimals" value="false"/>
        </javaTypeResolver>

        <!-- 生成实体类地址 -->
        <javaModelGenerator targetPackage="com.huiyuanai.mybatisgenerator.entity" targetProject="${mybatis.project}" >
            <property name="enableSubPackages" value="false"/>
            <property name="trimStrings" value="true"/>
        </javaModelGenerator>
        <!-- 生成mapxml文件 -->
        <sqlMapGenerator targetPackage="mapper" targetProject="${mybatis.resources}" >
            <property name="enableSubPackages" value="false" />
        </sqlMapGenerator>
        <!-- 生成mapxml对应client，也就是接口dao -->
        <javaClientGenerator targetPackage="com.huiyuanai.mybatisgenerator.dao" targetProject="${mybatis.project}" type="XMLMAPPER" >
            <property name="enableSubPackages" value="false" />
        </javaClientGenerator>
        <!-- table可以有多个,每个数据库中的表都可以写一个table，tableName表示要匹配的数据库表,也可以在tableName属性中通过使用%通配符来匹配所有数据库表,只有匹配的表才会自动生成文件 -->
        <table tableName="user" enableCountByExample="true" enableUpdateByExample="true" enableDeleteByExample="true" enableSelectByExample="true" selectByExampleQueryId="true">
            <property name="useActualColumnNames" value="false" />
            <!-- 数据库表主键 -->
            <generatedKey column="id" sqlStatement="Mysql" identity="true" />
        </table>
        <table tableName="book" enableCountByExample="true" enableUpdateByExample="true" enableDeleteByExample="true" enableSelectByExample="true" selectByExampleQueryId="true">
            <property name="useActualColumnNames" value="false" />
            <!-- 数据库表主键 -->
            <generatedKey column="id" sqlStatement="Mysql" identity="true" />
        </table>
    </context>
</generatorConfiguration>
```



#### application.properties文件配置

```properties
## mapper xml 文件地址
mybatis.mapper-locations=classpath*:mapper/*Mapper.xml

##数据库url
spring.datasource.url=jdbc:mysql://localhost:3306/mybatis-generator?characterEncoding=utf8&useSSL=false&serverTimezone=Asia/Shanghai
##数据库用户名
spring.datasource.username=root
##数据库密码
spring.datasource.password=root
##数据库驱动
spring.datasource.driver-class-name=com.mysql.jdbc.Driver

#Mybatis Generator configuration
#dao类和实体类的位置
mybatis.project =src/main/java
#mapper文件的位置
mybatis.resources=src/main/resources
```

#### 主启动类

```java
@SpringBootApplication
@MapperScan(basePackages = "com.huiyuanai.mybatisgenerator.dao") // 扫描数据访问接口
public class MybatisGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisGeneratorApplication.class, args);
        System.out.println("MybatisGeneratorApplication 启动成功， ｡:.ﾟヽ(｡◕‿◕｡)ﾉﾟ.:｡+ﾟ");
    }
}
```

