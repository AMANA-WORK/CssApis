package sa.gov.alriyadh.amana.utils;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.DatatypeConverter;

public class FileUtil {

	public static Set<String> allowedTypes = new HashSet<>(Arrays.asList("pdf", "jpg", "png", "jpeg", "gif"));

	public static String getSystemSeparator() {
		return File.separator;
	}

	public static boolean createFile(String filePath, String base64) {
		boolean created = false;
		OutputStream outputStream = null;

		try {
			// Remove the data:image/...;base64, part if it exists
			if (base64.contains(",")) {
				base64 = base64.substring(base64.indexOf(",") + 1);
			}

			File targetFile = new File(filePath);
			if (!targetFile.getParentFile().exists()) {
				created = targetFile.getParentFile().mkdirs();
			}

			byte[] data = Base64.getDecoder().decode(base64);
			outputStream = new BufferedOutputStream(new FileOutputStream(targetFile));
			outputStream.write(data);
			created = true;

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (outputStream != null)
					outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return created;
	}

	public static String encodeFileToBase64Binary(String filePath) {
		String encodedfile = null;
		FileInputStream fileInputStreamReader = null;
		try {
			File file = new File(filePath);
			fileInputStreamReader = new FileInputStream(file);
			byte[] bytes = new byte[(int) file.length()];
			fileInputStreamReader.read(bytes);
			encodedfile = Base64.getEncoder().encodeToString(bytes).toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileInputStreamReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return encodedfile;
	}

	public static String convertPdfUrlToBase64(String pdfUrl) {
		String base64EncodedPdf = "";
		try {
			URL url = new URL(pdfUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			try (InputStream inputStream = connection.getInputStream();
					ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
				// Read the input stream into the byte array output stream
				byte[] buffer = new byte[1024];
				int bytesRead;
				while ((bytesRead = inputStream.read(buffer)) != -1) {
					byteArrayOutputStream.write(buffer, 0, bytesRead);
				}
				// Convert the ByteArrayOutputStream to byte array
				byte[] pdfBytes = byteArrayOutputStream.toByteArray();
				// Encode the byte array to Base64
				base64EncodedPdf = Base64.getEncoder().encodeToString(pdfBytes);
				System.out.println(base64EncodedPdf);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return base64EncodedPdf;
	}

	public static String getApiFileRoot(){
		LocalDate today = LocalDate.now();
		String year = String.valueOf(today.getYear());
		String month = String.format("%02d", today.getMonthValue());
		String separator = FileUtil.getSystemSeparator();
		return separator+ "img"+separator+"CSS" + separator + year + separator + month + separator;
	}

	public static String getFileBase64Extention(String base64){
		return base64.split(";")[0].split("/")[1];
	}

}
