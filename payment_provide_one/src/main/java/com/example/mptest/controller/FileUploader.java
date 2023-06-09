package com.example.mptest.controller;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.UploadObjectArgs;
import io.minio.errors.MinioException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class FileUploader {

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            // Create a minioClient with the MinIO server playground, its access key and secret key.
            MinioClient minioClient =
                    MinioClient.builder()
                            .endpoint("http://192.168.56.99:9000")
                            .credentials("hQatBKOhMDoowP7D", "DmQYWDXHTeJ9FEjUZQjMULlJfJbVBOEo")
                            .build();

            // Make 'asiatrip' bucket if not exist.
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket("mybucket").build());
            if (!found) {
                // Make a new bucket called 'asiatrip'.
                minioClient.makeBucket(MakeBucketArgs.builder().bucket("mybucket").build());
            } else {
                System.out.println("Bucket 'mybucket' already exists.");
            }

            // Upload '/home/user/Photos/asiaphotos.zip' as object name 'asiaphotos-2015.zip' to bucket
            // 'asiatrip'.
            minioClient.uploadObject(
                    UploadObjectArgs.builder()
                            .bucket("mybucket")
                            .object("net.docx")
                            .filename("D:/MyData/shizq18/Desktop/net.docx")
                            .build());
            System.out.println("'D:/MyData/shizq18/Desktop/net.docx' is successfully uploaded as " + "object 'net.docx' to bucket 'mubucket'.");
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            System.out.println("HTTP trace: " + e.httpTrace());
        }
    }
}