# Java Web Project

Create java web project using the template of the archetype plugin with goal generate:

mvn archetype:generate -DarchetypeArtifactId=maven-archetype-webapp -DgroupId=com.gmail.at.irotech -DartifactId=maven-java-web-project

After the execution we will have the project structure below:

<root-dir>
	src
		main
			resources
			webapp
				WEB-INF
					web.xml
				index.jsp
	pom.xml
	