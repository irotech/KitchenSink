<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
		<title>Spring Image Upload</title>
		<link href="${pageContext.request.contextPath}/res/css/file_upload_styles.css" rel="stylesheet" >
	</head>
	<body>
		<h1>Spring Image Uploads</h1>
		<p>Following files are uploaded successfully.</p>
		<ol>
			<c:forEach items="${files}" var="file">
				<li>${file}</li>
			</c:forEach>
		</ol>
	</body>
</html>