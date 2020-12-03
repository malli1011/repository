package java8streams.examples;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URLConnection;
import java.util.stream.IntStream;

public class FileDownload {
    public static void main(String... args) {

        String url = "http://dl5.rmdlsv.com/tv-series/Young-Sheldon/S02/720P/";
        String filename = "C:\\Users\\mallikarjuna_bandi\\Downloads\\";

        IntStream.range(19,20).parallel().forEach( i->{
           String newFilename= "Young_Sheldon_S02E"+i+"_720p_HDTV_nItRo_%5BRoseMovie%5D.mkv";
            String newUrl = url.concat(newFilename);
            String newFile = filename.concat(newFilename.replace("%",""));
            System.out.println(newUrl);
            System.out.println(newFile);
            downloadVideos(newUrl,newFile);
        });
    }

    private static void downloadVideos(String url,String filename){
        try {
            File outputFile = new File(filename);
            long existingFileSize = outputFile.length();
            URLConnection downloadFileConnection = new URI(url).toURL()
                    .openConnection();

            HttpURLConnection httpFileConnection = (HttpURLConnection) downloadFileConnection;

            long fileLength = downloadFileConnection.getContentLengthLong();


            while (existingFileSize < fileLength) {
                try {
                    existingFileSize = outputFile.length();
                    httpFileConnection = (HttpURLConnection) new URI(url).toURL()
                            .openConnection();
                    httpFileConnection.setRequestProperty("Range", "bytes=" + existingFileSize + "-" + fileLength);
                    transferDataAndGetBytesDownloaded(httpFileConnection, outputFile);
                } catch (Exception e) {
                    Thread.sleep(1000);
                    System.out.println("download failed." + e.getMessage());
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void transferDataAndGetBytesDownloaded(URLConnection downloadFileConnection, File outputFile) throws IOException {
        try (InputStream is = downloadFileConnection.getInputStream(); OutputStream os = new FileOutputStream(outputFile, true)) {
            byte[] buffer = new byte[1024];
            int bytesCount;
            while ((bytesCount = is.read(buffer)) > 0) {
                os.write(buffer, 0, bytesCount);
            }
        }

    }




}
