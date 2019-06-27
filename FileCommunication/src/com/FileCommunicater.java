package com;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

import javax.xml.bind.DatatypeConverter;

public class FileCommunicater {
	public static String encodeFromFile(File input) throws IOException {
		byte[] data = Files.readAllBytes(input.toPath());
		Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(data);
	}
	
	public static String getMD5CodeOfFile(File file) throws NoSuchAlgorithmException, IOException {
		byte[] data = Files.readAllBytes(file.toPath());
		MessageDigest md5Digest = MessageDigest.getInstance("MD5");
		byte[] md5 = md5Digest.digest(data);
		String md5Code = DatatypeConverter.printHexBinary(md5);
		return md5Code;
	}
	
	public static void decodeToFile(File output, String base64String) throws IOException {
		Decoder decoder = Base64.getDecoder();
		byte[] data = decoder.decode(base64String);
		FileOutputStream stream = new FileOutputStream(output);
		stream.write(data);
		stream.close();
	}
}
