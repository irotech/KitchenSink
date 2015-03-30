# Java Project

Create simple java project using the default template of the archetype plugin with goal generate:

mvn archetype:generate -DarchetypeArtifactId=maven-archetype-quickstart -DgroupId=com.gmail.at.irotech -DartifactId=maven-java-project

After the execution we will have the project structure below:

<root-dir>
	src
		main
			java
				<package>
					App.java

		test
			java
				<package>
					AppTest.java
	pom.xml
	