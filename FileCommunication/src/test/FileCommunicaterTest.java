package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.FileCommunicater;

class FileCommunicaterTest {

	private static File input, output, decodeTextFile, md5TextFile;

	@BeforeAll
	static void initFile() throws UnsupportedEncodingException {
		String binPath = FileCommunicaterTest.class.getResource("..").getPath();
		binPath = URLDecoder.decode(binPath,"utf-8");  
		File binFolder = new File(binPath);
		File dataFolder = new File(binFolder.getParentFile(), "data");
		
		input = new File(dataFolder, "logo.png");
		output = new File(dataFolder, "output_logo.png");
		decodeTextFile = new File(dataFolder, "decode_logo.txt");
		md5TextFile = new File(dataFolder, "md5_log.txt");
	}
	
	@Test
	void testEncode() throws IOException {
		String excepted = getDecodeStringOfTestFile();
		String actual = FileCommunicater.encodeFromFile(input);
		assertEquals(excepted, actual);
	}
	
	private String getDecodeStringOfTestFile() throws IOException {
		String decodeString = Files.readAllLines(decodeTextFile.toPath()).get(0);
		return decodeString;
	}
	
	@Test
	void testGetMD5Code() throws NoSuchAlgorithmException, IOException {
		String excepted = getMD5StringOfTestFile();
		String actual = FileCommunicater.getMD5CodeOfFile(input);
		assertEquals(excepted, actual);
	}
	
	private String getMD5StringOfTestFile() throws IOException {
		String decodeString = Files.readAllLines(md5TextFile.toPath()).get(0);
		return decodeString;
	}

	@Test
	void testDecode() throws IOException, NoSuchAlgorithmException {
		String excepted = getMD5StringOfTestFile();
		String data = getDecodeStringOfTestFile();
		FileCommunicater.decodeToFile(output, data);
		String actual = FileCommunicater.getMD5CodeOfFile(output);
		assertEquals(excepted, actual);
	}

}
