<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
	<head>
		<title>Spring Image Upload</title>
	
		<link href="${pageContext.request.contextPath}/res/css/file_upload_styles.css" rel="stylesheet" >
		
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		
		<script>
		$(document).ready(function() {
			init();
			addObj();
			checkedOjb();
		});
		function init() {
			$('#addFile').prop('disabled', false);
		}
		function addObj() {
			$('#addFile').click(function() {
				var fileContainerIndex = $(".fileUpload").length;
				$('#fileUploadContainer').append(
						'<div id="fileUpload" class="fileUpload" name="files[' + fileContainerIndex + ']">' +
						'<div id="fileContent">' +
							'<input name="files[' + fileContainerIndex + '].file" type="file" cssClass="error" />' +
						'</div>' +
						'<div id="fileMeta">' +
							'<input type="text" id="alttag' + fileContainerIndex + '" name="files[' + fileContainerIndex + '].alttag" cssClass="error" />' +
							'<input type="text" id="caption' + fileContainerIndex + '" name="files[' + fileContainerIndex + '].caption" />' +
							'<input type="checkbox" id="default' + fileContainerIndex + '" name="files[' + fileContainerIndex + '].defaultname">Default' +
						'</div>' +
					'</div>');
				if(fileContainerIndex>1) {
					$('#addFile').prop('disabled', true);
				}
			});
		}
		function checkedOjb() { //TODO extends checks other fields
			$('#default0').click(function() {
			    if($("#default0").is(':checked')) {
					$("#alttag0").val("Default Tag");
					$("#caption0").val("text-align: center");
					//.prop('disabled', true)
			    } else {
					$("#alttag0").val("");
					$("#caption0").val("");
					//.prop('disabled', true)
			    }
			});
		}
		</script>
	</head>
	<body>
		<h1>Spring Image Upload</h1>
		<form:form 	id="uploadForm"
					method="post" 
					action="save.html" 
					modelAttribute="uploadForm" 
					enctype="multipart/form-data">
			<form:errors path="*" cssClass="errorblock" element="div" />
			<p>Select files to upload.</p>
			<p>Press Add button to add more file inputs.</p>
			<div id="fileUploadContainer">
				<div id="fileUpload" class="fileUpload" name="files[0]">
					<div id="fileContent">
						<input name="files[0].file" type="file" cssClass="error" />
					</div>
					<div id="fileMeta">
						<input type="text" id="alttag0" name="files[0].alttag" cssClass="error" />
						<input type="text" id="caption0" name="files[0].caption" />
						<input type="checkbox" id="default0" name="files[0].defaultname">Default
					</div>
				</div>
			</div>
			<br/>
			<div id="uploadControls">
				<div id="uploadAdd">
					<input id="addFile" type="button" value="Add File" />
				</div>
				<div id="uploadSubmit">
					<input type="submit" value="Upload" />
				</div>
			</div>
		</form:form>
	</body>
</html>