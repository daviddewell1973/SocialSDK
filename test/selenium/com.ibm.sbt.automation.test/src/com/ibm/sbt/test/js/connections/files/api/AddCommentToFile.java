package com.ibm.sbt.test.js.connections.files.api;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ibm.commons.util.StringUtil;
import com.ibm.commons.util.io.json.JsonJavaObject;
import com.ibm.sbt.automation.core.test.connections.BaseFilesTest;
import com.ibm.sbt.automation.core.test.pageobjects.JavaScriptPreviewPage;

public class AddCommentToFile extends BaseFilesTest {

	static final String SNIPPET_ID_FILE = "Social_Files_API_FileAddComment";

	@Before
	public void init() {
		createFile();
		addSnippetParam("sample.fileId", fileEntry.getFileId());
	}

	@After
	public void destroy() {
		deleteFileAndQuit();
	}

	@Test
	public void testFileAddComment() {
		JavaScriptPreviewPage previewPage = executeSnippet(SNIPPET_ID_FILE);
		JsonJavaObject json = previewPage.getJson();
		assertTrue(json.getString("getContent").startsWith("Comment Added from JS Sample"));
		assertEquals(fileEntry.getAuthorEntry().getUserUuid(), json.getJsonObject("getAuthor").getString("authorUserId"));
		assertEquals(fileEntry.getAuthorEntry().getName(), json.getJsonObject("getAuthor").getString("authorName"));
		if (!StringUtil.isEmpty(fileEntry.getAuthorEntry().getEmail())) {
			assertEquals(fileEntry.getAuthorEntry().getEmail(), json.getJsonObject("getAuthor").getString("authorEmail"));
		}
		assertEquals(fileEntry.getAuthorEntry().getUserState(), json.getJsonObject("getAuthor").getString("authorUserState"));
		assertEquals("Re: " + fileEntry.getTitle(), json.getString("getTitle"));
		assertEquals("1", json.getString("getVersionLabel"));
		assertEquals(fileEntry.getAuthorEntry().getUserUuid(), json.getJsonObject("getModifier").getString("modifierUserId"));
		assertEquals(fileEntry.getAuthorEntry().getUserState(), json.getJsonObject("getModifier").getString("modifierUserState"));
		assertEquals("en", json.getString("getLanguage"));
		assertEquals("true", json.getString("getDeleteWithRecord"));
	}

}