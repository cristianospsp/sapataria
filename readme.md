#Some setup information :).

## DataSource para WildFly 9

Dentro da pasta /com, ex. abaixo:

wildfly-9.0.2.Final/modules/system/layers/base/com

Criar a pasta mysql/main, ficará assim:

wildfly-9.0.2.Final/modules/system/layers/base/com/mysql/main

dentro da pasta main copiar os arquivos do GOOGLE-DRIVE loca: Pim > 4º Sem > DRIVER-MYSQL:
- module.xml
- mysql-connector-java-5.1.29-bin.jar

Deverá ficar assim:
wildfly-9.0.2.Final/modules/system/layers/base/com/mysql/main/module.xml
wildfly-9.0.2.Final/modules/system/layers/base/com/mysql/main/mysql-connector-java-5.1.29-bin.jar


Agora vamos configurar o arquivo standalone.xml que esta na pasta:
- wildfly-9.0.2.Final/standalone/configuration/standalone.xml

Localizar a sessao:   <!-- <datasources> </datasources>  -->

Dentro dela Adicionar como segue abaixo:

<!--

<datasources> 
	<datasource jta="false" jndi-name="java:jboss/datasources/SapatariaDS" pool-name="SapatariaDS" enabled="true" use-java-context="true">
		<connection-url>jdbc:mysql://localhost:3306/sapataria</connection-url>
		<driver>mysql</driver>
		<security>
			<user-name>root</user-name>
		</security>
		<drivers>
	       <driver name="mysql" module="com.mysql">
	           <xa-datasource-class>com.mysql.jdbc.jdbc2.optional.MysqlXADataSource</xa-datasource-class>
	       </driver>
	    </drivers>
	</datasource>
</datasources>

-->

OBS: Caso exista outras configurações, NAO REMOVA NADA, apenas adicione como as instruções acima. :o)