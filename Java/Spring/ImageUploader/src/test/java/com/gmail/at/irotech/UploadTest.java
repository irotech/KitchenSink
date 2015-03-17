package com.gmail.at.irotech;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.fileUpload;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.NestedServletException;

import com.gmail.at.irotech.controller.FileUploadController;
import com.gmail.at.irotech.model.FileUpload;
import com.gmail.at.irotech.model.FileUploadForm;
import com.gmail.at.irotech.validator.FileUploadValidator;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/customConf-servlet.xml" })
public class UploadTest extends WebMvcConfigurationSupport {

	@Mock
    private FileUploadValidator validator;
	
	@InjectMocks
	FileUploadController fileUploadController;
	
	private static String CONTROLLER_URL = "/show";
	private static String CONTROLLER_SAVE = "/save";
	
	private static String VIEW_MAPPING = "file_upload_form";
	private static String SUCCESS_MAPPING = "file_upload_success";

	protected MockMvc mockMvc;

	MockMultipartFile firstImageFile;
	MockMultipartFile secondImageFile;
	MockMultipartFile thirdImageFile;
	MockMultipartFile bigImageFile;
	MockMultipartFile noImageFile;
	MockMultipartFile xmlFile;
	MockMultipartFile jsonFile;

	FileUploadForm uploadForm;
	List<FileUpload> uploadFormList;
	FileUpload imageFile1;
	FileUpload imageFile2;
	FileUpload imageFile3;
	FileUpload bigImageFile1;
	FileUpload textFile1;
	FileUpload xmlFile1;
	FileUpload jsonFile1;

	 @Autowired
	 private WebApplicationContext webApplicationContext;

	@Before
	public void setup() throws IOException {

		MockitoAnnotations.initMocks(this);

		mockMvc = MockMvcBuilders.standaloneSetup(fileUploadController)
				.setViewResolvers(viewResolver()).build();
		
		uploadForm = new FileUploadForm();

		// @formatter:off
		firstImageFile = 
				new MockMultipartFile("image1", "filename1.jpg", "image/jpeg", "abc".getBytes());
		secondImageFile = new MockMultipartFile("image2", "filename2.txt", "image/gif", "def".getBytes());
		thirdImageFile = new MockMultipartFile("image3", "filename3.txt", "image/png", "ghi".getBytes());
		noImageFile = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());
		xmlFile = new MockMultipartFile("data", "file.xml", "application/xml", "<?xml version=\"1.0\" encoding=\"UTF-8\"?><root></root>".getBytes());
		jsonFile = new MockMultipartFile("json", "", "application/json", "{\"json\": \"someValue\"}".getBytes());
		// @formatter:on
		
		imageFile1 = new FileUpload();
		imageFile1.setFile(firstImageFile);
		
		imageFile2 = new FileUpload();
		imageFile2.setFile(secondImageFile);
		
		imageFile3 = new FileUpload();
		imageFile3.setFile(thirdImageFile);
		
		textFile1 = new FileUpload();
		textFile1.setFile(noImageFile);
		
		xmlFile1 = new FileUpload();  
		xmlFile1.setFile(xmlFile);
		
		jsonFile1 = new FileUpload(); 
		jsonFile1.setFile(jsonFile);
		
		uploadFormList = new ArrayList<FileUpload>();
		uploadForm.setFiles(uploadFormList);

	}
	
	@After
	public void cleanUp() {
	}

	private InternalResourceViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/jsp");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}

	@Test
	public void testLanding() {
		String response = fileUploadController.displayForm();
        assertNotNull("no response", response);
        assertEquals("view", VIEW_MAPPING, response);
	}
	
	@Test
	public void testGET() throws Exception {
		  mockMvc.perform(get(CONTROLLER_URL))
		    .andExpect(status().isOk())
		     .andExpect(forwardedUrl("/WEB-INF/jsp/" + VIEW_MAPPING + ".jsp"));
	}
	
	/* IllegalStateException: Invalid target for Validator [validator] */
	@Test(expected = NestedServletException.class)
	public void testPOST_Validator_target_KO() throws URISyntaxException, Exception {
		mockMvc
		.perform(MockMvcRequestBuilders.fileUpload(new URI(CONTROLLER_SAVE))
				.file(noImageFile))
				.andReturn();
	}
	
	/* FIXME @Valid @ModelAttribute("uploadForm") FileUploadForm uploadForm null */
	@Test
	public void testPOST_OK() throws URISyntaxException, Exception {
		
		uploadFormList.add(imageFile1);
		uploadFormList.add(imageFile2);
		uploadFormList.add(imageFile3);
		
		mockMvc
		.perform(MockMvcRequestBuilders.fileUpload(new URI(CONTROLLER_SAVE))
				.file(firstImageFile))
				.andExpect(status().isOk())
				.andExpect(content().string(SUCCESS_MAPPING))
				.andExpect(forwardedUrl("/WEB-INF/jsp/" + SUCCESS_MAPPING + ".jsp"))
				;
	}
	
	//FIXME
	@Test
	public void testPOST_File_Dimension_KO() throws URISyntaxException, Exception {
		
		when(validator.supports(FileUploadForm.class)).thenReturn(true);
		
		bigImageFile = new MockMultipartFile("files[0].file", "image_large.gif", "image/gif", this.getClass().getResourceAsStream("/image_large.gif"));
		bigImageFile1 = new FileUpload();
		bigImageFile1.setFile(bigImageFile);
		uploadFormList.add(bigImageFile1);
		
		mockMvc
		.perform(MockMvcRequestBuilders.fileUpload(new URI(CONTROLLER_SAVE))
				.requestAttr("uploadForm", uploadForm))
				.andExpect(status().isOk())
				.andExpect(content().string(VIEW_MAPPING))
		;
	}
	
	//FIXME
	@Test
	public void testPOST_File_Content_Type_KO() throws URISyntaxException, Exception {
		
		when(validator.supports(FileUploadForm.class)).thenReturn(true);
		
		bigImageFile = new MockMultipartFile("files[0].file", "image_large.gif", "image/gif", this.getClass().getResourceAsStream("/image_large.gif"));
		bigImageFile1 = new FileUpload();
		bigImageFile1.setFile(bigImageFile);
		uploadFormList.add(bigImageFile1);
		
		mockMvc
		.perform(MockMvcRequestBuilders.fileUpload(new URI(CONTROLLER_SAVE))
				.requestAttr("uploadForm", uploadForm))
				.andExpect(status().isOk())
				.andExpect(content().string(VIEW_MAPPING))
		;
	}	

	@Test
	public void test2() throws Exception {

		when(validator.supports(any(Class.class))).thenReturn(true);
				
		try {
			mockMvc.perform(
					MockMvcRequestBuilders.fileUpload("/save")
							.file(firstImageFile)
							.param("alttag", "alttag")
							.param("caption", "caption")
							)
							.andExpect(
					MockMvcResultMatchers.status().isOk())
					;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}